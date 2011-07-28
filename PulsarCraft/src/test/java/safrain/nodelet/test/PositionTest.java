package safrain.nodelet.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import safrain.nodelet.components.position.Position;

public class PositionTest {
	Position p1;
	Position p2;
	Position p3;

	@Before
	public void setup() {
		p1 = new Position();
		p2 = new Position();
		p3 = new Position();
		p2.parent = p1;
		p3.parent = p2;

	}

	@Test
	public void testPosition() {

		p1.set(100, 100, 90);
		p2.set(100, 100, 90);
		p3.set(100, 100);

		Position result = new Position();
		p2.getAbsolutePosition(result);
		print(result);
		Assert.assertEquals(0d, result.x, 0.1d);
		Assert.assertEquals(200d, result.y, 0.1d);

		p3.getAbsolutePosition(result);
		print(result);
		Assert.assertEquals(-100d, result.x, 0.1d);
		Assert.assertEquals(100d, result.y, 0.1d);
		Position px = new Position();
		px.set(-100, 100, 0);

		p3.setAbsolutePosition(px);
		print(p3);
		Assert.assertEquals(100d, p3.x, 0.1d);
		Assert.assertEquals(100d, p3.y, 0.1d);

	}

	public void print(Position position) {
		System.out.println(position.x + " - " + position.y + " : "
				+ position.angle);
	}

}
