package safrain.nodelet.script;

public abstract class ScriptAPI {
	protected ScriptService scriptService;

	protected abstract String getName();

	protected void beforeRegister() {
	}

	protected void afterRegister() {
	}
}
