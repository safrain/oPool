package safrain.pool.reseter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import safrain.pool.cache.FieldCache;
import safrain.pool.pool.OPool;
import safrain.pool.reseter.annotation.OPoolClear;
import safrain.pool.reseter.annotation.OPoolCustom;
import safrain.pool.reseter.annotation.OPoolReset;
import safrain.pool.reseter.annotation.OPoolSetNull;

public class AnnotationReseter extends AbstractObjectReseter {
	private final List<IFieldReseter> reseterList = new ArrayList<IFieldReseter>();

	@Override
	public void resetObject(Object object) {
		int size = reseterList.size();
		for (int i = 0; i < size; i++) {
			IFieldReseter reseter = reseterList.get(i);
			reseter.resetField(object);
		}
	}

	@Override
	public void resetField(Object object) {
		Object fieldValue;
		try {
			fieldValue = field.get(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		if (fieldValue != null) {
			resetObject(fieldValue);
		}
	}

	public AnnotationReseter(Field field, OPool oPool) {
		this.field = field;
		init(field.getType(), oPool);
	}

	public AnnotationReseter(Class<?> type, OPool oPool) {
		init(type, oPool);
	}

	private void init(Class<?> type, OPool oPool) {
		List<FieldCache> fieldCacheList = oPool.getClassCacheContext()
				.getClassCache(type).getFieldCacheList();
		for (FieldCache fieldCache : fieldCacheList) {
			IFieldReseter fieldReseter = null;
			OPoolSetNull setNull = fieldCache.getAnnotation(OPoolSetNull.class);
			if (setNull != null) {
				fieldReseter = new SetNullReseter(fieldCache.field);
			}

			OPoolReset reset = fieldCache.getAnnotation(OPoolReset.class);
			if (reset != null) {
				if (!(fieldCache.type.equals(type) && fieldCache.field
						.equals(field))) {
					fieldReseter = new AnnotationReseter(fieldCache.field,
							oPool);
				} else {
					fieldReseter = this;
				}
			}

			OPoolClear clear = fieldCache.getAnnotation(OPoolClear.class);
			if (clear != null) {
				fieldReseter = new ClearReseter(fieldCache.field);
			}

			OPoolCustom custom = fieldCache.getAnnotation(OPoolCustom.class);
			if (custom != null) {
				Class<? extends CustomObjectReseter<?>> customReseterType = custom
						.reseter();
				try {
					CustomObjectReseter<?> customObjectReseter = customReseterType
							.getConstructor(OPool.class).newInstance(oPool);
					customObjectReseter.field = fieldCache.field;
					fieldReseter = customObjectReseter;
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			reseterList.add(fieldReseter);
		}
	}
}
