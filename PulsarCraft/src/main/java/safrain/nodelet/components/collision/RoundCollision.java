package safrain.nodelet.components.collision;

import safrain.nodelet.pool.Reset;

public class RoundCollision extends Collision {
	@Reset
	public double radius;

	public static class Builder extends Collision.Builder<RoundCollision> {
		public double radius;

		@Override
		public void fill(RoundCollision t) {
			super.fill(t);
			t.radius = radius;
		}

	}

}
