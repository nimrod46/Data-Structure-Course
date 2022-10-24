import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public abstract class MatrixTest<T> {

	private MatrixFactory<T> matrixFactory = new MatrixFactory<T>();
	private Matrix<T> matrix3;
	private T defaultVal;
	private T newInstance2;
	private T newInstance3;

	/**
	 * @return a new instance of parameter T. Two instances which are created by
	 *         this method should be different according to "equals" method. For
	 *         example for T=Object, the implementation can be "return new
	 *         Object();".
	 */
	public abstract T getParameterInstance();

	@Before
	public void setUp() throws Exception {
		defaultVal = getParameterInstance();
		newInstance2 = getParameterInstance();
		newInstance3 = getParameterInstance();

		matrix3 = matrixFactory.getMatrix(3, defaultVal);

		if ((defaultVal.equals(newInstance2)) || (defaultVal.equals(newInstance3))
				|| (newInstance3.equals(newInstance2))) {
			fail("new instances should be different");
		}
	}

	@Test
	public void testSparseMatrixSize() {
		assertEquals(defaultVal, matrix3.get(1, 2));
		assertEquals(defaultVal, matrix3.get(1, 3));
		assertEquals(defaultVal, matrix3.get(3, 2));
		try {
			matrix3.get(1, 4);
			fail("Should not work");
		} catch (Exception e) {
		}
		try {
			matrix3.get(0, 1);
			fail("Should not work");
		} catch (Exception e) {
		}

		assertEquals(defaultVal, matrix3.get(1, 2));
		assertEquals(defaultVal, matrix3.get(1, 3));
		assertEquals(defaultVal, matrix3.get(3, 2));
		try {
			matrix3.get(1, 4);
			fail("Should not work");
		} catch (Exception e) {
		}
		try {
			matrix3.get(0, 1);
			fail("Should not work");
		} catch (Exception e) {
		}

		Matrix<T> matrixDefSize = matrixFactory.getMatrix(defaultVal);
		assertEquals(defaultVal, matrixDefSize.get(1, 2));
		assertEquals(defaultVal, matrixDefSize.get(1, 3));
		assertEquals(defaultVal, matrixDefSize.get(100, 100));
		try {
			matrix3.get(101, 1);
			fail("Should not work");
		} catch (Exception e) {
		}
		try {
			matrix3.get(0, 1);
			fail("Should not work");
		} catch (Exception e) {
		}

	}

	// TODO add Test Methods

}
