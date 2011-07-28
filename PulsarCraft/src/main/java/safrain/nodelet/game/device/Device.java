package safrain.nodelet.game.device;

import safrain.nodelet.components.collision.Collision;
import safrain.nodelet.components.position.Position;
import safrain.nodelet.components.renderable.Bitmap;
import safrain.nodelet.components.renderable.Renderable;
import safrain.nodelet.factory.BaseBuilder;
import safrain.nodelet.game.device.invoker.Invoker;
import safrain.nodelet.game.ship.Ship;
import safrain.nodelet.pool.Reset;

/**
 * @author safrain
 * 
 */
public abstract class Device {
	/**
	 * 在哪艘飞船上
	 */
	@Reset
	public Ship ship;
	/**
	 * 在飞船上的位置
	 */
	@Reset
	public Position position;
	/**
	 * 普通状态的显示
	 */
	@Reset
	public Bitmap renderable;
	/**
	 * 摧毁状态的显示
	 */
	@Reset
	public Bitmap destroyedRenderable;

	@Reset
	public Collision collision;
	@Reset
	public Invoker invoker;
	/**
	 * 生命值
	 */
	@Reset
	public double hp;
	/**
	 * 击中顺序
	 */
	@Reset
	public int hitSequence;

	/**
	 * 是否自动启动
	 */
	@Reset
	public boolean isAuto;

	public void invoke() {
		if (invoker.canInvoke()) {
			doInvoke();
			invoker.invoke();
		}
	}

	public abstract void doInvoke();

	public void tick() {
		invoker.tick();
		if (isAuto) {
			invoke();
		}
		Renderable toRender;
		if (hp > 0) {
			toRender = renderable;
		} else {
			toRender = destroyedRenderable;
		}
		if (toRender != null) {
			toRender.tick();
		}
	}

	public static abstract class Builder<T extends Device> extends
			BaseBuilder<T> {

		public Position.Builder position;
		public Bitmap.Builder renderable;
		public Bitmap.Builder destroyRenderable;
		public Collision.Builder<?> collision;
		public Invoker.Builder<?> invoker;

		public double hp;
		public int hitSequence;

		public boolean isAuto;

		@Override
		public void fill(T t) {
			t.position = position.build();

			if (renderable != null) {
				t.renderable = renderable.build();
				t.renderable.position = t.position;
			}
			if (destroyRenderable != null) {
				t.destroyedRenderable = destroyRenderable.build();
				t.destroyedRenderable.position = t.position;
			}
			if (collision != null) {
				t.collision = collision.build();
				t.collision.position = t.position;
			}
			t.invoker = invoker.build();
			t.hp = hp;
			t.hitSequence = hitSequence;

			t.isAuto = isAuto;

		}

	}
}