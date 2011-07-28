package safrain.nodelet.input;

import safrain.nodelet.event.Event;
import safrain.nodelet.pool.Reset;

public class InputEvent extends Event {
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int MOVE = 3;
	@Reset
	public int type;
	@Reset
	public double x;
	@Reset
	public double y;
}
