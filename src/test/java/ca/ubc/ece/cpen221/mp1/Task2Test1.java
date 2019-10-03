package ca.ubc.ece.cpen221.mp1;
import org.junit.Assert;
import org.junit.Test;

public class Task2Test1 {
    /*Test1: test contains for two same waves*/
    @Test
    public void testContains1() {
        double[] al = {1.0, 0.5};
        double[] ar = {1.0, 0.5};
        SoundWave a = new SoundWave(al, ar);
        double[] bl = {1.0, 0.5};
        double[] br = {1.0, 0.5};
        SoundWave b = new SoundWave(bl, br);
        Assert.assertTrue(a.contains(b));
    }

    /*Test2: test contains for two same waves with scaling */
    @Test
    public void testContains2() {
        double[] al = {1.0, 0.5};
        double[] ar = {1.0, 0.5};
        SoundWave a = new SoundWave(al, ar);
        double[] bl = {0.5, 0.25};
        double[] br = {0.5, 0.25};
        SoundWave b = new SoundWave(bl, br);
        Assert.assertTrue(a.contains(b));
    }

    /*Test3: test contains for two same waves with scaling overflow */
    @Test
    public void testContains3() {
        double[] al = {1.0, 0.5, 1.0};
        double[] ar = {1.0, 0.5, 1.0};
        SoundWave a = new SoundWave(al, ar);
        double[] bl = {0.5, 0.25, 1.0};
        double[] br = {0.5, 0.25, 1.0};
        SoundWave b = new SoundWave(bl, br);
        Assert.assertTrue(a.contains(b));
    }

    /*Test4: test contains for invalid case 1, only the first few terms of wave2 are in wave1 */
    @Test
    public void testContains4() {
        double[] al = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6};
        double[] ar = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6};
        SoundWave a = new SoundWave(al, ar);
        double[] bl = {0.5, 0.6, 0.7, 0.8};
        double[] br = {0.5, 0.6, 0.7, 0.8};
        SoundWave b = new SoundWave(bl, br);
        Assert.assertFalse(a.contains(b));
    }

    /*Test5: test contains for invalid case 2,  wave2 is longer than wave1 */
    @Test
    public void testContains5() {
        double[] al = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6};
        double[] ar = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6};
        SoundWave a = new SoundWave(al, ar);
        double[] bl = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.5, 0.6, 0.7, 0.8};
        double[] br = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.5, 0.6, 0.7, 0.8};
        SoundWave b = new SoundWave(bl, br);
        Assert.assertFalse(a.contains(b));
    }

    /*Test6: test contains for different left and right channels */
    @Test
    public void testContains6() {
        double[] al = {-0.1, 0.2, 0.3, 0.4, 0.5, 0.6};
        double[] ar = {0.3, -0.4, 0.7, 0.9, 1.0, 0.2};
        SoundWave a = new SoundWave(al, ar);
        double[] bl = {-0.01, 0.02, 0.03};
        double[] br = {0.03, -0.04, 0.07};
        SoundWave b = new SoundWave(bl, br);
        Assert.assertTrue(a.contains(b));
    }

    /*Test7: test contains for different left and right channels starting point of the similar sequences are different*/
    @Test
    public void testContains7() {
        double[] al = {0.0, 0.0, 0.0, 1.0};
        double[] ar = {0.0, 0.4, 0.7, 0.9, 1.0, 0.2};
        SoundWave a = new SoundWave(al, ar);
        double[] bl = {0.0, 0.0, 0.0, 1.0};
        double[] br = {0.7, 0.9, 1.0, 0.2};
        SoundWave b = new SoundWave(bl, br);
        Assert.assertFalse(a.contains(b));
    }

    /*Test8: test contains with starting zeros and negative numbers*/
    @Test
    public void testContains8() {
        double[] al = {0.0, 0.0, 0.0, -1.0};
        double[] ar = {0.0, 0.4, 0.7, 0.9, 1.0, 0.2};
        SoundWave a = new SoundWave(al, ar);
        double[] bl = {0.0, 0.0, 0.0, -0.5};
        double[] br = {0.0, 0.2, 0.35, 0.45};
        SoundWave b = new SoundWave(bl, br);
        Assert.assertTrue(a.contains(b));
    }

    /*Test9: test contains with one wave of all zeros*/
    @Test
    public void testContains9() {
        double[] al = {0.0, 0.0, 0.0, -1.0};
        double[] ar = {0.0, 0.0, 0.0, 0.0, 1.0, 0.2};
        SoundWave a = new SoundWave(al, ar);
        double[] bl = {0.0, 0.0, 0.0, 0.0};
        double[] br = {0.0, 0.0, 0.0, 0.0};
        SoundWave b = new SoundWave(bl, br);
        Assert.assertFalse(a.contains(b));
    }

    /*Test10: test contains with one wave of all zeros*/
    @Test
    public void testContains10() {
        double[] al = {0.1};
        double[] ar = {0.2, 0.0, 0.0, 0.0, 0.0, 1.0, 0.2};
        SoundWave a = new SoundWave(al, ar);
        double[] bl = {0.0, 0.0, 0.0, 0.0};
        double[] br = {0.0, 0.0, 0.0, 0.0};
        SoundWave b = new SoundWave(bl, br);
        Assert.assertFalse(a.contains(b));
    }

    /*Test11: test contains with one wave of all zeros*/
    @Test
    public void testContains11() {
        double[] al = {0.1};
        double[] ar = {-0.2, 0.0, 0.0, 0.0, 0.0, 1.0, 0.2};
        SoundWave a = new SoundWave(al, ar);
        double[] bl = {0.0, 0.0, 0.0, 0.0};
        double[] br = {0.0, 0.0, 0.0, 0.0, 0.0};
        SoundWave b = new SoundWave(bl, br);
        Assert.assertFalse(a.contains(b));
    }
    /*Test12 : similarity test wave1 and wave2 are the same, the output should be 1*/
    @Test
    public void similarityTest1() {
        double[] r = {0.1, 0.2, 0.3};
        double[] l = {0.1, 0.2, 0.3};
        SoundWave a = new SoundWave(l, r);
        SoundWave b = new SoundWave(l, r);
        Assert.assertEquals(1.0, a.similarity(b), 0.00001);
    }

    /*Test13 : similarity test, wave1 and wave2 are different*/
    @Test
    public void similarityTest2() {
        double[] ar = {0.1, 0.2, 0.3, 0.4};
        double[] al = {0.1, 0.2, 0.3, 0.4};
        double[] br = {0.0, 0.0, 0.0};
        double[] bl = {0.0, 0.0};
        SoundWave a = new SoundWave(al, ar);
        SoundWave b = new SoundWave(bl, br);
        Assert.assertEquals(0.625, a.similarity(b), 0.00001);
    }
