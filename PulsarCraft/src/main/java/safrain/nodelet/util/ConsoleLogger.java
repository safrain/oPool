package safrain.nodelet.util;

public class ConsoleLogger implements ILog {

	@Override
	public void debug(String format, Object... objects) {
		System.out.println(String.format(format, objects));
	}

}
