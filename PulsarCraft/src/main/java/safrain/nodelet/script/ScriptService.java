package safrain.nodelet.script;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class ScriptService {
	public Scriptable scope;
	public Context context;

	public ScriptService() {
		// android 平台不能开优化 否则报错 因为dalvik和jvm class
		// format不同，没法执行生成的字节码，所以在游戏过程中要尽量减少脚本的执行
		this(-1);
	}

	public ScriptService(int optimization) {
		context = Context.enter();
		context.setOptimizationLevel(optimization);
		scope = context.initStandardObjects();
	}

	public void registerScriptAPI(ScriptAPI scriptAPI) {
		scriptAPI.scriptService = this;
		scriptAPI.beforeRegister();
		ScriptableObject.putProperty(scope, scriptAPI.getName(),
				Context.javaToJS(scriptAPI, scope));
		scriptAPI.afterRegister();
	}

	@SuppressWarnings("unchecked")
	public <T> T getVariable(String name, Class<T> desiredType) {
		return (T) Context.jsToJava(ScriptableObject.getProperty(scope, name),
				desiredType);
	}

	public Object runScript(String name, String source) {
		try {
			return runScript(compileScript(name, source));
		} catch (RhinoException e) {
			System.out.println("Error while running script: " + name
					+ " at line " + e.lineNumber());
			System.out.println(e.getMessage());
			System.out.println(e.getScriptStackTrace());
			throw e;
		} catch (Exception e2) {
			System.out.println("Error while running script: " + name);
			throw new RuntimeException(e2);
		}
	}

	public Object runScript(String path) {
		return runScript(path, loadScript(path));
	}

	private Object runScript(Script script) {
		return script.exec(context, scope);
	}

	private Script compileScript(String name, String source) {
		return context.compileString(source, name, 0, null);
	}

	private static String loadScript(String path) {
		return loadScript(path, "utf-8");
	}

	private static String loadScript(String path, String charSet) {
		InputStream in = ScriptService.class.getResourceAsStream(path);
		try {
			int readed;
			byte[] b = new byte[8192];
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			while ((readed = in.read(b)) > 0) {
				bos.write(b, 0, readed);
			}
			return new String(bos.toByteArray(), "utf-8");
		} catch (Exception e) {
			throw new RuntimeException("Load script failed: " + path);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
