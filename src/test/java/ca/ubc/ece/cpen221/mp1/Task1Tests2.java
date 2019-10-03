package ca.ubc.ece.cpen221.mp1;

import org.junit.Assert;
import org.junit.Test;



public class Task1Tests2 {


    @Test(expected = IllegalArgumentException.class)
    public void HPS1() {
        double[] r = {1.0, 0.2, 0.6};
        double[] l = {1.0, 0.2, 0.6};
        SoundWave a = new SoundWave(l, r);
        a.highPassFilter(-11, 1.0);
    }


    @Test(expected = IllegalArgumentException.class)
    public void HPS2() {
        double[] r = {1.0, 0.2, 0.6};
        double[] l = {1.0, 0.2, 0.6};
        SoundWave a = new SoundWave(l, r);
        a.highPassFilter(12, 0.0);
    }


    @Test
    public void HPS3() {
        double[] r = {1.0, 0.2, 0.6};
        double[] l = {1.0, 0.2, 0.6};
        SoundWave a = new SoundWave(l, r);
        SoundWave result = a.highPassFilter(1, 1.2);
        /*in this case, alpha = 6/11
         * y[0] = x[0] = 1.0
         * y[1] = 6/11 * y[0] + 6/11 * (x[1]-x[0])=12/55
         * y[2] = 6/11 * y[1] + 6/11 * (x[2]-x[1])=21/121*/
        double[] resultr = {1.0, 0.1090909091, 0.277685950};
        double[] resultl = {1.0, 0.1090909091, 0.277685950};
        Assert.assertArrayEquals(resultl, result.getLeftChannel(), 0.00001);
        Assert.assertArrayEquals(resultr, result.getRightChannel(), 0.00001);
    }


    @Test
    public void HPS4() {
        double[] r = {-1.0, 0.8, 0.4, -0.6};
        double[] l = {-1.0, 0.8, 0.4, -0.6};
        SoundWave a = new SoundWave(l, r);
        SoundWave result = a.highPassFilter(3, 2.0);
        double[] resultr = {-1.0, 0.32, -0.032, -0.4128};
        double[] resultl = {-1.0, 0.32, -0.032, -0.4128};
        Assert.assertArrayEquals(resultl, result.getLeftChannel(), 0.00001);
        Assert.assertArrayEquals(resultr, result.getRightChannel(), 0.00001);
    }


    @Test
    public void HAF1() {
        double[] r = {1.0, -1.0};
        double[] l = {1.0, -1.0};
        SoundWave a = new SoundWave(l, r);
        Assert.assertEquals(0.5 * 44100, a.highAmplitudeFreqComponent(), 0.00001);
    }


    @Test
    public void HAF2() {
        double[] r = {1.0, 0.7, 0.4, -0.1};
        double[] l = {1.0, 0.7, 0.4, -0.1};
        SoundWave a = new SoundWave(l, r);
        Assert.assertEquals(0, a.highAmplitudeFreqComponent(), 0.00001);
    }


    @Test
    public void HAF3() {
        double[] r = {0.1, 0.45, 0.72, 1.0, -1.0, -0.3};
        double[] l = {0.1, 0.45, 0.72, 1.0, -1.0, -0.3};
        SoundWave a = new SoundWave(l, r);
        Assert.assertEquals(5.0/6 * 44100, a.highAmplitudeFreqComponent(), 0.00001);
    }

    @Test
    public void HAF4() {
        double[] r = {0.3, -1.0, 0.22, 1.0, -0.6, -0.4, 0.0};
        double[] l = {0.3, -1.0, 0.22, 1.0, -0.6, -0.4, 0.0};
        SoundWave a = new SoundWave(l, r);
        Assert.assertEquals(0.7142857143 * 44100, a.highAmplitudeFreqComponent(), 0.00001);
    }


    @Test
    public void HAF5() {
        double[] r = {0.1, 0.6, 0.5, -0.1};
        double[] l = {0.7, 0.95, -0.3, -0.5};
        SoundWave a = new SoundWave(l, r);
        Assert.assertEquals(0.75 * 44100, a.highAmplitudeFreqComponent(), 0.00001);
    }


    @Test
    public void HAF6() {
        double[] r = {1.0, 0.8, 0.4, -0.1};
        double[] l = {0.3, 0.75, -0.1, -0.5};
        SoundWave a = new SoundWave(l, r);
        Assert.assertEquals(0, a.highAmplitudeFreqComponent(), 0.00001);
    }

}
