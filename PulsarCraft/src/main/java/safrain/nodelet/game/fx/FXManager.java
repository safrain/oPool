package safrain.nodelet.game.fx;

import safrain.nodelet.game.ManagerSupport;

public class FXManager extends ManagerSupport<FX> {

	@Override
	protected void onTick(FX t) {
		t.tick();
	}

	@Override
	protected void onRemove(FX t) {
	}

	@Override
	protected boolean isToRemove(FX t) {
		return t.isOver();
	}

}
