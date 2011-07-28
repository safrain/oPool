package safrain.nodelet.game.radar;

import java.util.List;

import safrain.nodelet.factory.BaseBuilder;
import safrain.nodelet.game.ship.Ship;

public abstract class Radar {

	public abstract Ship getSingleTarget();

	public abstract List<Ship> getMultiTarget();

	public static abstract class Builder extends BaseBuilder<Radar> {

	}

}
