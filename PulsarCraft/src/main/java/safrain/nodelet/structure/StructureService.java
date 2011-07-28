package safrain.nodelet.structure;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * 提供对象结构的描述服务（描述、组织存放各个Field的信息）
 * 
 * @author safrain
 * 
 */
public class StructureService {

	/**
	 * 如果取一个没有注册的结构，是否自动注册
	 */
	public boolean autoRegister = true;

	public final HashMap<Class<?>, Structure> structureMap = new HashMap<Class<?>, Structure>();

	public Structure register(Class<?> clazz) {
		Structure structure = structureMap.get(clazz);
		if (structure != null) {
			throw new RuntimeException("Type " + clazz.getName()
					+ " already registed.");
		}
		structure = new Structure();
		structure.type = clazz;
		Class<?> currentType = clazz;
		// 取各个属性的反射信息，包括所有父类的
		while (currentType != null) {
			for (Field field : currentType.getDeclaredFields()) {
				FieldInfo fieldInfo = new FieldInfo();
				fieldInfo.type = field.getType();
				fieldInfo.declaringType = field.getDeclaringClass();
				fieldInfo.field = field;
				fieldInfo.fieldName = field.getName();
				fieldInfo.fieldType = FieldType.getFieldType(field);
				for (Annotation annotation : field.getAnnotations()) {
					fieldInfo.annotationMap.put(annotation.annotationType(),
							annotation);
				}
				structure.fieldMap.put(field.getName(), fieldInfo);
			}
			currentType = currentType.getSuperclass();
		}
		structureMap.put(clazz, structure);
		return structure;
	}

	public Structure getStructure(Class<?> clazz) {
		Structure structure = structureMap.get(clazz);
		if (structure == null) {
			if (autoRegister) {
				structure = register(clazz);
			} else {
				throw new RuntimeException();
			}
		}
		return structure;
	}
}
