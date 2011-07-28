package safrain.nodelet.graphic.renderop;

/**
 * 动画指令，若干帧被拼接为一个竖向的长条图片，绘制时剪切并绘制
 * 
 * @author safrain
 * 
 */
public class AnimateOp extends BitmapOp {

	/**
	 * 每一帧的高度，因为是竖向的条状，所以高度需要指定，宽度可以直接从源图取
	 */
	public double frameHeight;

	/**
	 * 当前播放的帧，根据这个计算出剪切的偏移
	 */
	public int currentFrame;

	// some extra:
	//frame length
	//time?
	//time frame covert
	
	@Override
	public int getTypeCode() {
		return 2;
	}

}
