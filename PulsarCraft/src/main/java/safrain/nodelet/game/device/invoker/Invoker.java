package safrain.nodelet.game.device.invoker;

import safrain.nodelet.factory.BaseBuilder;

public abstract class Invoker {
	public abstract boolean canInvoke();

	public abstract void invoke();

	public void tick() {
	}

	public static abstract class Builder<T extends Invoker> extends
			BaseBuilder<T> {

	}
}
