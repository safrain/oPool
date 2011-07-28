package safrain.nodelet.components.collision;

import safrain.nodelet.components.position.Position;
import safrain.nodelet.game.Game;

public class CollisionService {

	public boolean hitCheck(Collision node1, Collision node2, Position result) {
		if (node1 instanceof RoundCollision) {
			if (node2 instanceof RoundCollision) {
				return collisionCheckRound2Round((RoundCollision) node1,
						(RoundCollision) node2, result);
			}
		}
		return false;
	}

	/**
	 * 检测两个圆形是否碰撞，如果碰撞则返回true并设置result为碰撞点的绝对坐标
	 */
	private boolean collisionCheckRound2Round(RoundCollision round1,
			RoundCollision round2, Position result) {
		Position p1 = Game.instance.poolService.get(Position.class);
		Position p2 = Game.instance.poolService.get(Position.class);
		round1.position.getAbsolutePosition(p1);
		round2.position.getAbsolutePosition(p2);
		double dx = p2.x - p1.x;
		double dy = p2.y - p1.y;
		double r = round1.radius + round2.radius;
		boolean hit;
		if (dx * dx + dy * dy <= r * r) {
			if (result != null) {
				result.set(p1.x + ((p2.x - p1.x)) * round1.radius
						/ round2.radius, p1.y + (p2.y - p1.y) * round1.radius
						/ round2.radius);
			}
			hit = true;
		} else {
			hit = false;
		}
		Game.instance.poolService.recycle(p1);
		Game.instance.poolService.recycle(p2);
		return hit;
	}
}
