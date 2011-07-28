var common = api_common;
var _using_processed = {};
var using = function(path) {
	if (_using_processed[path] != true) {
		common.runScript(path);
		_using_processed[path] = true;
	}
}

var isString = function(obj) {
	return typeof (obj) == 'string';
}
var isArray = function(obj) {
	return toString.apply(obj) === '[object Array]';
}
var isDefined = function(obj) {
	return typeof (obj) != 'undefined';
}
var value = function(v, defaultValue) {
	if (isDefined(v)) {
		return v;
	} else {
		return defaultValue;
	}
};
var fillJavaList = function(jsList, javaList) {
	for (x in jsList) {
		javaList.add(jsList[x]);
	}
}
var fillJavaMap = function(jsMap, javaMap) {
	for (x in jsMap) {
		javaMap.put(x, jsMap[x]);
	}
}
var toJavaList = function(jsList) {
	var javaList = common.newList();
	fillJavaList(jsList, javaList);
	return javaList;
}
var toJavaMap = function(jsMap) {
	var javaMap = common.newMap();
	fillJavaMap(jsMap, javaMap);
	return javaMap;
}