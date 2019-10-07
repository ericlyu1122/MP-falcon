package ca.ubc.ece.cpen221.mp1;
import ca.ubc.ece.cpen221.mp1.utils.NotFoundException;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class Task3Test {

    @Test
    public void testCreateWave() throws NotFoundException {
        double[] lchannel = {1.0, -1.0};
        double[] rchannel = {1.0, -1.0};
        SoundWave wave1 = new SoundWave(lchannel, rchannel);
        double[] lchannel1={0.6, 0.8, 0.5, -0.4};
        double[] rchannel1={0.8,1.0, 0.2, -0.6};
        SoundWave wave2=new SoundWave(lchannel1,rchannel1);
        Set<SoundWave> com=new HashSet<>();
        double[] lchannel2={0.3, 0.8, 0.5, -0.4};
        double[] rchannel2={0.4,1.0, 0.7, -0.6,-0.2,0.4};
        SoundWave wave3=new SoundWave(lchannel2,rchannel2);
        double[] lchannel3={0.2, 0.5, 0.1, -0.7, -0.2,-1.0};
        double[] rchannel3={0.8,5.0, 0.1, -0.5};
        SoundWave wave4=new SoundWave(lchannel3,rchannel3);
        double[] lchannel4={0.2, 0.7, 0.5, -0.4};
        double[] rchannel4={0.8, 0.0, 0.2, 0.6, -1.0 };
        SoundWave wave5=new SoundWave(lchannel4,rchannel4);
        double[] lchannel5={0.6, 1.0, 0.7, -0.2, 0.7};
        double[] rchannel5={0.8,1.0, 0.2, -0.6, 0.2};
        SoundWave wave6=new SoundWave(lchannel5,rchannel5);
        com.add(wave1);
        com.add(wave2);
        com.add(wave3);
        com.add(wave4);
        com.add(wave5);
        com.add(wave6);
        Set<SoundWave> res=new HashSet<>();
        SoundWaveSimilarity abc=new SoundWaveSimilarity();
        res=abc.getSimilarWaves(com,3,wave1);

        Assert.assertEquals(res,com);
    }

}
