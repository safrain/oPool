package safrain.pool.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这个类缓存一个类型内的属性信息，按照annotation的类型做分组存放
 * 
 * @author safrain
 * 
 */
public class ClassCache {
	/**
	 * 缓存的哪个类型
	 */
	public Class<?> type;

	/**
	 * 属性名与属性
	 */
	public final Map<String, FieldCache> fieldCacheMap = new HashMap<String, FieldCache>();

	/**
	 * 得到所有Field信息
	 */
	public List<FieldCache> getFieldCacheList() {
		List<FieldCache> fieldCacheList = new ArrayList<FieldCache>();
		for (FieldCache fieldInfo : fieldCacheMap.values()) {
			fieldCacheList.add(fieldInfo);
		}
		return fieldCacheList;
	}

	public FieldCache getFieldCache(String fieldName) {
		return fieldCacheMap.get(fieldName);
	}

}
