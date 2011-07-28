package safrain.nodelet.game.ship;

import java.util.ArrayList;
import java.util.List;

import safrain.nodelet.components.mover.Mover;
import safrain.nodelet.components.position.Position;
import safrain.nodelet.factory.BaseBuilder;
import safrain.nodelet.game.Game;
import safrain.nodelet.game.device.Device;
import safrain.nodelet.game.fx.FX;
import safrain.nodelet.pool.Reset;
import safrain.nodelet.util.MathUtil;

public class Ship {
	/**
	 * 飞船的位置
	 */
	@Reset
	public Position position;
	@Reset
	public Mover mover;
	/**
	 * 飞船的组件
	 */
	@Reset
	public final List<Device> devices = new ArrayList<Device>();

	@Reset
	public FX.Builder destroyFXBuilder;
	/**
	 * 当前打到哪个级别了
	 */
	@Reset
	public int hitLevel;
	/**
	 * 是否已经被摧毁
	 */
	@Reset
	public boolean isDestroyed;
	/**
	 * 属于哪个玩家
	 */
	@Reset
	public int player;

	// 移动目标

	public void moveTo(double x, double y) {
		moveTargetX = x;
		moveTargetY = y;
		hasMoveTarget = true;
	}

	public void tick() {
		int size = devices.size();
		for (int i = 0; i < size; i++) {
			Device device = devices.get(i);
			device.tick();
		}
		hitLevel = getMaxHitLevel();
		if (hitLevel == -1) {
			// BOOM
			isDestroyed = true;
			if (destroyFXBuilder != null) {
				Position absolute = Game.instance.poolService
						.get(Position.class);
				position.getAbsolutePosition(absolute);
				FX fx = destroyFXBuilder.build();
				fx.position.setAbsolutePosition(absolute);
				Game.instance.addFX(fx);
			}
		}

	}

	/**
	 * 返回飞船能被集中的级数，如果是-1，则说明全部被打爆
	 */
	private int getMaxHitLevel() {
		int size = devices.size();
		int max = -1;
		for (int i = 0; i < size; i++) {
			Device device = devices.get(i);
			if (device.hp > 0 && device.hitSequence > max) {
				max = device.hitSequence;
			}
		}
		return max;
	}

	public static class Builder extends BaseBuilder<Ship> {
		public Position.Builder position;
		public Mover.Builder mover;
		public List<Device.Builder<?>> devices;
		public FX.Builder destroyFX;

		@Override
		public void fill(Ship t) {
			t.position = position.build();
			t.mover = mover.build();
			if (devices != null) {
				int size = devices.size();
				for (int i = 0; i < size; i++) {
					Device device = devices.get(i).build();
					t.devices.add(device);
					device.ship = t;
					device.position.parent = t.position;
				}
			}
			t.destroyFXBuilder = destroyFX;

			t.mover.position = t.position;
		}

	}

}
