package safrain.nodelet.test.viewer.game;

import safrain.nodelet.event.EventListener;
import safrain.nodelet.game.Game;
import safrain.nodelet.tick.TickEvent;

public abstract class BaseGameViewer {
	protected Game game = new Game();

	public abstract void beforeStart();

	private EventListener<TickEvent> tickListener = new EventListener<TickEvent>() {

		@Override
		public void onEvent(TickEvent event) {
			onTick();
		}
	};

	protected void onTick() {

	}

	public void start() {
		Game.instance = game;
		game.init();
		beforeStart();
		game.tickService.eventSource.addListener(tickListener);
		game.start();
	}

}
