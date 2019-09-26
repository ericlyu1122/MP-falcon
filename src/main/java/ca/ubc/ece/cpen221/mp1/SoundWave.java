package ca.ubc.ece.cpen221.mp1;

import ca.ubc.ece.cpen221.mp1.utils.ComplexNumber;
import ca.ubc.ece.cpen221.mp1.utils.HasSimilarity;
import javazoom.jl.player.StdPlayer;

import java.util.ArrayList;

import java.util.*;

public class SoundWave implements HasSimilarity<SoundWave> {

    // We are going to treat the number of samples per second of a sound wave
    // as a constant.
    // The best way to refer to this constant is as
    // SoundWave.SAMPLES_PER_SECOND.
    public static final int SAMPLES_PER_SECOND = 44100;

    // some representation fields that you could use
    private ArrayList<Double> lchannel = new ArrayList<>();
    private ArrayList<Double> rchannel= new ArrayList<>();



    private int samples = 0;

    /**
     * Create a new SoundWave using the provided left and right channel
     * amplitude values. After the SoundWave is created, changes to the
     * provided arguments will not affect the SoundWave.
     *
     * @param lchannel is not null and represents the left channel.
     * @param rchannel is not null and represents the right channel.
     */
    public SoundWave(double[] lchannel, double[] rchannel) {
        // TODO: Implement this constructor
        for(int i=0;i<lchannel.length;i++) {
            this.lchannel.add(lchannel[i]) ;


        }
        for(int j=0;j<rchannel.length;j++) {

            this.rchannel.add(rchannel[j]) ;

        }
        this.samples=this.rchannel.size();

    }

    public SoundWave() {
        // TODO: You should implement a default constructor
        // that creates an empty wave
        this.samples=0;
    }

    /**
     * Create a new sinusoidal sine wave,
     * sampled at a rate of 44,100 samples per second
     *
     * @param freq      the frequency of the sine wave, in Hertz
     * @param phase     the phase of the sine wave, in radians
     * @param amplitude the amplitude of the sine wave, 0 < amplitude <= 1
     * @param duration  the duration of the sine wave, in seconds
     */
    public SoundWave(double freq, double phase, double amplitude, double duration) {
        // TODO: Implement this constructor
        this.samples= (int) (duration*SAMPLES_PER_SECOND);
        for(int i=0;i<=samples;i++){
            double sin = Math.sin(2 * Math.PI * freq * i / SAMPLES_PER_SECOND + phase);

            this.lchannel.add(amplitude* sin);
            this.rchannel.add(amplitude* sin);
        }
    }

    /**
     * Obtain the left channel for this wave.
     * Changes to the returned array should not affect this SoundWave.
     *
     * @return an array that represents the left channel for this wave.
     */
    public double[] getLeftChannel() {
        // TODO: Implement this
        double[] leftChannel = new double[lchannel.size()];
        for(int i=0;i<lchannel.size();i++){
            leftChannel[i]=lchannel.get(i);
        }

        return leftChannel; // change this
    }

    /**
     * Obtain the right channel for this wave.
     * Changes to the returned array should not affect this SoundWave.
     *
     * @return an array that represents the right channel for this wave.
     */
    public double[] getRightChannel() {
        // TODO: Implement this
        double[] rightChannel = new double[rchannel.size()];
        for(int i=0;i<rchannel.size();i++){
            rightChannel[i]=rchannel.get(i);
        }

        return rightChannel; // change this
    }


    /**
     * A simple main method to play an MP3 file. Note that MP3 files should
     * be encoded to use stereo channels and not mono channels for the sound to
     * play out correctly.
     * <p>
     * One should try to get this method to work correctly at the start.
     * </p>
     *
     * @param args are currently ignored but you could be creative.
     */
    /**
     * A simple main method to play an MP3 file. Note that MP3 files should
     * be encoded to use stereo channels and not mono channels for the sound to
     * play out correctly.
     * <p>
     * One should try to get this method to work correctly at the start.
     * </p>
     *
     * @param args are currently ignored but you could be creative.
     */
    public static void main(String[] args) {
        StdPlayer.open("mp3/anger.mp3");
        SoundWave sw = new SoundWave();
        while (!StdPlayer.isEmpty()) {
            double[] lchannel = StdPlayer.getLeftChannel();
            double[] rchannel = StdPlayer.getRightChannel();
            sw.append(lchannel, rchannel);
        }
        sw.sendToStereoSpeaker();
        StdPlayer.close();
    }

