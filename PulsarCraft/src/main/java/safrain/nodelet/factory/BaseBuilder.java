package safrain.nodelet.factory;

import safrain.nodelet.game.Game;

public abstract class BaseBuilder<T> {
	public Class<T> type;

	public T build() {
		T t = createObject();
		fill(t);
		return t;
	}

	public abstract void fill(T t);

	private T createObject() {
		return Game.instance.poolService.get(type);
	}
}
