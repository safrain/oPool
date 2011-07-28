package safrain.nodelet.tick;

import safrain.nodelet.event.Event;

public class TickEvent extends Event {

	/**
	 * 跟上一次Tick的时间间隔
	 */
	public long timeElapsed;

}
