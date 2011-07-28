package safrain.nodelet.game.bullet;

import safrain.nodelet.game.ManagerSupport;

public class BulletManager extends ManagerSupport<Bullet> {

	@Override
	protected boolean isToRemove(Bullet t) {
		return t.isDestroyed;
	}

	@Override
	protected void onTick(Bullet t) {
		t.tick();

	}

	@Override
	protected void onRemove(Bullet t) {

	}

}
