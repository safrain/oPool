package safrain.nodelet.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂类，缓存各种builder
 * 
 * @author safrain
 * 
 */
public class Factory {
	private final Map<String, BaseBuilder<?>> builderMap = new HashMap<String, BaseBuilder<?>>();

	public void addBuilder(String name, BaseBuilder<?> builder) {
		builderMap.put(name, builder);
	}

	public void removeBuilder(String name) {
		builderMap.remove(name);
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseBuilder<?>> T getBuilder(String name) {
		if (!builderMap.containsKey(name)) {
			throw new RuntimeException("Builder not registered:" + name);
		}
		return (T) builderMap.get(name);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> T build(String name) {
		BaseBuilder builder = getBuilder(name);
		return (T) builder.build();

	}
}