@Test
    public void testContains13() {
        double[] al = {0.1, 0.2, 0.3, 0.4};
        double[] ar = {-0.1, 0.2, 0.3, 0.4};
        SoundWave a = new SoundWave(al, ar);
        double[] bl = {-0.2, -0.4};
        double[] br =  {0.2, -0.4,-0.6};
        SoundWave b = new SoundWave(bl, br);
        Assert.assertTrue(a.contains(b));
    }
    /*Test14 : similarity test*/
    @Test
    public void similarityTest3() {
        double[] ar = {-0.1, 0.2, 0.3, 0.4};
        double[] al = {0.1, 0.2, 0.3, 0.4};
        double[] br = {0.2, -0.4,-0.6};
        double[] bl = {-0.2, -0.4};
        SoundWave a = new SoundWave(al, ar);
        SoundWave b = new SoundWave(bl, br);
        Assert.assertEquals(0.70921986, a.similarity(b), 0.00001);
    }

    @Test
    public void similarityTest4() {
        double[] ar = {-0.1, 0.2, 0.3, 0.4};
        double[] al = {0.1, 0.2, 0.3, 0.4};
        double[] br = { -0.4,-0.6};
        double[] bl = {-0.4, -0.6,-0.8};
        SoundWave a = new SoundWave(al, ar);
        SoundWave b = new SoundWave(bl, br);
        Assert.assertEquals(0.64935065, a.similarity(b), 0.00001);
    }
    @Test
    public void similarityTest5() {

        SoundWave a = new SoundWave(9,0,0.5,1.0);
        SoundWave b = new SoundWave(49,0,0.7,1.0);
        Assert.assertEquals(0.64935065, a.similarity(b), 0.00001);
    }
}
