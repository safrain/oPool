package safrain.nodelet.structure.fieldsetter;

import java.awt.List;
import java.lang.reflect.Field;

import safrain.nodelet.structure.FieldInfo;
import safrain.nodelet.util.ClassUtil;

public abstract class AbstractValueField extends AbstractField {
	public FieldInfo fieldInfo;
	public Field field;

	@Override
	public void set(Object obj) {
		try {
			set0(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected abstract void set0(Object obj) throws Exception;

	public abstract void setValue(Object value);

	public static AbstractValueField getValueField(FieldInfo fieldInfo) {
		AbstractValueField valueField = null;
		switch (fieldInfo.fieldType) {
		case INT:
			valueField = new IntField();
			break;
		case LONG:
			valueField = new LongField();
			break;
		case BYTE:
			valueField = new ByteField();
			break;
		case CHAR:
			valueField = new CharField();
			break;
		case BOOLEAN:
			valueField = new BooleanField();
			break;
		case SHORT:
			valueField = new ShortField();
			break;
		case FLOAT:
			valueField = new FloatField();
			break;
		case DOUBLE:
			valueField = new DoubleField();
			break;
		case REFERENCE:
			if (ClassUtil.canAssign(List.class, fieldInfo.field.getType())) {

			}
			valueField = new ReferenceField();
			break;
		}
		valueField.fieldInfo = fieldInfo;
		valueField.field = fieldInfo.field;
		return valueField;
	}
}
