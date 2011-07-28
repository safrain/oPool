package safrain.nodelet.graphic.renderop;

/**
 * 供图像驱动程序处理的绘图指令
 * 
 * @author safrain
 * 
 */
public abstract class RenderOp {
	/**
	 * 用于绘制时快速区分Op类型，因为不同的Op之间可能会存在继承的关系，所以用instanceof会比较麻烦，用int判断比较方便
	 */
	public abstract int getTypeCode();
}
