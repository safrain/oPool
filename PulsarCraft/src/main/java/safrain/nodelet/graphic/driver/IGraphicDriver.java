package safrain.nodelet.graphic.driver;

import safrain.nodelet.graphic.BitmapResourceManager;
import safrain.nodelet.graphic.DebugInfo;
import safrain.nodelet.graphic.renderop.RenderOp;

public interface IGraphicDriver {

	BitmapResourceManager getBitmapResourceManager();

	int registerBitmapResource(String address);

	void process(RenderOp renderOp);

	void processDebugInfo(DebugInfo debugInfo);

	void startRender();

	void endRender();

}
