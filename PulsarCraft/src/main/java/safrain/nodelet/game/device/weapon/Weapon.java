package safrain.nodelet.game.device.weapon;

import safrain.nodelet.game.Game;
import safrain.nodelet.game.bullet.Bullet;
import safrain.nodelet.game.device.Device;

public abstract class Weapon extends Device {
	public Bullet.Builder<?> bulletBuilder;

	@Override
	public void doInvoke() {
		Bullet bullet = getBullet();
		bullet.player = ship.player;
		Game.instance.addBullet(bullet);
	}

	protected Bullet getBullet() {
		Bullet bullet = bulletBuilder.build();
		bullet.position.copyAbsoulte(position);
		bullet.mover.turnOn();
		return bullet;
	}

	public static abstract class Builder<T extends Weapon> extends
			Device.Builder<T> {
		public Bullet.Builder<?> bullet;

		@Override
		public void fill(T t) {
			super.fill(t);
			t.bulletBuilder = bullet;
		}

	}
}
