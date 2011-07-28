package safrain.nodelet.game.device.invoker;

import safrain.nodelet.pool.Reset;

/**
 * 被动，一只有效的
 */
public class PassiveInvoker extends Invoker {
	@Reset
	public boolean canInvoke;

	@Override
	public boolean canInvoke() {
		return canInvoke;
	}

	@Override
	public void invoke() {
	}

	public static class Builder extends Invoker.Builder<PassiveInvoker> {
		public boolean canInvoke;

		@Override
		public void fill(PassiveInvoker t) {
			t.canInvoke = canInvoke;
		}

	}

}
