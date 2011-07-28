package safrain.nodelet.structure;

import java.lang.annotation.Annotation;
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
public class Structure {
	/**
	 * 缓存的哪个类型
	 */
	public Class<?> type;

	/**
	 * 属性名与属性
	 */
	public final Map<String, FieldInfo> fieldMap = new HashMap<String, FieldInfo>();

	/**
	 * 得到某种annotation的所有Field信息
	 */
	public List<FieldInfo> getFieldInfoList(
			Class<? extends Annotation> annotationType) {
		List<FieldInfo> fieldInfoList = new ArrayList<FieldInfo>();
		for (FieldInfo fieldInfo : fieldMap.values()) {
			if (fieldInfo.getAnnotation(annotationType) != null) {
				fieldInfoList.add(fieldInfo);
			}
		}
		return fieldInfoList;
	}

	public FieldInfo getFieldInfo(String fieldName) {
		return fieldMap.get(fieldName);
	}

}
