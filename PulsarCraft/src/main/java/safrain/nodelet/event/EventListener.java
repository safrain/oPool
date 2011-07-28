package safrain.nodelet.event;

public abstract class EventListener<E extends Event> implements IEventObject {
	EventSource<E> source;

	public abstract void onEvent(E event);

	void onSourceDestroy() {
		source = null;
	}

	@Override
	public void unHook() {
		if (source != null) {
			source.onListenerDestroy(this);
		}
	}
}