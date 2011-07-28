package safrain.nodelet.graphic;

import safrain.nodelet.event.EventListener;
import safrain.nodelet.graphic.driver.IGraphicDriver;
import safrain.nodelet.graphic.renderop.RenderOp;
import safrain.nodelet.tick.TickEvent;

/**
 * 绘图服务
 * 
 * @author safrain
 * 
 */
public class GraphicService {

	/**
	 * 切换帧的时候的同步锁
	 */
	private final byte[] lock = new byte[0];

	/**
	 * 三个帧，只需要三个帧就可以满足绘图需要，同一时刻有一个FILLING，一个DRAWING，最多两个READY和EMPTY
	 */
	private final Frame[] frames = new Frame[] { new Frame(), new Frame(),
			new Frame() };
	/**
	 * 当前正在由主线程填充的帧
	 */
	private Frame filling;

	/**
	 * 当前正在由绘图线程绘制的帧
	 */
	private Frame drawing;

	/**
	 * 绘图用的图形驱动
	 */
	public IGraphicDriver driver;

	/**
	 * 指示是否绘制调试信息
	 */
	public final boolean drawDebugInfo = true;

	private final DebugInfo debugInfo = new DebugInfo();

	/**
	 * 绘图监听器
	 */
	public final EventListener<TickEvent> tickListener = new EventListener<TickEvent>() {
		@Override
		public void onEvent(TickEvent event) {
			doRender();
		}
	};

	/**
	 * 主线程触发开始填充一个帧
	 */
	public void frameStart() {
		synchronized (lock) {
			if (filling != null) {// 如果当前有正在填充的帧，状态错误
				throw new IllegalStateException();
			}

			Frame toFill = null;
			for (Frame frame : frames) {
				int s = frame.getState();
				if (s == Frame.EMPTY) {// 如果是EMPTY的帧 可以直接填充
					toFill = frame;
					break;
				}
				if (s == Frame.READY) {// 如果是READY的帧 需要确定没有EMPTY帧后再填充
					toFill = frame;
				}
			}
			// 切换帧的状态并fill
			toFill.switchState(Frame.FILL);
			filling = toFill;
		}
	}

	/**
	 * 主县城触发结束填充一个帧
	 */
	public void frameEnd() {
		synchronized (lock) {
			if (filling == null) {// 帧结束的时候必须有正在填充的帧也就是start end需要配对
				throw new IllegalStateException();
			}
			// 清掉以前填充但未绘制的READY状态的帧，这个操作是为了防止帧绘制顺序错误
			for (Frame frame : frames) {
				if (frame.getState() == Frame.READY) {
					frame.switchState(Frame.EMPTY);
				}
			}
			filling.switchState(Frame.READY);
			filling = null;
		}
	}

	/**
	 * 绘制方法，从指令队列中取出一帧，然后进行各种绘制操作
	 */
	private void doRender() {
		if (processFrameStart()) {
			driver.startRender();
			int size = drawing.ops.size();
			for (int i = 0; i < size; i++) {
				driver.process(drawing.ops.get(i));
			}
			if (drawDebugInfo) {
				debugInfo.addFrame();
				driver.processDebugInfo(debugInfo);
			}
			processFrameEnd();
			driver.endRender();
		}

	}

	private boolean processFrameStart() {
		synchronized (lock) {
			Frame toDraw = null;
			for (Frame frame : frames) {
				if (frame.getState() == Frame.READY) {
					if (toDraw == null) {
						toDraw = frame;
					}
				}
			}
			if (toDraw != null) {
				toDraw.switchState(Frame.DRAW);
				drawing = toDraw;
				return true;
			}
			return false;
		}
	}

	private void processFrameEnd() {
		synchronized (lock) {
			drawing.switchState(Frame.EMPTY);
			drawing = null;
		}
	}

	public void post(RenderOp renderOp) {
		if (renderOp != null) {
			filling.ops.add(renderOp);
		}
	}

}
