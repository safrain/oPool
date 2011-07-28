var Renderable = {
	// 存放加载后位图的名称和ID
	bitmaps : {}
};
/**
 * 加载位图
 */
function loadBitmap(map) {
	for ( var x in map) {
		Renderable.bitmaps[x] = Game.graphicService.driver
				.registerBitmapResource(map[x]);
	}
}
var defaultBitmap = function(bitmapName) {
	return Bitmap({
		bitmap : bitmapName,
		centerPivot : true
	});
}
/**
 * Bitmap Builder
 */
var Bitmap = newBuilder({
	javaType : 'safrain.nodelet.components.renderable.Bitmap',
	filler : function(b, p) {
		b.bitmapId = Renderable.bitmaps[p.bitmap];
		b.zoomX = value(p.zoomX, 1);
		b.zoomY = value(p.zoomY, 1);
		b.alpha = value(p.alpha, 1);
		var size = Game.graphicService.driver.getBitmapResourceManager()
				.getBitmapSize(b.bitmapId);
		b.width = size[0];
		b.height = size[1];
		if (p.centerPivot == true) {
			b.pivotX = b.width / 2;
			b.pivotY = b.height / 2;
		} else {
			b.pivotX = value(p.pivotX, 0);
			b.pivotY = value(p.pivotY, 0);
		}
	}
});
/**
 * Animate Builder
 */
var Animate = newBuilder({
	javaType : 'safrain.nodelet.components.renderable.Bitmap',
	filler : function(b, p) {
		Bitmap.fill(b, p);
		b.isAnimate = true;
		b.loop = value(p.loop, false);
		b.time = value(p.time, -1);// -1表示跟framecount一样
		b.frameHeight = p.frameHeight;
		if (p.centerPivot == true) {
			b.pivotX = b.width / 2;
			b.pivotY = p.frameHeight / 2;
		}
	}
});