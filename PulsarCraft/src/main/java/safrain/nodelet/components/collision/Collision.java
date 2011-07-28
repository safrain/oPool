package safrain.nodelet.components.collision;

import safrain.nodelet.components.position.Position;
import safrain.nodelet.factory.BaseBuilder;
import safrain.nodelet.game.Game;
import safrain.nodelet.pool.Reset;

public abstract class Collision {
	@Reset
	public Position position;

	public boolean collisionCheck(Collision node, Position result) {
		return Game.instance.collisionService.hitCheck(this, node, result);
	}

	public static abstract class Builder<T extends Collision> extends
			BaseBuilder<T> {
		public Position.Builder position;

		@Override
		public void fill(T t) {
			t.position = position.build();
		}

	}
}
