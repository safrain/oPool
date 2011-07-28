var Ship = newBuilder({
	javaType : 'safrain.nodelet.game.ship.Ship',
	filler : function(b, p) {
		b.position = value(p.position, Position());
		b.mover = value(p.mover, Mover());
		if (isArray(p.devices)) {
			b.devices = toJavaList(p.devices);
		}
		b.destroyFX = value(p.destroyFX, null);
	}
});