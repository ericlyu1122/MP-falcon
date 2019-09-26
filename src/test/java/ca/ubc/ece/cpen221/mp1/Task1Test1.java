package ca.ubc.ece.cpen221.mp1;

import org.junit.Assert;
import org.junit.Test;



public class Task1Test1 {

    /*Test1: append a new wave by two arrays*/
    @Test
    public void testappend1() {
        double[] al = {0.87, 0.22, -0.78};
        double[] ar = {0.87, 0.22, -0.78};
        SoundWave a = new SoundWave(al, ar);
        double[] bl = {0.87, 0.22, -0.78};
        double[] br = {0.87, 0.22, -0.78};
        a.append(bl, br);
        double[] resultl = {0.87, 0.22, -0.78, 0.87, 0.22, -0.78};
        double[] resultr = {0.87, 0.22, -0.78,0.87, 0.22, -0.78};
        Assert.assertArrayEquals(resultl, a.getLeftChannel(), 0.00001);
        Assert.assertArrayEquals(resultr, a.getRightChannel(), 0.00001);
    }


    /*Test3: add a new wave with smaller length*/
    @Test
    public void testadd1() {
        double[] al = {0.1, -0.27, 0.11, 0.89};
        double[] ar = {0.1, -0.27, 0.11, 0.89};
        SoundWave a = new SoundWave(al, ar);
        double[] bl = {-0.03, 0.2872, 0.13};
        double[] br = {-0.03, 0.2872, 0.13};
        SoundWave b = new SoundWave(bl, br);
        a.add(b);
        double[] resultl = {0.07, 0.0172, 0.24, 0.89};
        double[] resultr = {0.07, 0.0172, 0.24, 0.89};
        Assert.assertArrayEquals(resultl, a.getLeftChannel(), 0.00001);
        Assert.assertArrayEquals(resultr, a.getRightChannel(), 0.00001);
    }


    /*Test2: append a new wave by another wave*/
    @Test
    public void  testappend2() {
        double[] al = {-0.24, 0.0, 0.3};
        double[] ar = {-0.24, 0.0, 0.3};
        SoundWave a = new SoundWave(al, ar);
        double[] bl = {0.12, 0.68, -1.0};
        double[] br = {0.12, 0.68, -1.0};
        SoundWave b = new SoundWave(bl, br);
        a.append(b);
        double[] resultl = {-0.24, 0.0, 0.3, 0.12, 0.68, -1.0};
        double[] resultr = {-0.24, 0.0, 0.3, 0.12, 0.68, -1.0};
        Assert.assertArrayEquals(resultl, a.getLeftChannel(), 0.00001);
        Assert.assertArrayEquals(resultr, a.getRightChannel(), 0.00001);
    }



    @Test
    public void testadd2() {
        double[] al = {0.2, -0.13334552, 0.0, 0.9888732};
        double[] ar = {0.2, -0.13334552, 0.0, 0.9888732};
        SoundWave a = new SoundWave(al, ar);
        double[] bl = {-0.3, 0.3333333, 0.5, -0.1666666};
        double[] br = {-0.3, 0.3333333, 0.5, -0.1666666};
        SoundWave b = new SoundWave(bl, br);
        a.add(b);
        double[] resultl = {-0.1, 0.19998778, 0.5, 0.8222066};
        double[] resultr = {-0.1, 0.19998778, 0.5, 0.8222066};
        Assert.assertArrayEquals(resultl, a.getLeftChannel(), 0.00001);
        Assert.assertArrayEquals(resultr, a.getRightChannel(), 0.00001);
    }

    /*Test5: add a new wave with longer length*/
    @Test
    public void addTest3() {
        double[] al = {0.1, -0.2, 0.0000001, 0.9};
        double[] ar = {0.1, -0.2, 0.0000001, 0.9};
        SoundWave a = new SoundWave(al, ar);
        double[] bl = {-0.45, 0.5, 1.0, -0.3, 0.66};
        double[] br = {-0.45, 0.5, 1.0, -0.3, 0.66};
        SoundWave b = new SoundWave(bl, br);
        a.add(b);
        double[] resultl = {-0.35, 0.3, 1.0, 0.6, 0.66};
        double[] resultr = {-0.35, 0.3, 1.0, 0.6, 0.66};
        Assert.assertArrayEquals(resultl, a.getLeftChannel(), 0.00001);
        Assert.assertArrayEquals(resultr, a.getRightChannel(), 0.00001);
    }


