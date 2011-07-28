var Mover = newBuilder({
	javaType : 'safrain.nodelet.components.mover.Mover',
	filler : function(b, p) {
		b.speed = value(p.speed, 0);
		b.acceleration = value(p.acceleration, 0);
		b.maxSpeed = value(p.maxSpeed, 0);
		b.syncAngle = value(p.syncAngle, true);
		b.angle = value(p.angle, 0);
	}
});
