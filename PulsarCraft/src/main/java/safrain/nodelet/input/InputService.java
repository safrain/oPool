package safrain.nodelet.input;

import java.util.ArrayList;
import java.util.List;

import safrain.nodelet.game.Game;

public class InputService {
	private final List<InputEvent> queue = new ArrayList<InputEvent>();
	private byte[] lock = new byte[0];

	public void tick() {
		synchronized (lock) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				InputEvent event = queue.get(i);
				Game.instance.uiManager.dispatch(event);
				Game.instance.poolService.recycle(event);
			}
			queue.clear();
		}
	}

	public InputEvent newEvent() {
		return Game.instance.poolService.get(InputEvent.class);
	}

	public void addToQueue(InputEvent event) {
		synchronized (lock) {
			queue.add(event);
		}
	}

}