    /**
     * Append a wave to this wave.
     *
     * @param lchannel
     * @param rchannel
     */
    public void append(double[] lchannel, double[] rchannel) {
        // TODO: Implement this method.
      for(double value : lchannel){
          this.lchannel.add(value);
      }
      for(double v: rchannel){
          this.rchannel.add(v);
      }
    }

    /**
     * Append a wave to this wave.
     *
     * @param other the wave to append.
     */
    public void append(SoundWave other) {
        // TODO: Implement this method.
        this.lchannel.addAll(other.lchannel);
        this.rchannel.addAll(other.rchannel);
    }

    /**
     * Create a new wave by adding another wave to this wave.
     * (You should also write clear specifications for this method.)
     *
     * @param other the other wave added to the original one
     */
    public SoundWave add(SoundWave other) {
        // TODO: Implement this method
        SoundWave Addwave =new SoundWave();

        for(int i=0;i<other.lchannel.size()||
                (i<this.lchannel.size());i++){
            if(i>=other.lchannel.size()||i>=this.lchannel.size()){
                if(other.lchannel.size()>this.lchannel.size()){
                    this.lchannel.add(other.lchannel.get(i));
                    this.rchannel.add(other.rchannel.get(i));
                }
            }else {
                this.lchannel.set(i,this.lchannel.get(i)+other.lchannel.get(i));
                this.rchannel.set(i,this.rchannel.get(i)+other.rchannel.get(i));
            }
        }
        Addwave.lchannel.addAll(this.lchannel);
        Addwave.rchannel.addAll(this.rchannel);
        return Addwave; // change this
    }

    /**
     * Create a new wave by adding an echo to this wave.
     *
     * @param delta > 0. delta is the lag between this wave and the echo wave.
     * @param alpha > 0. alpha is the damping factor applied to the echo wave.
     * @return a new sound wave with an echo.
     */
    public SoundWave addEcho(int delta, double alpha) throws IllegalArgumentException {
        // TODO: Implement this method
if(delta<0||alpha<0){
    throw new IllegalArgumentException();
}else {
    int temp ;

    double[] rchan = new double[this.samples + delta];
    double[] lchan = new double[this.samples + delta];
    for (int i = 0; i < (this.samples + delta); i++) {
        temp = i - delta;
        if (i < delta && i < this.samples) {
            rchan[i] = this.rchannel.get(i);
            lchan[i] = this.lchannel.get(i);
        } else if (i >= delta && i < this.samples && temp >= 0) {
            if (this.rchannel.get(i) + this.rchannel.get(temp) * alpha <= 1.0 && this.rchannel.get(i) + this.rchannel.get(temp) * alpha >= -1.0) {
                rchan[i] = this.rchannel.get(i) + this.rchannel.get(temp) * alpha;
                lchan[i] = this.lchannel.get(i) + this.lchannel.get(temp) * alpha;
            } else if (this.rchannel.get(i) + this.rchannel.get(temp) * alpha >= 1.0) {
                rchan[i] = 1.0;
                lchan[i] = 1.0;
            } else {
                rchan[i] = -1.0;
                lchan[i] = -1.0;
            }
        } else if (i >= delta && i < this.samples + delta && temp < 0) {
            rchan[i] = 0.0;
            lchan[i] = 0.0;
        } else if (i >= delta && i < this.samples + delta && temp >= 0) {
            rchan[i] = this.rchannel.get(temp) * alpha;
            lchan[i] = this.lchannel.get(temp) * alpha;
        }
    }
    SoundWave echoWave = new SoundWave(rchan, lchan);
    return echoWave;
}// change this
    }

    /**
     * Scale the amplitude of this wave by a scaling factor.
     * After scaling, the amplitude values are clipped to remain
     * between -1 and +1.
     *
     * @param scalingFactor is a value > 0.
     */
    public void scale(double scalingFactor) {
        // TODO: Implement this method.
        for(Double x:rchannel) {
           x*=scalingFactor;
            if(x>=1.0) x=1.0;
            else if(x<-1.0) x=-1.0;
        }
        for(Double y:lchannel) {
            y*=scalingFactor;
            if(y>=1.0) y=1.0;
            else if(y<-1.0) y=-1.0;
        }
    }

