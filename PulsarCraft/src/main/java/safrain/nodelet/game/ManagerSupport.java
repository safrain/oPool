package safrain.nodelet.game;

import java.util.ArrayList;
import java.util.List;

public abstract class ManagerSupport<T> {
	private final List<T> objects = new ArrayList<T>();

	private final List<T> toRemove = new ArrayList<T>();

	public List<T> getObjects() {
		return objects;
	}

	public void addObject(T t) {
		objects.add(t);
	}

	public int getObjectCount() {
		return objects.size();
	}

	public void tick() {
		int size = objects.size();
		for (int i = 0; i < size; i++) {
			T t = objects.get(i);
			if (!isToRemove(t)) {
				onTick(t);
			} else {
				onRemove(t);
				toRemove.add(t);
			}
		}
		if (toRemove.size() > 0) {
			objects.removeAll(toRemove);
		}
		toRemove.clear();
	}

	protected abstract boolean isToRemove(T t);

	protected void onTick(T t) {
	}

	protected void onRemove(T t) {
	}
}
