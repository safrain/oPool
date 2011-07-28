var Position = newBuilder({
	javaType : 'safrain.nodelet.components.position.Position',
	filler : function(b, p) {
		b.x = value(p.x, 0);
		b.y = value(p.y, 0);
		b.angle = value(p.angle, 0);
	}
});
