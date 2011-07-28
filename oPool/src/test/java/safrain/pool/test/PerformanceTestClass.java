package safrain.pool.test;

import safrain.pool.pool.OPool;
import safrain.pool.reseter.CustomObjectReseter;
import safrain.pool.reseter.annotation.OPoolSetNull;

public class PerformanceTestClass {
	@OPoolSetNull
	private int i1;
	@OPoolSetNull
	private int i2;
	@OPoolSetNull
	private int i3;
	@OPoolSetNull
	private int i4;
	@OPoolSetNull
	private int i5;

	public static class Reseter extends
			CustomObjectReseter<PerformanceTestClass> {

		public Reseter(OPool oPool) {
			super(oPool);
		}

		@Override
		public void reset(PerformanceTestClass t) {
			t.i1 = 0;
			t.i2 = 0;
			t.i3 = 0;
			t.i4 = 0;
			t.i5 = 0;
		}

	}

	public int getI1() {
		return i1;
	}

	public void setI1(int i1) {
		this.i1 = i1;
	}

	public int getI2() {
		return i2;
	}

	public void setI2(int i2) {
		this.i2 = i2;
	}

	public int getI3() {
		return i3;
	}

	public void setI3(int i3) {
		this.i3 = i3;
	}

	public int getI4() {
		return i4;
	}

	public void setI4(int i4) {
		this.i4 = i4;
	}

	public int getI5() {
		return i5;
	}

	public void setI5(int i5) {
		this.i5 = i5;
	}
}
