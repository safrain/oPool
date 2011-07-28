package safrain.nodelet.game;

import java.util.List;

import safrain.nodelet.components.collision.CollisionService;
import safrain.nodelet.components.ui.UIManager;
import safrain.nodelet.event.EventListener;
import safrain.nodelet.factory.Factory;
import safrain.nodelet.game.bullet.Bullet;
import safrain.nodelet.game.fx.FX;
import safrain.nodelet.game.fx.FXManager;
import safrain.nodelet.game.ship.Ship;
import safrain.nodelet.graphic.GraphicService;
import safrain.nodelet.graphic.driver.awt.AWTDriver;
import safrain.nodelet.input.InputService;
import safrain.nodelet.pool.PoolService;
import safrain.nodelet.script.ScriptService;
import safrain.nodelet.script.js.common.CommonAPI;
import safrain.nodelet.script.js.context.GameAPI;
import safrain.nodelet.structure.StructureService;
import safrain.nodelet.tick.TickEvent;
import safrain.nodelet.tick.TickService;

public class Game {
	public static Game instance;
	public final StructureService structureService = new StructureService();
	public final PoolService poolService = new PoolService();
	public final GraphicService graphicService = new GraphicService();
	public final ScriptService scriptService = new ScriptService();
	public final TickService tickService = new TickService();
	public final TickService graphicTickService = new TickService();
	public final InputService inputService = new InputService();

	public final CollisionService collisionService = new CollisionService();

	public final Factory renderableFactory = new Factory();
	public final Factory gameFactory = new Factory();

	public final UIManager uiManager = new UIManager();
	public final FXManager fxManager = new FXManager();

	public final Player player = new Player();
	public final Player enemy = new Player();

	public final HitManager hitManager = new HitManager();

	public void init() {
		graphicTickService.eventSource.addListener(graphicService.tickListener);
		tickService.eventSource.addListener(tickListener);

		AWTDriver driver = new AWTDriver();
		driver.init(400, 400);
		graphicService.driver = driver;

		scriptService.registerScriptAPI(new CommonAPI());
		scriptService.registerScriptAPI(new GameAPI(this));
	}

	public void start() {

		graphicTickService.start();
		tickService.start();
	}

	public void addFX(FX fx) {
		fxManager.addObject(fx);
	}

	public void addShip(Ship ship) {
		if (ship.player == 1) {
			this.player.shipManager.addObject(ship);
		} else if (ship.player == 2) {
			this.enemy.shipManager.addObject(ship);
		} else {
			throw new RuntimeException();
		}
	}

	public void addBullet(Bullet bullet) {
		if (bullet.player == 1) {
			this.player.bulletManager.addObject(bullet);
		} else if (bullet.player == 2) {
			this.enemy.bulletManager.addObject(bullet);
		} else {
			throw new RuntimeException();
		}
	}

	public final EventListener<TickEvent> tickListener = new EventListener<TickEvent>() {
		@Override
		public void onEvent(TickEvent event) {
			graphicService.frameStart();
			tick();
			graphicService.frameEnd();
		}
	};

	public void tick() {
		inputService.tick();
		uiManager.tick();
		hitManager.tick();
		player.shipManager.tick();
		enemy.shipManager.tick();
		player.bulletManager.tick();
		enemy.bulletManager.tick();
		fxManager.tick();
	}

	public class HitManager {
		public void tick() {
			processHit(player.bulletManager.getObjects(),
					enemy.shipManager.getObjects());
			processHit(enemy.bulletManager.getObjects(),
					player.shipManager.getObjects());
		}

		private void processHit(List<Bullet> bulletList, List<Ship> shipList) {
			int bulletSize = bulletList.size();
			int shipSize = shipList.size();
			for (int i = 0; i < bulletSize; i++) {
				Bullet bullet = bulletList.get(i);
				shipLoop: for (int j = 0; j < shipSize; j++) {
					Ship ship = shipList.get(j);
					bullet.hitCheck(ship);
					if (bullet.isDestroyed) {
						break shipLoop;
					}
				}
			}
		}
	}
}
