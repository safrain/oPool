var UI = newBuilder({
	javaType : 'safrain.nodelet.components.ui.UI',
	filler : function(b, p) {
		b.position = value(p.position, Position());
		b.width = value(p.width, 0);
		b.width = value(p.height, 0);
		b.renderable = value(p.renderable, null);
		b.layer = value(p.layer, 0);
	}
});
var ControlUI = newBuilder({
	javaType : 'safrain.nodelet.components.ui.ControlUI',
	filler : function(b, p) {
		UI.fill(b, p);
	}
});