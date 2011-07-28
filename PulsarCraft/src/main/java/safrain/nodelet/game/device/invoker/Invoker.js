var ChargeInvoker = newBuilder({
	javaType : 'safrain.nodelet.game.device.invoker.ChargeInvoker',
	filler : function(b, p) {
		b.maxCharge = value(p.maxCharge, 100);
		b.chargeRate = value(p.chargeRate, 0);
		b.currentCharge = value(p.currentCharge, 0);
		b.consume = value(p.consume, 100);
	}
});
var PassiveInvoker = newBuilder({
	javaType : 'safrain.nodelet.game.device.invoker.PassiveInvoker',
	filler : function(b, p) {
		b.canInvoke = value(p.canInvoke, false);
	}
});
