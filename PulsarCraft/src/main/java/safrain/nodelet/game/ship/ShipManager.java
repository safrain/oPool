package safrain.nodelet.game.ship;

import java.util.ArrayList;
import java.util.List;

import safrain.nodelet.game.Game;
import safrain.nodelet.game.ManagerSupport;
import safrain.nodelet.game.fx.FX;

public class ShipManager extends ManagerSupport<Ship> {

	public final List<Ship> ships = new ArrayList<Ship>();
	public final List<Ship> toRemove = new ArrayList<Ship>();

	@Override
	protected boolean isToRemove(Ship t) {
		return t.isDestroyed;
	}

	@Override
	protected void onTick(Ship t) {
		t.tick();
	}

	@Override
	protected void onRemove(Ship t) {
		if (t.destroyFXBuilder != null) {
			FX fx = t.destroyFXBuilder.build();
			fx.position = t.position;
			Game.instance.fxManager.addObject(fx);
		}
	}

}
