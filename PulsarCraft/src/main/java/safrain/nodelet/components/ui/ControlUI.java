package safrain.nodelet.components.ui;

import safrain.nodelet.game.ship.Ship;

public class ControlUI extends UI {
	public Ship ship;

	@Override
	public void onDrag(double x, double y, double startX, double startY) {
		ship.moveTo(x, y);
	}

	@Override
	public void onDown(double x, double y) {
		ship.moveTo(x, y);
	}

	public static class Builder extends UI.Builder<ControlUI> {

	}
}
