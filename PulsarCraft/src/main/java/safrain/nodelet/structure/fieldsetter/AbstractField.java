package safrain.nodelet.structure.fieldsetter;

import safrain.nodelet.structure.FieldInfo;

public abstract class AbstractField {
	public FieldInfo fieldInfo;

	public abstract void set(Object obj);

}
