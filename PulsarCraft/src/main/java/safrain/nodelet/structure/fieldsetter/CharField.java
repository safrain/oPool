package safrain.nodelet.structure.fieldsetter;

public class CharField extends AbstractValueField {
	public char value;

	@Override
	protected void set0(Object obj) throws Exception {
		field.setChar(obj, value);
	}

	@Override
	public void setValue(Object value) {
		this.value = (Character) value;

	}

}