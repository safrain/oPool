package safrain.nodelet.graphic;

import java.util.ArrayList;

import safrain.nodelet.graphic.renderop.RenderOp;

/* Frame */
public class Frame {
	ArrayList<RenderOp> ops = new ArrayList<RenderOp>();
	// 此帧的状态
	private int state;
	// 空
	static final int EMPTY = 0;
	// 正在由外部填充绘图指令
	static final int FILL = 1;
	// 填充完成，形成一帧
	static final int READY = 2;
	// 正在绘制
	static final int DRAW = 3;

	public int getState() {
		return state;
	}

	void switchState(int newState) {
		switch (newState) {
		case EMPTY:
		case FILL:
			clear();
			state = newState;
			break;
		case READY:
			state = newState;
			break;
		case DRAW:
			state = newState;
			break;
		}
	}

	void clear() {
		ops.clear();
	}

}
