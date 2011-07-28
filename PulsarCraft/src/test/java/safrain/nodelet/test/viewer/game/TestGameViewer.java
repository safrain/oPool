package safrain.nodelet.test.viewer.game;

import safrain.nodelet.components.ui.ControlUI;
import safrain.nodelet.components.ui.UI;
import safrain.nodelet.game.ship.Ship;
import safrain.nodelet.util.ClassUtil;

public class TestGameViewer extends BaseGameViewer {
	Ship ship1;
	Ship ship2;

	@Override
	public void beforeStart() {
		game.scriptService.runScript(ClassUtil.getAbsoluteClassPath(getClass())
				+ "/testGame.js");
		Ship.Builder shipBuilder = game.gameFactory.getBuilder("testShip");
		ship1 = shipBuilder.build();
		ship1.player = 1;
		System.out.println(ship1.position.angle);
		ship1.position.set(200, 200);
		game.addShip(ship1);
		ship2 = shipBuilder.build();
		ship2.player = 2;
		ship2.position.set(300, 100);
		game.addShip(ship2);

		UI.Builder<?> uib = game.gameFactory.getBuilder("testUI");
		UI ui = uib.build();
		System.out.println(ui);
		game.uiManager.addObject(ui);
		ship1.moveTo(30, 30);
		ControlUI.Builder controlUIBuilder = game.gameFactory
				.getBuilder("controlUI");
		ControlUI controlUI = controlUIBuilder.build();
		controlUI.ship = ship1;
		game.uiManager.addObject(controlUI);

	}

	public static void main(String[] args) {
		new TestGameViewer().start();
	}

}
