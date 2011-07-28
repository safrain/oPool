var Collision = {
	fill : function(b, p) {
		b.position = value(p.position, Position());
	}
};
var RoundCollision = newBuilder({
	javaType : 'safrain.nodelet.components.collision.RoundCollision',
	filler : function(b, p) {
		Collision.fill(b, p);
		b.radius = value(p.radius, 0);
	}
});
