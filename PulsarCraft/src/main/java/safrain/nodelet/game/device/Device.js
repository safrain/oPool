var Device = {
	fill : function(b, p) {
		b.position = value(p.position, Position());
		b.renderable = value(p.renderable, null);
		b.destroyRenderable = value(p.destroyRenderable, null);
		b.collision = value(p.collision, null);
		b.hitSequence = value(p.hitSequence, 0);
		b.invoker = value(p.invoker, PassiveInvoker(false));
		b.hp = value(p.hp, 0);
		b.isAuto = value(p.isAuto, false);
	}
};
var Weapon = {
	fill : function(b, p) {
		Device.fill(b, p);
		b.bullet = value(p.bullet, null);
	}
};
var Hull = newBuilder({
	javaType : 'safrain.nodelet.game.device.Hull',
	filler : function(b, p) {
		Device.fill(b, p);
	}
});
var SimpleWeapon = newBuilder({
	javaType : 'safrain.nodelet.game.device.weapon.SimpleWeapon',
	filler : function(b, p) {
		Weapon.fill(b, p);
	}
});
