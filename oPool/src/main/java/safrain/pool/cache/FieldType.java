package safrain.pool.cache;

import java.lang.reflect.Field;

public enum FieldType {
	INT, LONG, BYTE, CHAR, BOOLEAN, SHORT, FLOAT, DOUBLE, REFERENCE;

	/**
	 * 分析属性的类型
	 */
	public static FieldType getFieldType(Field field) {
		Class<?> fieldClass = field.getType();
		if (fieldClass.equals(Integer.TYPE)) {
			return INT;
		} else if (fieldClass.equals(Long.TYPE)) {
			return LONG;
		} else if (fieldClass.equals(Byte.TYPE)) {
			return BYTE;
		} else if (fieldClass.equals(Character.TYPE)) {
			return CHAR;
		} else if (fieldClass.equals(Boolean.TYPE)) {
			return BOOLEAN;
		} else if (fieldClass.equals(Short.TYPE)) {
			return SHORT;
		} else if (fieldClass.equals(Float.TYPE)) {
			return FLOAT;
		} else if (fieldClass.equals(Double.TYPE)) {
			return DOUBLE;
		} else {
			return FieldType.REFERENCE;
		}
	}
}
