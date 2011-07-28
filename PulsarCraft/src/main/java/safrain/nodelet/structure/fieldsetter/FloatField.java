package safrain.nodelet.structure.fieldsetter;

public class FloatField extends AbstractValueField {
	public float value;

	@Override
	protected void set0(Object obj) throws Exception {
		field.setFloat(obj, value);
	}

	@Override
	public void setValue(Object value) {
		this.value = (Float) value;

	}

}