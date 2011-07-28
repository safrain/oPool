package safrain.nodelet.game;

import safrain.nodelet.game.bullet.BulletManager;
import safrain.nodelet.game.ship.ShipManager;

public class Player {
	public ShipManager shipManager = new ShipManager();
	public BulletManager bulletManager = new BulletManager();
}
