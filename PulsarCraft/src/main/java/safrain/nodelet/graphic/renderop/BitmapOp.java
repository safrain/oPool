package safrain.nodelet.graphic.renderop;

/**
 * 绘制位图的绘制指令
 * 
 * @author safrain
 * 
 */
public class BitmapOp extends RenderOp {
	/**
	 * Driver中注册的位图资源的编号，由于不同Driver所支持的位图资源的格式可能不同，所以统一的用int来标识
	 */
	public int bitmapId;

	/**
	 * 位置X
	 */
	public double x;

	/**
	 * 位置Y
	 */
	public double y;

	/**
	 * 旋转角度
	 */
	public double angle;
	/**
	 * 横向缩放
	 */
	public double zoomX;
	/**
	 * 纵向缩放
	 */
	public double zoomY;
	/**
	 * 图像透明度
	 */
	// TODO:是不是放在特效部分
	public double alpha;

	/**
	 * 用于旋转和缩放的锚点X
	 */
	public double pivotX;
	/**
	 * 用于旋转和缩放的锚点Y
	 */
	public double pivotY;

	@Override
	public int getTypeCode() {
		return 1;
	}
}
