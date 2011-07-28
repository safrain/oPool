package safrain.nodelet.game.device.invoker;

import safrain.nodelet.pool.Reset;

public class ChargeInvoker extends Invoker {
	// 满值是多少
	@Reset
	public double maxCharge;
	// 充电速度
	@Reset
	public double chargeRate;
	// 放电量
	@Reset
	public double consume;
	// 当前充了多少
	@Reset
	public double currentCharge;

	@Override
	public boolean canInvoke() {
		return currentCharge - consume >= 0;
	}

	@Override
	public void invoke() {
		currentCharge -= consume;
	}

	public void tick() {
		currentCharge += chargeRate;
		if (currentCharge > maxCharge) {
			currentCharge = maxCharge;
		}
	}

	public static class Builder extends Invoker.Builder<ChargeInvoker> {
		public double maxCharge;
		public double chargeRate;
		public double consume;
		public double currentCharge;

		@Override
		public void fill(ChargeInvoker t) {
			t.chargeRate = chargeRate;
			t.maxCharge = maxCharge;
			t.currentCharge = currentCharge;
			t.consume = consume;
		}

	}

}
