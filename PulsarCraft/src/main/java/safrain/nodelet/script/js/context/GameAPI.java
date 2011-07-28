package safrain.nodelet.script.js.context;

import safrain.nodelet.game.Game;
import safrain.nodelet.graphic.BitmapResourceManager;
import safrain.nodelet.graphic.driver.IGraphicDriver;
import safrain.nodelet.script.ScriptAPI;
import safrain.nodelet.util.ClassUtil;

public class GameAPI extends ScriptAPI {

	private Game game;

	public GameAPI(Game game) {
		this.game = game;
	}

	public IGraphicDriver getGraphicDriver() {
		return game.graphicService.driver;
	}

	public BitmapResourceManager getBitmapResourceManager() {
		return game.graphicService.driver.getBitmapResourceManager();
	}

	@Override
	protected String getName() {
		return "api_game";
	}

	public Game getGame() {
		return game;
	}

	@Override
	protected void afterRegister() {
		scriptService.runScript(ClassUtil.getAbsoluteClassPath(getClass())
				+ "/Game.js");
	}

}
