package safrain.pool.reseter;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ClearReseter extends AbstractObjectReseter {
	private AbstractObjectReseter reseter;

	public ClearReseter(Field field) {
		this.field = field;
		init(field.getType());
		reseter.field = field;
	}

	public ClearReseter(Class<?> type) {
		init(type);
	}

	private void init(Class<?> type) {
		if (type.isAssignableFrom(List.class)) {
			reseter = new CollectionReseter();
		} else if (type.isAssignableFrom(Map.class)) {
			reseter = new MapReseter();
		} else {
			throw new RuntimeException();
		}
	}

	@Override
	public void resetObject(Object object) {
		reseter.resetObject(object);
	}

	public static class CollectionReseter extends AbstractObjectReseter {

		@SuppressWarnings("rawtypes")
		@Override
		public void resetObject(Object obj) {
			((Collection) obj).clear();
		}
	}

	public static class MapReseter extends AbstractObjectReseter {
		@SuppressWarnings("rawtypes")
		@Override
		public void resetObject(Object obj) {
			((Map) obj).clear();
		}
	}

}
