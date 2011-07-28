package safrain.nodelet.game.fx;

import safrain.nodelet.components.position.Position;
import safrain.nodelet.components.renderable.Bitmap;
import safrain.nodelet.factory.BaseBuilder;
import safrain.nodelet.pool.Reset;

public class FX {
	@Reset
	public Position position;
	@Reset
	public Bitmap renderable;

	public boolean isOver() {
		return renderable.isOver;
	}

	public void tick() {
		renderable.tick();
	}

	public static class Builder extends BaseBuilder<FX> {
		public Position.Builder position;
		public Bitmap.Builder renderable;

		@Override
		public void fill(FX t) {
			t.position = position.build();
			t.renderable = renderable.build();
			t.renderable.position = t.position;
		}

	}

}
