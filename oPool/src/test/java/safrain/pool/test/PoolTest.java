package safrain.pool.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import safrain.pool.pool.OPool;
import safrain.pool.pool.Pool;
import safrain.pool.reseter.CustomObjectReseter;
import safrain.pool.reseter.annotation.OPoolClear;
import safrain.pool.reseter.annotation.OPoolCustom;
import safrain.pool.reseter.annotation.OPoolReset;
import safrain.pool.reseter.annotation.OPoolSetNull;

public class PoolTest {

	private OPool smartPool;

	@Before
	public void setup() {
		smartPool = new OPool();
	}

	@Test
	public void test() {
		smartPool.register(TestClass.class, 100);
		TestClass t = smartPool.get(TestClass.class);
		// SetNull
		t.intField = 10;
		// Reset
		t.objectField = new TestClass();
		t.objectField.intField = 100;
		// Custom
		t.objectField1 = new TestClass();
		t.objectField1.intField = 100;
		// List
		t.listField.add("hello");
		smartPool.recycle(t);
		Assert.assertEquals(0, t.intField);
		Assert.assertEquals(0, t.objectField.intField);
		Assert.assertEquals(0, t.objectField1.intField);
		Assert.assertEquals(0, t.listField.size());
	}

	private final int LOOP = 10000000;

	@Test
	public void performance() {
		long ta = timeAnno(new OPool());
		long tc = timeCustom(new OPool());
		System.out.println("TA:" + ta);
		System.out.println("TC:" + tc);

		System.out.println("RATIO:" + ta * 1.0f / tc);
	}

	/**
	 * @return time used while using AnnotationReseter
	 */
	public long timeAnno(OPool oPool) {
		Pool pool = oPool.getPool(PerformanceTestClass.class);
		long start = System.currentTimeMillis();
		for (int i = 0; i < LOOP; i++) {
			PerformanceTestClass t = (PerformanceTestClass) pool.get();
			t.setI1(100);
			t.setI2(100);
			t.setI3(100);
			t.setI4(100);
			t.setI5(100);
			pool.recycle(t);
		}

		return System.currentTimeMillis() - start;
	}

	/**
	 * @return time used while using CustomReseter
	 */
	public long timeCustom(OPool oPool) {
		oPool.register(PerformanceTestClass.class, 1000,
				new PerformanceTestClass.Reseter(oPool));
		Pool pool = oPool.getPool(PerformanceTestClass.class);
		long start = System.currentTimeMillis();
		for (int i = 0; i < LOOP; i++) {
			PerformanceTestClass t = (PerformanceTestClass) pool.get();
			t.setI1(100);
			t.setI2(100);
			t.setI3(100);
			t.setI4(100);
			t.setI5(100);
			pool.recycle(t);
		}
		return System.currentTimeMillis() - start;
	}

	@Test
	public void testSync() {
	}

	public static class TestClass {
		@OPoolSetNull
		private int intField;

		@OPoolReset
		private TestClass objectField;

		@OPoolCustom(reseter = TestClassReseter.class)
		private TestClass objectField1;

		@OPoolClear
		private List<String> listField = new ArrayList<String>();

	}

	public static class TestClassReseter extends CustomObjectReseter<TestClass> {

		public TestClassReseter(OPool oPool) {
			super(oPool);
		}

		@Override
		public void reset(TestClass t) {
			t.intField = 0;
			t.listField.clear();
			t.objectField = null;
		}

	}
}
