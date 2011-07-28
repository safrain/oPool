package safrain.nodelet.components.renderable;

import safrain.nodelet.components.position.Position;
import safrain.nodelet.factory.BaseBuilder;
import safrain.nodelet.game.Game;
import safrain.nodelet.graphic.renderop.RenderOp;
import safrain.nodelet.pool.Reset;

public abstract class Renderable {
	@Reset
	public Position position;

	/**
	 * 获得绘图指令
	 */
	protected abstract RenderOp getRenderOp();

	public void tick() {
		Game.instance.graphicService.post(getRenderOp());
	}

	public abstract static class Builder<T extends Renderable> extends
			BaseBuilder<T> {
	}
}
