package safrain.nodelet.structure.fieldsetter;

public class IntField extends AbstractValueField {
	public int value;

	@Override
	protected void set0(Object obj) throws Exception {
		field.setInt(obj, value);
	}

	@Override
	public void setValue(Object value) {
		this.value = (Integer) value;
	}

}