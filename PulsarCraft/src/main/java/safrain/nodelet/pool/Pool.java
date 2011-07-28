package safrain.nodelet.pool;

import java.util.ArrayList;
import java.util.List;

import safrain.nodelet.structure.FieldInfo;
import safrain.nodelet.structure.Structure;
import safrain.nodelet.structure.fieldsetter.AbstractValueField;

/**
 * 某一类型的对象池
 * 
 * @author safrain
 * 
 */
public class Pool {
	private final List<Object> objects = new ArrayList<Object>();
	private Class<?> type;
	private List<AbstractValueField> resetFields = new ArrayList<AbstractValueField>();
	private int poolSize;

	private byte[] lock = new byte[0];

	public Pool(Structure structure, int poolSize) {
		this.type = structure.type;
		for (FieldInfo fieldInfo : structure.getFieldInfoList(Reset.class)) {
			AbstractValueField valueField = AbstractValueField
					.getValueField(fieldInfo);
			switch (valueField.fieldInfo.fieldType) {
			case BOOLEAN:
				valueField.setValue(false);
				break;
			case INT:
				valueField.setValue(0);
				break;
			case BYTE:
				valueField.setValue((byte) 0);
				break;
			case SHORT:
				valueField.setValue((short) 0);
				break;
			case LONG:
				valueField.setValue(0L);
				break;
			case CHAR:
				valueField.setValue((char) 0);
				break;
			case DOUBLE:
				valueField.setValue(0D);
				break;
			case FLOAT:
				valueField.setValue(0F);
				break;
			case REFERENCE:
				valueField.setValue(null);
				break;
			}
			this.resetFields.add(valueField);
		}
		this.poolSize = poolSize;
	}

	public Object get() {
		if (!objects.isEmpty()) {
			Object poolObject = objects.remove(objects.size() - 1);
			return poolObject;
		}
		try {
			return type.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Object getSync() {
		synchronized (lock) {
			return get();
		}
	}

	public void recycle(Object poolObject) {
		if (objects.size() < poolSize) {
			resetObject(poolObject);
			objects.add(poolObject);
		}
	}

	public void recycleSync(Object poolObject) {
		synchronized (lock) {
			recycle(poolObject);
		}
	}

	private void resetObject(Object object) {
		int size = resetFields.size();
		for (int i = 0; i < size; i++) {
			AbstractValueField field = resetFields.get(i);
			field.set(object);
		}
	}
}