    /**
     * Return a new sound wave that is obtained by applying a high-pass filter to
     * this wave.
     *
     * @param dt >= 0. dt is the time interval used in the filtering process.
     * @param RC > 0. RC is the time constant for the high-pass filter.
     * @return
     */
    public SoundWave highPassFilter(int dt, double RC) {
        // TODO: Implement this
        SoundWave highPass =new SoundWave();
        double alpha=RC/(RC+dt);
        double tempR=this.rchannel.get(0);
        double tempL=this.lchannel.get(0);
        double storeR;
        double storeL;
        for (int i=1;i<rchannel.size();i++){
        // y[i] := α * y[i-1] + α * (x[i] - x[i-1])
            storeR=this.rchannel.get(i);
            this.rchannel.set(i,alpha*this.rchannel.get(i-1)+alpha*(storeR-tempR));
            tempR=storeR;
            storeL=this.lchannel.get(i);
            this.lchannel.set(i,alpha*this.lchannel.get(i-1)+alpha*(storeL-tempL));
            tempL=storeL;
        }
        highPass.rchannel.addAll(this.rchannel);
        highPass.lchannel.addAll(this.lchannel);

        return highPass; // change this
    }

    /**
     * Return the frequency of the component with the greatest amplitude
     * contribution to this wave. This component is obtained by applying the
     * Discrete Fourier Transform to this wave.
     *
     * @return the frequency of the wave component of highest amplitude.
     * If more than one frequency has the same amplitude contribution then
     * return the higher frequency.
     */
    public double highAmplitudeFreqComponent() {
        // TODO: Implement this method
        double xt;
        double tempAmp=0.0;
        double maxAmp=0.0;
        int Kval=0;
        int i;
        ComplexNumber Sum=new ComplexNumber(0.0,0.0);
        for( i=0;i<this.samples;i++){

            for (int j=0;j<this.samples;j++){
                xt=this.rchannel.get(j);
                ComplexNumber c= new ComplexNumber(xt*(Math.cos(2*Math.PI*i*j/SAMPLES_PER_SECOND/this.samples)),-xt*(Math.sin(2*Math.PI*i*j/SAMPLES_PER_SECOND/this.samples)));
                Sum=Sum.add(c);
            }
            tempAmp=Sum.magnitude();
            if(tempAmp>=maxAmp){
                maxAmp=tempAmp;
                Kval=i;
            }
        }
        return (float)Kval*SAMPLES_PER_SECOND/samples; // change this
    }

    /**
     * Determine if this wave fully contains the other sound wave as a pattern.
     *
     * @param other is the wave to search for in this wave.
     * @return true if the other wave is contained in this after amplitude scaling,
     * and false if the other wave is not contained in this with any
     * possible amplitude scaling.
     */
    public boolean contains(SoundWave other) {
        // TODO: Implement this method
        int len=0;
        for(int i=0;i<this.lchannel.size()||i<this.rchannel.size();i++){

            if((this.lchannel.get(i)==other.lchannel.get(0))||(this.rchannel.get(i)==other.rchannel.get(0))) {
                for(int j=1;j<other.lchannel.size()||j<other.rchannel.size();j++){
                    len++;
                    if((this.lchannel.get(i+j) !=other.lchannel.get(j))||(this.rchannel.get(i+j)!=other.rchannel.get(j))){
                        return false;
                    }
                }
            }
        }
       if(len==other.lchannel.size()||len==other.rchannel.size()) {
           return true;
       }
        else {
            return false; // change this
       }
    }

    /**
     * Determine the similarity between this wave and another wave.
     * The similarity metric, gamma, is the sum of squares of
     * instantaneous differences.
     *
     * @param other is not null.
     * @return the similarity between this wave and other.
     */
    public double similarity(SoundWave other) {
        // TODO: Implement this method
        return -1;
    }

    /**
     * Play this wave on the standard stereo device.
     */
    public void sendToStereoSpeaker() {
        // You may not need to change this...
        double[] lchannel = this.lchannel.stream().mapToDouble(x -> x.doubleValue()).toArray();
        double[] rchannel = this.rchannel.stream().mapToDouble(x -> x.doubleValue()).toArray();
        StdPlayer.playWave(lchannel, rchannel);
    }

}
