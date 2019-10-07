package ca.ubc.ece.cpen221.mp1;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BasicTests {

    @Test
    public void testCreateWave() {
        double[] lchannel = {1.0, -1.0};
        double[] rchannel = {1.0, -1.0};
        SoundWave wave = new SoundWave(lchannel, rchannel);
        double[] lchannel1 = wave.getLeftChannel();
        Assert.assertArrayEquals(lchannel, lchannel1, 0.00001);
        double[] rchannel1 = wave.getRightChannel();
        Assert.assertArrayEquals(rchannel, rchannel1, 0.00001);
    }

    @Test
    public void testCreate1() {
        double[] lchannel = {0.3829, -0.2667};
        double[] rchannel = {0.32003, -0.7738};
        SoundWave wave = new SoundWave(lchannel, rchannel);
        double[] lchannel1 = wave.getLeftChannel();
        Assert.assertArrayEquals(lchannel, lchannel1, 0.00001);
        double[] rchannel1 = wave.getRightChannel();
        Assert.assertArrayEquals(rchannel, rchannel1, 0.00001);
    }



    @Test
    public void testCreate3bySinFunction() {
        double[] left = {0.77,0.768047};
        double[] right = {0.77,0.768047};
        SoundWave wave = new SoundWave(left,right);
        double[] left1 = wave.getLeftChannel();
        Assert.assertArrayEquals(left, left1, 0.00001);
        double[] right1 = wave.getRightChannel();
        Assert.assertArrayEquals(right, right1, 0.00001);
    }


}


