package safrain.nodelet.structure.fieldsetter;

public class DoubleField extends AbstractValueField {
	public double value;

	@Override
	protected void set0(Object obj) throws Exception {
		field.setDouble(obj, value);
	}

	@Override
	public void setValue(Object value) {
		this.value = (Double) value;

	}

}