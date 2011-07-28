package safrain.nodelet.structure.fieldsetter;

import java.util.List;

public class ListField extends AbstractValueField {

	@Override
	protected void set0(Object obj) throws Exception {
		@SuppressWarnings("rawtypes")
		List list = (List) obj;
		list.clear();
	}

	@Override
	public void setValue(Object value) {
	}

}
