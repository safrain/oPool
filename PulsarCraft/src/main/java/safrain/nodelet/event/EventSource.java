package safrain.nodelet.event;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class EventSource<E extends Event> implements IEventObject {
	final List<EventListener> listeners = new ArrayList<EventListener>();

	public boolean hasListener() {
		return !listeners.isEmpty();
	}

	@SuppressWarnings("unchecked")
	public void dispatch(E event) {
		int size = listeners.size();
		for (int i = 0; i < size; i++) {
			listeners.get(i).onEvent(event);
		}
	}

	@SuppressWarnings("unchecked")
	public boolean addListener(EventListener listener) {
		if (listener.source != null)
			return false;
		boolean result = listeners.add(listener);
		if (result) {
			listener.source = this;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public boolean removeListener(EventListener listener) {
		if (listener.source != this)
			return false;
		boolean result = listeners.remove(listener);
		if (result) {
			listener.source = null;
		}
		return result;
	}

	@Override
	public void unHook() {
		int size = listeners.size();
		for (int i = 0; i < size; i++) {
			listeners.get(i).onSourceDestroy();
		}
	}

	void onListenerDestroy(EventListener listener) {
		removeListener(listener);
	}
}
