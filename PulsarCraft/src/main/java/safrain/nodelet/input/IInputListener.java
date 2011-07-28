package safrain.nodelet.input;

public interface IInputListener {
	/**
	 * 单击
	 */
	void onTap(double x, double y);

	/**
	 * 按下
	 */
	void onDown(double x, double y);

	/**
	 * 抬起
	 */
	void onUp(double x, double y);

	/**
	 * 拖动
	 */
	void onDrag(double x, double y, double startX, double startY);

	/**
	 * 拖放
	 */
	Object onDrop(double x, double y);

	/**
	 * 拖放被拒绝
	 */
	void onDropRejected(Object dropObject, double x, double y);

	/**
	 * 接受拖放
	 */
	boolean acceptDrop(Object dropObject, double x, double y);

}