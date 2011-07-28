package safrain.nodelet.structure.fieldsetter;

public class LongField extends AbstractValueField {
	public long value;

	@Override
	protected void set0(Object obj) throws Exception {
		field.setLong(obj, value);
	}

	@Override
	public void setValue(Object value) {
		this.value = (Long) value;

	}

}