package safrain.nodelet.components.ui;

import safrain.nodelet.components.position.Position;
import safrain.nodelet.components.renderable.Bitmap;
import safrain.nodelet.components.renderable.Renderable;
import safrain.nodelet.factory.BaseBuilder;
import safrain.nodelet.input.IInputListener;
import safrain.nodelet.pool.Reset;

public class UI implements IInputListener {
	@Reset
	public Position position;
	@Reset
	public double width;
	@Reset
	public double height;
	@Reset
	public Renderable renderable;
	/**
	 * 层级？
	 */
	@Reset
	public int layer;

	public void tick() {
		if (renderable != null) {
			renderable.tick();
		}
	}

	@Override
	public void onTap(double x, double y) {
	}

	@Override
	public void onDown(double x, double y) {
	}

	@Override
	public void onUp(double x, double y) {
	}

	@Override
	public void onDrag(double x, double y, double startX, double startY) {
	}

	@Override
	public Object onDrop(double x, double y) {
		return null;
	}

	@Override
	public void onDropRejected(Object dropObject, double x, double y) {
	}

	@Override
	public boolean acceptDrop(Object dropObject, double x, double y) {
		return false;
	}

	public static class Builder<T extends UI> extends BaseBuilder<T> {
		public Position.Builder position;
		public double width;
		public double height;
		public Bitmap.Builder renderable;
		public boolean canDrag;
		public int layer;

		@Override
		public void fill(T t) {
			t.position = position.build();
			t.width = width;
			t.height = height;
			if (renderable != null) {
				t.renderable = renderable.build();
				t.renderable.position = t.position;
			}
			t.layer = layer;

		}

	}
}