    /*Test6: add echo with invalid numbers*/
    @Test (expected = IllegalArgumentException.class)
    public void TestEcho1() {
        double[] al = {0.2, -0.8, 0.0000001, 0.5};
        double[] ar = {0.2, -0.8, 0.0000001, 0.5};
        SoundWave a = new SoundWave(al, ar);
        a = a.addEcho(-2, 1);
    }

    /*Test7: add echo with invalid numbers*/
    @Test (expected = IllegalArgumentException.class)
    public void TestEcho2() {
        double[] al = {0.2, -0.8, 0.0000001, 0.5};
        double[] ar = {0.2, -0.8, 0.0000001, 0.5};
        SoundWave a = new SoundWave(al, ar);
        a = a.addEcho(1, -2);
    }
    /*Test8: add echo with lag < wave length*/
    @Test
    public void TestEcho3() {
        double[] al = {0.2, -0.4, 0.001, 0.6};
        double[] ar = {0.2, -0.4, 0.001, 0.6};
        SoundWave a = new SoundWave(al, ar);
        a = a.addEcho(1, 0.5);
        double[] resultl = {0.2, -0.3, -0.199, 0.6005, 0.3};
        double[] resultr = {0.2, -0.3, -0.199, 0.6005, 0.3};
        Assert.assertArrayEquals(resultl, a.getLeftChannel(), 0.00001);
        Assert.assertArrayEquals(resultr, a.getRightChannel(), 0.00001);
    }

    /*Test9: add echo with lag > wave length*/
    @Test
    public void TestEcho4() {
        double[] al = {-0.1, 0.4, 0.14, 0.72};
        double[] ar = {-0.1, 0.4, 0.14, 0.72};
        SoundWave a = new SoundWave(al, ar);
        a = a.addEcho(5, 0.5);
        double[] resultl = {-0.1, 0.4, 0.14, 0.72, 0.0, -0.05, 0.2, 0.07, 0.36};
        double[] resultr = {-0.1, 0.4, 0.14, 0.72, 0.0, -0.05, 0.2, 0.07, 0.36};
        Assert.assertArrayEquals(resultl, a.getLeftChannel(), 0.00001);
        Assert.assertArrayEquals(resultr, a.getRightChannel(), 0.00001);
    }
    /*Test10: add echo with lag == wave length*/
    @Test
    public void TestEcho5() {
        double[] al = {-0.4, 0.46, 0.28, 0.8};
        double[] ar = {-0.4, 0.46, 0.28, 0.8};
        SoundWave a = new SoundWave(al, ar);
        a = a.addEcho(4, 0.5);
        double[] resultl = {-0.4, 0.46, 0.28, 0.8, -0.2, 0.23, 0.14, 0.4};
        double[] resultr = {-0.4, 0.46, 0.28, 0.8, -0.2, 0.23, 0.14, 0.4};
        Assert.assertArrayEquals(resultl, a.getLeftChannel(), 0.00001);
        Assert.assertArrayEquals(resultr, a.getRightChannel(), 0.00001);
    }

    /*Test10: add echo with lag == wave length-1*/
    @Test
    public void TestEcho6() {
        double[] al = {-0.1, 0.4, 0.28, 0.56};
        double[] ar = {-0.1, 0.4, 0.28, 0.56};
        SoundWave a = new SoundWave(al, ar);
        a = a.addEcho(3, 0.2);
        double[] resultl = {-0.1, 0.4, 0.28, 0.54, 0.08, 0.056, 0.112};
        double[] resultr = {-0.1, 0.4, 0.28, 0.54, 0.08, 0.056, 0.112};
        Assert.assertArrayEquals(resultl, a.getLeftChannel(), 0.00001);
        Assert.assertArrayEquals(resultr, a.getRightChannel(), 0.00001);
    }

    /*Test10: add echo such that lag = 1, some values exceeds 1 or -1 */
    @Test
    public void TestEcho7() {
        double[] al = {0.8, 0.6, 0.34, 0.12};
        double[] ar = {0.8, 0.6, 0.34, 0.12};
        SoundWave a = new SoundWave(al, ar);
        a = a.addEcho(1, 0.5);
        double[] resultl = {0.8, 1.0, 0.64, 0.29, 0.06};
        double[] resultr = {0.8, 1.0, 0.64, 0.29, 0.06};
        Assert.assertArrayEquals(resultl, a.getLeftChannel(), 0.00001);
        Assert.assertArrayEquals(resultr, a.getRightChannel(), 0.00001);
    }
}
