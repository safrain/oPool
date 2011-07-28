package safrain.nodelet.game.bullet;

import safrain.nodelet.components.collision.Collision;
import safrain.nodelet.components.mover.Mover;
import safrain.nodelet.components.position.Position;
import safrain.nodelet.components.renderable.Bitmap;
import safrain.nodelet.factory.BaseBuilder;
import safrain.nodelet.game.Game;
import safrain.nodelet.game.device.Device;
import safrain.nodelet.game.fx.FX;
import safrain.nodelet.game.ship.Ship;
import safrain.nodelet.pool.Reset;

public abstract class Bullet {
	@Reset
	public Position position;
	@Reset
	public Collision collision;
	@Reset
	public Bitmap renderable;
	@Reset
	public Mover mover;
	@Reset
	public double damage;
	@Reset
	public FX.Builder hitFXBuilder;
	@Reset
	public boolean isDestroyed;
	@Reset
	public int player;

	public void hitCheck(Ship ship) {
		Position hitPosition = Game.instance.poolService.get(Position.class);
		int size = ship.devices.size();
		for (int i = 0; i < size; i++) {
			Device device = ship.devices.get(i);
			if (device.hitSequence == ship.hitLevel && device.hp > 0) {
				if (collision.collisionCheck(device.collision, hitPosition)) {
					onHit(device, hitPosition);
				}
			}
		}
		Game.instance.poolService.recycle(hitPosition);
	}

	public void onHit(Device device, Position hitPosition) {
		device.hp -= damage;
		if (hitFXBuilder != null) {
			FX fx = hitFXBuilder.build();
			fx.position.parent = device.position;
			fx.position.setAbsolutePosition(hitPosition);
			Game.instance.addFX(fx);
		}
		isDestroyed = true;
	}

	public void tick() {
		renderable.tick();
		mover.tick();
		mover.proceedMove();
	}

	public static abstract class Builder<T extends Bullet> extends
			BaseBuilder<T> {
		public Position.Builder position;
		public Collision.Builder<?> collision;
		public Bitmap.Builder renderable;
		public Mover.Builder mover;
		public double damage;
		public FX.Builder hitFX;

		@Override
		public void fill(T t) {
			t.position = position.build();

			t.collision = collision.build();
			t.collision.position.parent = t.position;

			t.renderable = renderable.build();
			t.renderable.position = t.position;

			t.mover = mover.build();
			t.mover.position = t.position;

			t.damage = damage;

			t.hitFXBuilder = hitFX;
		}

	}
}
