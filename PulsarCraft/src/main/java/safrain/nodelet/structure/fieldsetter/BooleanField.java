package safrain.nodelet.structure.fieldsetter;

public class BooleanField extends AbstractValueField {
	public boolean value;

	@Override
	protected void set0(Object obj) throws Exception {
		field.setBoolean(obj, value);
	}

	@Override
	public void setValue(Object value) {
		this.value = (Boolean) value;
	}

}