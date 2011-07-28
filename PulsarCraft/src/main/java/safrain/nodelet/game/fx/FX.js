var FX = newBuilder({
	javaType : 'safrain.nodelet.game.fx.FX',
	filler : function(b, p) {
		b.position = value(p.position, Position());
		b.renderable = value(p.renderable, null);
	}
});