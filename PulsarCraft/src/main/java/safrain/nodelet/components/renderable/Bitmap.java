package safrain.nodelet.components.renderable;

import safrain.nodelet.components.position.Position;
import safrain.nodelet.graphic.renderop.AnimateOp;
import safrain.nodelet.graphic.renderop.BitmapOp;
import safrain.nodelet.graphic.renderop.RenderOp;
import safrain.nodelet.pool.Reset;

public class Bitmap extends Renderable {
	/* 图像 */
	@Reset
	public int bitmapId;
	@Reset
	public double zoomX;
	@Reset
	public double zoomY;
	@Reset
	public double alpha;
	@Reset
	public double pivotX;
	@Reset
	public double pivotY;

	/* 动画，只有在isAnimate为真的时候才会用到 */

	/**
	 * 指示是否是动画
	 */
	@Reset
	public boolean isAnimate;
	/**
	 * 每一帧的高度（因为是连起来的长条）
	 */
	@Reset
	public double frameHeight;
	/**
	 * 当前帧
	 */
	@Reset
	public int currentFrame;
	/**
	 * 总帧数
	 */
	@Reset
	public int frameCount;
	/**
	 * 是否循环播放
	 */
	@Reset
	public boolean loop;
	/**
	 * 动画是否播放结束
	 */
	@Reset
	public boolean isOver;
	/**
	 * 每次增加的Time计数，最大为1
	 */
	@Reset
	public double timeIncreasement;
	/**
	 * 当前Time
	 */
	@Reset
	public double currentTime;

	@Override
	public void tick() {
		if (isOver) {
			return;
		}
		if (isAnimate) {
			if (currentTime >= 1.0d) {// 如果播放完毕
				if (loop) {// 如果是循环播放，则退回去
					currentTime = 0d;
				} else {// 如果不是循环播放，则播放结束
					isOver = true;
					return;
				}
			}
			// 计算帧位置
			currentFrame = (int) (frameCount * currentTime);
			currentTime += timeIncreasement;
		}
		super.tick();
	}

	@Override
	protected RenderOp getRenderOp() {
		if (!isAnimate) {
			BitmapOp renderOp = new BitmapOp();
			fillBitmapOp(renderOp);
			return renderOp;
		} else {
			AnimateOp renderOp = new AnimateOp();
			fillBitmapOp(renderOp);
			renderOp.frameHeight = frameHeight;
			renderOp.currentFrame = currentFrame;
			return renderOp;
		}
	}

	/**
	 * 每次绘图的时候，需要计算绝对位置，用这个对象暂存计算结果以防止过多的new
	 * 
	 */
	private Position fillBuffer = new Position();

	private void fillBitmapOp(BitmapOp bitmapOp) {
		position.getAbsolutePosition(fillBuffer);
		bitmapOp.x = fillBuffer.x;
		bitmapOp.y = fillBuffer.y;
		bitmapOp.angle = fillBuffer.angle;
		bitmapOp.bitmapId = bitmapId;
		bitmapOp.zoomX = zoomX;
		bitmapOp.zoomY = zoomY;
		bitmapOp.alpha = alpha;
		bitmapOp.pivotX = pivotX;
		bitmapOp.pivotY = pivotY;
	}

	public static class Builder extends Renderable.Builder<Bitmap> {
		public int bitmapId;
		public double zoomX;
		public double zoomY;
		public double alpha;
		public double pivotX;
		public double pivotY;

		public double width;
		public double height;

		public boolean isAnimate;
		public double frameHeight;
		public boolean loop;
		/**
		 * 播放时间，单位是Tick
		 */
		public double time;

		@Override
		public void fill(Bitmap t) {
			t.bitmapId = bitmapId;
			t.zoomX = zoomX;
			t.zoomY = zoomY;
			t.alpha = alpha;
			t.pivotX = pivotX;
			t.pivotY = pivotY;
			t.isAnimate = isAnimate;
			t.frameHeight = frameHeight;
			t.loop = loop;
			t.frameCount = (int) (height / frameHeight);
			if (time == -1) {
				time = t.frameCount;
			}
			t.timeIncreasement = 1 / time;
		}

	}
}
