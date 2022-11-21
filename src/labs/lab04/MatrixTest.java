package labs.lab04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class MatrixTest {

    private MatrixFactory<Integer> matrixFactory = new MatrixFactory<Integer>();
    private Matrix<Integer> matrix3;
    private Matrix<Integer> matrix6;
    private Integer defaultVal;
    private Integer newInstance2;
    private Integer newInstance3;
    private Integer newInstance4;
    private Integer newInstance5;
    private Integer defaultVal2;

    private int i = 0;

    public Integer getParameterInstance() {
        return i++;
    }


    @Before
    public void setUp() throws Exception {
        i = 0;
        defaultVal = getParameterInstance();
        newInstance2 = getParameterInstance();
        newInstance3 = getParameterInstance();

        matrix3 = matrixFactory.getMatrix(3, defaultVal);

        if ((defaultVal.equals(newInstance2)) || (defaultVal.equals(newInstance3))
                || (newInstance3.equals(newInstance2))) {
            fail("new instances should be different");
        }


        defaultVal2 = getParameterInstance();
        newInstance4 = getParameterInstance();
        newInstance5 = getParameterInstance();

        matrix6 = matrixFactory.getMatrix(6, defaultVal);
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

        Matrix<Integer> matrixDefSize = matrixFactory.getMatrix(defaultVal);
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


        assertEquals(defaultVal, matrix6.get(1, 4));
        assertEquals(defaultVal, matrix6.get(5, 3));
        assertEquals(defaultVal, matrix6.get(1, 5));
        assertEquals(defaultVal, matrix6.get(6, 6));
        assertEquals(defaultVal, matrix6.get(4, 5));
        assertEquals(defaultVal, matrix6.get(2, 3));
        try {
            matrix3.get(0, 2);
            fail("Should not work");
        } catch (Exception e) {
        }
        try {
            matrix3.get(1, 7);
            fail("Should not work");
        } catch (Exception e) {
        }
        try {
            matrix3.get(7, 4);
            fail("Should not work");
        } catch (Exception e) {
        }
        try {
            matrix3.get(8, 10);
            fail("Should not work");
        } catch (Exception e) {
        }
        try {
            matrix3.get(-1, 4);
            fail("Should not work");
        } catch (Exception e) {
        }
    }

    @Test
    public void testGetSet() {
        matrix3.set(1, 1, newInstance2);
        assertEquals(newInstance2, matrix3.get(1, 1));
        assertNotEquals(defaultVal, matrix3.get(1, 1));
        assertEquals(defaultVal, matrix3.get(2, 2));
        matrix3.set(2, 2, newInstance3);
        assertEquals(newInstance2, matrix3.get(1, 1));
        assertNotEquals(defaultVal, matrix3.get(1, 1));
        assertEquals(newInstance3, matrix3.get(2, 2));
        assertNotEquals(defaultVal, matrix3.get(2, 2));
        assertEquals(defaultVal, matrix3.get(3, 3));
        assertEquals(defaultVal, matrix3.get(1, 3));

        matrix3.set(1, 3, defaultVal);
        assertEquals(defaultVal, matrix3.get(1, 3));
        matrix3.set(1, 1, defaultVal);
        assertEquals(defaultVal, matrix3.get(1, 1));

        try {
            matrix3.set(4, 1, newInstance3);
            fail("Should not work");
        } catch (Exception e) {
        }
        try {
            matrix3.set(1, 4, newInstance3);
            fail("Should not work");
        } catch (Exception e) {
        }


        matrix6.set(2, 1, newInstance2);
        assertEquals(newInstance2, matrix6.get(2, 1));
        matrix6.set(2, 1, newInstance3);
        assertEquals(newInstance3, matrix6.get(2, 1));
        matrix6.set(6, 6, newInstance3);
        assertEquals(newInstance3, matrix6.get(6, 6));
        matrix6.set(6, 6, newInstance4);
        assertEquals(newInstance4, matrix6.get(6, 6));
        matrix6.set(3, 4, newInstance5);
        assertEquals(newInstance5, matrix6.get(3, 4));
        assertEquals(defaultVal, matrix6.get(5, 5));
        assertEquals(defaultVal, matrix6.get(5, 5));
        assertEquals(defaultVal, matrix6.get(2, 2));
        assertEquals(defaultVal, matrix6.get(5, 5));
    }

    @Test
    public void testTranspose() {
        matrix3.set(1, 1, newInstance2);
        matrix3.set(2, 3, newInstance3);
        matrix3.transpose();

        chackTrans(matrix3);


        matrix6.set(1, 1, newInstance2);
        matrix6.set(2, 3, newInstance3);
        matrix6.set(4, 5, newInstance4);
        matrix6.set(6, 1, newInstance5);
        matrix6.transpose();

        chackTrans(matrix6);
        assertEquals(newInstance4, matrix6.get(5, 4));
        assertEquals(newInstance5, matrix6.get(1, 6));
        assertEquals(defaultVal, matrix6.get(2, 3));
        assertEquals(defaultVal, matrix6.get(4, 5));

        matrix6.set(3, 2, newInstance3);
        assertEquals(newInstance3, matrix6.get(3, 2));

        matrix6.transpose();

        assertEquals(newInstance4, matrix6.get(4, 5));
        assertEquals(newInstance5, matrix6.get(6, 1));
        assertEquals(defaultVal, matrix6.get(3, 2));
        assertEquals(defaultVal, matrix6.get(5, 4));
        assertEquals(newInstance3, matrix6.get(2, 3));
    }

    private void chackTrans(Matrix<Integer> transM) {
        assertEquals(newInstance2, transM.get(1, 1));
        assertEquals(defaultVal, transM.get(2, 2));
        assertEquals(defaultVal, transM.get(2, 3));
        assertEquals(defaultVal, transM.get(1, 2));
        assertEquals(newInstance3, transM.get(3, 2));
    }
}
