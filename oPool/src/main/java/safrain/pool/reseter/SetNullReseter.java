package safrain.pool.reseter;

import java.lang.reflect.Field;

public class SetNullReseter implements IFieldReseter {
	private PrimitiveFieldReseter reseter;

	@Override
	public void resetField(Object object) {
		reseter.resetField(object);
	}

	public SetNullReseter(Field field) {
		Class<?> fieldClass = field.getType();
		if (fieldClass.equals(Integer.TYPE)) {
			reseter = new IntReseter();
		} else if (fieldClass.equals(Long.TYPE)) {
			reseter = new LongReseter();
		} else if (fieldClass.equals(Byte.TYPE)) {
			reseter = new ByteReseter();
		} else if (fieldClass.equals(Character.TYPE)) {
			reseter = new CharReseter();
		} else if (fieldClass.equals(Boolean.TYPE)) {
			reseter = new BooleanReseter();
		} else if (fieldClass.equals(Short.TYPE)) {
			reseter = new ShortReseter();
		} else if (fieldClass.equals(Float.TYPE)) {
			reseter = new FloatReseter();
		} else if (fieldClass.equals(Double.TYPE)) {
			reseter = new DoubleReseter();
		} else {
			reseter = new ReferenceReseter();
		}
		reseter.field = field;
	}

	private static abstract class PrimitiveFieldReseter implements
			IFieldReseter {
		protected Field field;
	}

	private static class ByteReseter extends PrimitiveFieldReseter {

		@Override
		public void resetField(Object obj) {
			try {
				field.setByte(obj, (byte) 0);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static class BooleanReseter extends PrimitiveFieldReseter {
		@Override
		public void resetField(Object obj) {
			try {
				field.setBoolean(obj, false);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static class CharReseter extends PrimitiveFieldReseter {
		@Override
		public void resetField(Object obj) {
			try {
				field.setChar(obj, (char) 0);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static class DoubleReseter extends PrimitiveFieldReseter {
		@Override
		public void resetField(Object obj) {
			try {
				field.setDouble(obj, 0D);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static class FloatReseter extends PrimitiveFieldReseter {
		@Override
		public void resetField(Object obj) {
			try {
				field.setFloat(obj, 0f);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static class IntReseter extends PrimitiveFieldReseter {
		@Override
		public void resetField(Object obj) {
			try {
				field.setInt(obj, 0);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static class LongReseter extends PrimitiveFieldReseter {
		@Override
		public void resetField(Object obj) {
			try {
				field.setLong(obj, 0L);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static class ReferenceReseter extends PrimitiveFieldReseter {
		@Override
		public void resetField(Object obj) {
			try {
				field.set(obj, null);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static class ShortReseter extends PrimitiveFieldReseter {
		@Override
		public void resetField(Object obj) {
			try {
				field.setShort(obj, (short) 0);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
