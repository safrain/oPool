package safrain.pool.cache;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 包含Field的相关信息
 * 
 * @author safrain
 * 
 */
public class FieldCache {
	/**
	 * 在Structure中查找FieldInfo的时候需要根据annotation的类型来查找，这个对象就是Field上声明的Annotation实例
	 */
	public final Map<Class<? extends Annotation>, Annotation> annotationMap = new HashMap<Class<? extends Annotation>, Annotation>();

	/**
	 * 取得此Field上的某类型的Annotation
	 */
	@SuppressWarnings("unchecked")
	public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
		return (T) annotationMap.get(annotationType);

	}

	/**
	 * 描述的Field
	 */
	public Field field;

	/**
	 * Field的名字，存放一份，取值方便
	 */
	public String fieldName;

	/**
	 * Field的Class
	 */
	public Class<?> type;

	/**
	 * 在哪个类里面声明的
	 */
	public Class<?> declaringType;

	/**
	 * Field的类型，包括各种primivite类型和一个reference类型，这个变量是为了判断方便
	 */
	public FieldType fieldType;

}
