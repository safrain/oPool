package safrain.nodelet.graphic;

public class DebugInfo {
	public double checkingFps;
	public double fps;
	public long lastCheckTime;

	public void addFrame() {
		checkingFps += 1;
		long currentTime = System.currentTimeMillis();
		long delta = currentTime - lastCheckTime;
		if (delta >= 1000) {// 一秒计算一次FPS
			fps = checkingFps * 1000 / delta;
			checkingFps = 0D;
			lastCheckTime = currentTime;
		}
	}
}
