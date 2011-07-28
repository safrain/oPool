package safrain.nodelet.graphic.driver.awt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import safrain.nodelet.game.Game;
import safrain.nodelet.graphic.BitmapResourceManager;
import safrain.nodelet.graphic.DebugInfo;
import safrain.nodelet.graphic.driver.IGraphicDriver;
import safrain.nodelet.graphic.renderop.AnimateOp;
import safrain.nodelet.graphic.renderop.BitmapOp;
import safrain.nodelet.graphic.renderop.RenderOp;
import safrain.nodelet.input.driver.awt.AWTInputDriver;

public class AWTDriver implements IGraphicDriver {

	private AWTBitmapResourceManager bitmapResourceManager;

	private JFrame frame;
	private JPanel panel;
	private Graphics2D graphics;
	private Image buffer;

	@SuppressWarnings("serial")
	public void init(int width, int height) {
		bitmapResourceManager = new AWTBitmapResourceManager();
		buffer = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		graphics = (Graphics2D) buffer.getGraphics();

		frame = new JFrame();
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(buffer, 0, 0, null);
			}
		};
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 加入Input
		AWTInputDriver awtInputDriver = new AWTInputDriver(
				Game.instance.inputService);
		panel.addMouseListener(awtInputDriver);
		panel.addMouseMotionListener(awtInputDriver);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				frame.setVisible(true);
			}
		});
		while (!panel.isVisible()) {
		}
	}

	@Override
	public void processDebugInfo(DebugInfo debugInfo) {
		graphics.setColor(Color.WHITE);
		graphics.drawString(String.format("fps:%.1f", debugInfo.fps), 10, 10);
	}

	@Override
	public void process(RenderOp renderOp) {
		switch (renderOp.getTypeCode()) {
		case 1:// BitmapOp
			processBitmapOp((BitmapOp) renderOp);
			break;
		case 2:// AnimateOp
			processAnimateOp((AnimateOp) renderOp);
			break;
		}
	}

	private void processBitmapOp(BitmapOp op) {
		Image image = bitmapResourceManager.bitmaps.get(op.bitmapId);

		AffineTransform transform = getAffineTransform(op.x, op.y, op.pivotX,
				op.pivotY, op.zoomX, op.zoomY, op.angle);
		graphics.drawImage(image, transform, null);
	}

	private void processAnimateOp(AnimateOp op) {
		Image image = bitmapResourceManager.bitmaps.get(op.bitmapId);
		AffineTransform transform = getAffineTransform(op.x, op.y, op.pivotX,
				op.pivotY, op.zoomX, op.zoomY, op.angle);
		int imageWidth = image.getWidth(null);
		int imageHeight = (int) op.frameHeight;
		AffineTransform originalTransform = graphics.getTransform();
		graphics.setTransform(transform);
		graphics.drawImage(image, 0, 0, imageWidth, imageHeight, 0, imageHeight
				* op.currentFrame, imageWidth, imageHeight
				* (1 + op.currentFrame), null);
		graphics.setTransform(originalTransform);
	}

	private AffineTransform getAffineTransform(double translateX,
			double translateY, double pivotX, double pivotY, double scaleX,
			double scaleY, double rotation) {
		AffineTransform transform = new AffineTransform();
		// 平移=>旋转=>缩放=>Pivot
		// TODO:如果加viewport 连接矩阵即可
		transform.translate(translateX, translateY);
		transform.rotate(-rotation * Math.PI / 180);
		transform.scale(scaleX, scaleY);
		transform.translate(-pivotX, -pivotY);
		return transform;
	}

	@Override
	public void startRender() {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, buffer.getWidth(null), buffer.getHeight(null));
	}

	@Override
	public void endRender() {
		panel.repaint();
	}

	@Override
	public BitmapResourceManager getBitmapResourceManager() {
		return bitmapResourceManager;
	}

	@Override
	public int registerBitmapResource(String address) {
		return bitmapResourceManager.registerBitmapResource(address);
	}

}
