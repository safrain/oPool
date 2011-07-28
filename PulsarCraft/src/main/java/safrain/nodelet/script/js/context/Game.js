var Game = api_game.getGame();
/**
 * 这个方法是构建Builder函数的，根据传递进来的名字来生成一个builder函数
 */
function newBuilder(config) {
	var objTypeName = config.javaType;
	if (!isDefined(objTypeName) || objTypeName == null) {
		throw "Error while creating builder,type name is null";
	}
	var builderTypeName = objTypeName + '$Builder';
	if (!common.checkClassName(objTypeName)) {
		throw "Class :" + objTypeName + " not Found";
	}
	if (!common.checkClassName(builderTypeName)) {
		throw "Builder Class :" + builderTypeName + " not Found";
	}
	var func = function(param) {
		var obj = common.newObject(builderTypeName);
		obj.type = common.getClass(objTypeName);
		if (!isDefined(param)) {
			param = {};
		}
		func.fill(obj, param);
		return obj;
	}
	func.fill = config.filler;
	return func;
}
function batchAddBuilder(builderMap, factory) {
	for ( var x in builderMap) {
		factory.addBuilder(x, builderMap[x]);
	}
}
common.runScript('/safrain/nodelet/components/position/Position.js');
common.runScript('/safrain/nodelet/components/renderable/Renderable.js');
common.runScript('/safrain/nodelet/components/collision/Collision.js');
common.runScript('/safrain/nodelet/components/mover/Mover.js');
common.runScript('/safrain/nodelet/components/ui/UI.js');
common.runScript('/safrain/nodelet/game/fx/FX.js');
common.runScript('/safrain/nodelet/game/device/invoker/Invoker.js');
common.runScript('/safrain/nodelet/game/device/Device.js');
common.runScript('/safrain/nodelet/game/bullet/Bullet.js');
common.runScript('/safrain/nodelet/game/ship/Ship.js');