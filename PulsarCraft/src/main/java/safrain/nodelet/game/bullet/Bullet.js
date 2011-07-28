var Bullet = {
	fill : function(b, p) {
		b.position = value(p.position, Position());
		b.collision = value(p.collision, null);
		b.renderable = value(p.renderable, null);
		b.hitFX = value(p.hitFX, null);
		b.mover = value(p.mover, null);
		b.damage = value(p.damage, 0);
	}
}

var SimpleBullet = newBuilder({
	javaType : 'safrain.nodelet.game.bullet.SimpleBullet',
	filler : function(b, p) {
		Bullet.fill(b, p);
	}
});