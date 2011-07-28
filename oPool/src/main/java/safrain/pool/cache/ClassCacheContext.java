package safrain.pool.cache;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;

public class ClassCacheContext {

	public final HashMap<Class<?>, ClassCache> classCacheMap = new HashMap<Class<?>, ClassCache>();

	private ClassCache register(Class<?> type) {
		ClassCache classCache = new ClassCache();
		classCache.type = type;
		Class<?> currentType = type;
		// 取各个属性的反射信息，包括所有父类的
		while (currentType != null) {
			for (Field field : currentType.getDeclaredFields()) {
				field.setAccessible(true);//
				FieldCache fieldCache = new FieldCache();
				fieldCache.type = field.getType();
				fieldCache.declaringType = field.getDeclaringClass();
				fieldCache.field = field;
				fieldCache.fieldName = field.getName();
				fieldCache.fieldType = FieldType.getFieldType(field);
				for (Annotation annotation : field.getAnnotations()) {
					fieldCache.annotationMap.put(annotation.annotationType(),
							annotation);
				}
				classCache.fieldCacheMap.put(fieldCache.fieldName, fieldCache);
			}
			currentType = currentType.getSuperclass();
		}
		classCacheMap.put(type, classCache);
		return classCache;
	}

	public ClassCache getClassCache(Class<?> type) {
		ClassCache classCache = classCacheMap.get(type);
		if (classCache == null) {
			classCache = register(type);
		}
		return classCache;
	}
}
