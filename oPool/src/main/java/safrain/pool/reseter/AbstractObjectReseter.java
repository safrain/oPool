package safrain.pool.reseter;

import java.lang.reflect.Field;

public abstract class AbstractObjectReseter implements IObjectReseter,
		IFieldReseter {
	protected Field field;

	@Override
	public void resetField(Object object) {
		try {
			Object fieldValue = field.get(object);
			if (fieldValue != null) {
				resetObject(fieldValue);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public abstract void resetObject(Object object);
}
