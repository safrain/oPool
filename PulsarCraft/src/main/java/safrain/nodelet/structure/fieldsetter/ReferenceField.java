package safrain.nodelet.structure.fieldsetter;

public class ReferenceField extends AbstractValueField {
	public Object value;

	@Override
	protected void set0(Object obj) throws Exception {
		field.set(obj, value);
	}

	@Override
	public void setValue(Object value) {
		this.value = value;
	}

	public Object get(Object object) {
		try {
			return field.get(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}