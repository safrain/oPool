package safrain.nodelet.tick;

import safrain.nodelet.event.EventSource;

public class TickService {

	public final EventSource<TickEvent> eventSource = new EventSource<TickEvent>();

	private long tickRate = 30;

	public long getTickRate() {
		return tickRate;
	}

	public void setTickRate(long tickRate) {
		this.tickRate = tickRate;
		tickInterval = 1000 / tickRate;
	}

	private long tickInterval = 1000 / tickRate;
	private TickThread thread;
	private ThreadState state = ThreadState.IDLE;

	public void start() {
		if (state == ThreadState.IDLE) {
			state = ThreadState.RUNNING;
			thread = new TickThread();
			thread.suppressTimeElapsed = true;
			thread.start();
		}
	}

	public void pause() {
		if (state == ThreadState.RUNNING) {
			state = ThreadState.PAUSING;
		}
	}

	public void resume() {
		if (state == ThreadState.PAUSED) {
			synchronized (thread) {
				thread.notify();
			}
			state = ThreadState.RUNNING;
		}
	}

	public void stop() {
		state = ThreadState.STOPING;
	}

	private long lastTickTime = 0;

	private class TickThread extends Thread {

		/**
		 * 防止第一次Tick或者暂停后过大的timelapsed
		 */
		private boolean suppressTimeElapsed;

		@Override
		public void run() {
			long timeElapsed;
			while (true) {
				if (state == ThreadState.RUNNING) {
					long currentTime = System.nanoTime();
					if (suppressTimeElapsed) {
						timeElapsed = 0;
						suppressTimeElapsed = false;
					} else {
						timeElapsed = (currentTime - lastTickTime) / 1000000;
					}
					lastTickTime = currentTime;
					dispatchTickEvent(timeElapsed);
					sleepWhenNeed(timeElapsed);
				} else if (state == ThreadState.PAUSING) {
					synchronized (this) {
						try {
							state = ThreadState.PAUSED;
							wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				} else if (state == ThreadState.STOPING) {
					state = ThreadState.IDLE;
					break;
				}

			}
		}

		private void dispatchTickEvent(long timeElapsed) {
			TickEvent tickEvent = new TickEvent();
			tickEvent.timeElapsed = timeElapsed;
			tickEvent.source = TickService.this;
			eventSource.dispatch(tickEvent);
		}

		private void sleepWhenNeed(long timeElapsed) {
			long sleepTime = tickInterval - timeElapsed;
			if (sleepTime > 0) {
				try {
					synchronized (this) {
						wait(sleepTime);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static enum ThreadState {
		IDLE, RUNNING, PAUSING, PAUSED, STOPING;
	}
}
