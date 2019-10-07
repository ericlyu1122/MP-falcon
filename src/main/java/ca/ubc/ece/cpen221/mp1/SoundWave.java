package ca.ubc.ece.cpen221.mp1;

import ca.ubc.ece.cpen221.mp1.utils.ComplexNumber;
import ca.ubc.ece.cpen221.mp1.utils.HasSimilarity;
import javazoom.jl.player.StdPlayer;

import java.util.ArrayList;

import java.util.*;
import java.lang.*;


public class SoundWave implements HasSimilarity<SoundWave>  ,Comparable<SoundWave> {

    // We are going to treat the number of samples per second of a sound wave
    // as a constant.
    // The best way to refer to this constant is as
    // SoundWave.SAMPLES_PER_SECOND.
    public static final int SAMPLES_PER_SECOND = 44100;

    // some representation fields that you could use
    private ArrayList<Double> lchannel = new ArrayList<>();
    private ArrayList<Double> rchannel = new ArrayList<>();
    private double[] beta = new double[1];


    private int samples = 0;
    private int samplesL = 0;

    /**
     * Create a new SoundWave using the provided left and right channel
     * amplitude values. After the SoundWave is created, changes to the
     * provided arguments will not affect the SoundWave.
     *
     * @param lchannel is not null and represents the left channel.
     * @param rchannel is not null and represents the right channel.
     */
    public SoundWave(double[] lchannel, double[] rchannel) {

        for (double v : lchannel) {
            this.lchannel.add(v);
        }
        for (double v : rchannel) {
            this.rchannel.add(v);
        }
        this.samples = this.rchannel.size();
        this.samplesL = this.lchannel.size();
    }

    public SoundWave() {
        this.samples = 0;
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
        samples = (int) duration * SAMPLES_PER_SECOND;
        for (int i = 0; i <= samples; i++) {
            double sin = Math.sin(2 * Math.PI * freq * i / SAMPLES_PER_SECOND + phase);
            this.lchannel.add(amplitude * sin);
            this.rchannel.add(amplitude * sin);
        }
    }

    /**
     * Obtain the left channel for this wave.
     * Changes to the returned array should not affect this SoundWave.
     *
     * @return an array that represents the left channel for this wave.
     */
    public double[] getLeftChannel() {
        double[] leftChannel = new double[lchannel.size()];
        for (int i = 0; i < lchannel.size(); i++) {
            leftChannel[i] = lchannel.get(i);
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
        double[] rightChannel = new double[rchannel.size()];
        for (int i = 0; i < rchannel.size(); i++) {
            rightChannel[i] = rchannel.get(i);
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
        for (double value : lchannel) {
            this.lchannel.add(value);
        }
        for (double v : rchannel) {
            this.rchannel.add(v);
        }
    }

    /**
     * Append a wave to this wave.
     *
     * @param other the wave to append.
     */
    public void append(SoundWave other) {
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

        SoundWave Addwave = new SoundWave();

        for (int i = 0; i < other.lchannel.size() ||
                (i < this.lchannel.size()); i++) {
            if (i >= other.lchannel.size() || i >= this.lchannel.size()) {
                if (other.lchannel.size() > this.lchannel.size()) {
                    this.lchannel.add(other.lchannel.get(i));
                    this.rchannel.add(other.rchannel.get(i));
                }
            } else {
                if (this.lchannel.get(i) + other.lchannel.get(i) <= 1 && this.lchannel.get(i) + other.lchannel.get(i) >= -1) {
                    this.lchannel.set(i, this.lchannel.get(i) + other.lchannel.get(i));
                    this.rchannel.set(i, this.rchannel.get(i) + other.rchannel.get(i));
                } else if (this.lchannel.get(i) + other.lchannel.get(i) > 1) {
                    this.lchannel.set(i, 1.0);
                    this.rchannel.set(i, 1.0);
                } else {
                    this.lchannel.set(i, -1.0);
                    this.rchannel.set(i, -1.0);
                }
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

        if (delta < 0 || alpha < 0) {
            throw new IllegalArgumentException();
        } else {
            int temp;

            double[] rchan = new double[this.samples + delta];
            double[] lchan = new double[this.samples + delta];
            for (int i = 0; i < (this.samples + delta); i++) {
                temp = i - delta;
                if (i < delta && i < this.samples) {
                    rchan[i] = this.rchannel.get(i);
                    lchan[i] = this.lchannel.get(i);
                } else if (i >= delta && i < this.samples && temp >= 0) {
                    if (this.rchannel.get(i) + this.rchannel.get(temp) * alpha <= 1.0
                            && this.rchannel.get(i) + this.rchannel.get(temp) * alpha >= -1.0) {
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
        scaleChannel(this.lchannel, scalingFactor);
        scaleChannel(this.rchannel, scalingFactor);
    }


    private void scaleChannel(List<Double> channel, double scalingFac) {
        for (int i = 0; i < channel.size(); i++) {
            channel.set(i, channel.get(i) * scalingFac);
            if (channel.get(i) > 1) {
                channel.set(i, 1.0);
            } else if (channel.get(i) < -1) {
                channel.set(i, -1.0);
            }
        }
    }

    /**
     * Return a new sound wave that is obtained by applying a high-pass filter to
     * this wave.
     *
     * @param dt >= 0. dt is the time interval used in the filtering process.
     * @param RC > 0. RC is the time constant for the high-pass filter.
     * @return HPS Sound wave
     */
    public SoundWave highPassFilter(int dt, double RC) throws IllegalArgumentException {

        if (dt < 0 || RC <= 0) {
            throw new IllegalArgumentException();
        } else {
            SoundWave highPass = new SoundWave();
            double alpha = RC / (RC + dt);
            double[] rchan = new double[this.rchannel.size()];
            double[] lchan = new double[this.lchannel.size()];
            for (int j = 0; j < rchannel.size(); j++) {
                rchan[j] = this.rchannel.get(j);
                lchan[j] = this.lchannel.get(j);
            }

            for (int i = 1; i < rchannel.size(); i++) {
                // y[i] := α * y[i-1] + α * (x[i] - x[i-1])
                this.rchannel.set(i, alpha * this.rchannel.get(i - 1) + alpha * (rchan[i] - rchan[i - 1]));
                this.lchannel.set(i, alpha * this.lchannel.get(i - 1) + alpha * (lchan[i] - lchan[i - 1]));

            }
            highPass.rchannel.addAll(this.rchannel);
            highPass.lchannel.addAll(this.lchannel);

            return highPass;
        }// change this
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

        double tempAmpR1;
        double tempAmpL1;
        double temp;
        double maxAmp = 0.0;
        int Kval = 0;
        int i;

        for (i = 0; i < this.samples; i++) {
            ComplexNumber SumR = new ComplexNumber(0.0, 0.0);
            ComplexNumber SumL = new ComplexNumber(0.0, 0.0);
            for (int j = 0; j < this.samples; j++) {

                ComplexNumber cR = new ComplexNumber(this.rchannel.get(j), 0.0);
                ComplexNumber cR1 = new ComplexNumber(Math.cos(2 * Math.PI * i * j / this.samples), -Math.sin(2 * Math.PI * i * j / this.samples));
                ComplexNumber R = cR.multiply(cR1);
                SumR = SumR.add(R);

                ComplexNumber cL = new ComplexNumber(this.lchannel.get(j), 0.0);
                ComplexNumber cL1 = new ComplexNumber(Math.cos(2 * Math.PI * i * j / this.samples), -Math.sin(2 * Math.PI * i * j / this.samples));
                ComplexNumber L = cL.multiply(cL1);
                SumL = SumL.add(L);
            }
            tempAmpR1 = Math.sqrt(SumR.real() * SumR.real() + SumR.imaginary() * SumR.imaginary()) / samples;
            tempAmpL1 = Math.sqrt(SumL.real() * SumL.real() + SumL.imaginary() * SumL.imaginary()) / samples;
            double tempAmpL = Math.round((float) tempAmpL1 * 10.0) / 10.0;
            double tempAmpR = Math.round((float) tempAmpR1 * 10.0) / 10.0;
            if (Math.abs(tempAmpL) >= Math.abs(tempAmpR)) {
                temp = Math.abs(tempAmpL);
            } else {
                temp = Math.abs(tempAmpR);
            }
            if (temp >= maxAmp) {
                maxAmp = temp;
                Kval = i;
            }
        }
        return (float) Kval * SAMPLES_PER_SECOND / samples; // change this
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

        int tempR = 0, tempL = 0;
        boolean state1 = false;
        boolean state2 = false;
        double factorL = 0, factorR = 0;
        int i;
        for (i = 0; i <= this.lchannel.size() - other.lchannel.size(); i++) {
            int lenL = 0;
            for (int g = 0; g < other.lchannel.size() && i + g < this.lchannel.size(); g++) {
                factorL = 0;
                if (other.lchannel.get(g) != 0) {
                    factorL = this.lchannel.get(i + g) / other.lchannel.get(g);

                } else if (other.lchannel.get(g) == 0) {
                    factorL = 0;
                }
                double[] R = new double[other.rchannel.size()];
                double[] L = new double[other.lchannel.size()];
                for (int j = 0; j < other.lchannel.size(); j++) {

                    L[j] = other.lchannel.get(j);
                }
                for (int f = 0; f < other.rchannel.size(); f++) {
                    R[f] = other.rchannel.get(f);
                }

                SoundWave LS = new SoundWave(L, R);

                LS.scale(factorL);
                for (int o = 0; o < other.lchannel.size(); o++) {
                    double numx = LS.lchannel.get(o);
                    double scale = Math.pow(10, 3);
                    numx = Math.round(numx * scale) / scale;
                    LS.lchannel.set(o, numx);
                }
                lenL = 0;
                for (int k = 0; k < other.lchannel.size(); k++) {
                    double numL = this.lchannel.get(i + k);
                    double numL1 = LS.lchannel.get(k);

                    if (numL == numL1) {
                        lenL += 1;
                    }

                }

                if (lenL == other.lchannel.size()) {
                    state1 = true;
                    tempL = i;
                    break;
                }
            }
            if (state1) {
                break;
            }
        }
        //
        int v;
        for (v = 0; v <= this.rchannel.size() - other.rchannel.size(); v++) {
            int lenR = 0;
            for (int p = 0; p < other.rchannel.size() && v + p < this.rchannel.size(); p++) {
                factorR = 0;
                if (other.rchannel.get(p) != 0) {

                    factorR = this.rchannel.get(v + p) / other.rchannel.get(p);
                } else if (other.rchannel.get(p) == 0) {

                    factorR = 0;


                }
                double[] R = new double[other.rchannel.size()];
                double[] L = new double[other.lchannel.size()];
                for (int j = 0; j < other.rchannel.size(); j++) {
                    R[j] = other.rchannel.get(j);

                }
                for (int h = 0; h < other.lchannel.size(); h++) {
                    L[h] = other.lchannel.get(h);

                }
                SoundWave RS = new SoundWave(L, R);

                RS.scale(factorR);
                for (int u = 0; u < other.rchannel.size(); u++) {
                    double numk = RS.rchannel.get(u);
                    double scale = Math.pow(10, 3);
                    numk = Math.round(numk * scale) / scale;
                    RS.rchannel.set(u, numk);
                }
                lenR = 0;
                for (int k = 0; k < other.rchannel.size(); k++) {

                    double numR = this.rchannel.get(v + k);
                    double numR1 = RS.rchannel.get(k);

                    if (numR == numR1) {
                        lenR += 1;
                    }
                }

                if (lenR == other.rchannel.size()) {
                    state2 = true;
                    tempR = v;
                    break;
                }
            }
            if (state2) {
                break;
            }
        }
        if (state1 && state2 && tempL == tempR) {
            this.beta[0] = factorR;
            return true;
        }
        return false;
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
        double gama1, gama2;
        gama1 = this.sim(other);
        gama2 = other.sim(this);
        if (this.contains(other) && other.contains(this)) {
            return 1.0;
        } else {
            return (gama1 + gama2) / 2.0;
        }
    }

    /**
     * Determine the similarity between waves (either this wave or another wave).
     * @param other .
     * @return the similarity between waves
     */

    public double sim(SoundWave other) {
        double betaPro = 0;
        double temp = 0;
        double gamma = 0;
        double upper = 0.0, lower = 0.0;
        if (this.rchannel.size() >= other.rchannel.size()) {
            double tempL = 0.0, tempR = 0.0;
            if (this.contains(other)) {
                betaPro = this.beta[0];


                for (int j = 0; j < this.lchannel.size(); j++) {
                    if (j < other.lchannel.size()) {
                        tempL += (this.lchannel.get(j) - betaPro * other.lchannel.get(j)) * (this.lchannel.get(j) - betaPro * other.lchannel.get(j));
                    } else {
                        tempL += this.lchannel.get(j) * this.lchannel.get(j);
                    }
                }
                for (int k = 0; k < this.rchannel.size(); k++) {
                    if (k < other.rchannel.size()) {
                        tempR += (this.rchannel.get(k) - betaPro * other.rchannel.get(k)) * (this.rchannel.get(k) - betaPro * other.rchannel.get(k));
                    } else {
                        tempR += this.rchannel.get(k) * this.rchannel.get(k);
                    }
                }


            } else {
                if (this.rchannel.size() >= this.lchannel.size()) {
                    for (int m = 0; m < this.rchannel.size(); m++) {
                        if (m >= other.lchannel.size()) {
                            other.lchannel.add(0.0);
                        }
                        if (m >= other.rchannel.size()) {
                            other.rchannel.add(0.0);
                        }
                        if (m >= this.lchannel.size()) {
                            this.lchannel.add(0.0);
                        }
                    }
                } else {
                    for (int n = 0; n < this.lchannel.size(); n++) {
                        if (n >= other.lchannel.size()) {
                            other.lchannel.add(0.0);
                        }
                        if (n >= other.rchannel.size()) {
                            other.rchannel.add(0.0);
                        }
                        if (n >= this.rchannel.size()) {
                            this.rchannel.add(0.0);
                        }
                    }
                }
                for (int y = 0; y < this.lchannel.size(); y++) {
                    upper += this.rchannel.get(y) * other.rchannel.get(y) + this.lchannel.get(y) * other.lchannel.get(y);
                    lower += other.rchannel.get(y) * other.rchannel.get(y) + other.lchannel.get(y) * other.lchannel.get(y);
                }
                if (lower != 0) {
                    betaPro = upper / lower;

                    for (int j = 0; j < this.lchannel.size(); j++) {

                        tempL += (this.lchannel.get(j) - betaPro * other.lchannel.get(j)) * (this.lchannel.get(j) - betaPro * other.lchannel.get(j));

                    }
                    for (int k = 0; k < this.rchannel.size(); k++) {

                        tempR += (this.rchannel.get(k) - betaPro * other.rchannel.get(k)) * (this.rchannel.get(k) - betaPro * other.rchannel.get(k));
                    }

                }
                if (lower == 0) {
                    for (int j = 0; j < this.lchannel.size(); j++) {

                        tempL += (this.lchannel.get(j)) * (this.lchannel.get(j));

                    }
                    for (int k = 0; k < this.rchannel.size(); k++) {

                        tempR += (this.rchannel.get(k)) * (this.rchannel.get(k));
                    }
                }

            }
            temp += tempL + tempR;
            gamma = 1 / (1 + temp);
        } else {
            double tempL = 0.0, tempR = 0.0;
            if (other.contains(this)) {
                betaPro = 1.0 / other.beta[0];
                if (other.beta[0] == 0) {
                    return 1.0;
                }

                for (int j = 0; j < other.lchannel.size(); j++) {
                    if (j < this.lchannel.size()) {
                        tempL += (this.lchannel.get(j) - betaPro * other.lchannel.get(j)) * (this.lchannel.get(j) - betaPro * other.lchannel.get(j));
                    } else {
                        tempL += betaPro * other.lchannel.get(j) * betaPro * other.lchannel.get(j);
                    }
                }
                for (int k = 0; k < other.rchannel.size(); k++) {
                    if (k < this.rchannel.size()) {
                        tempR += (this.rchannel.get(k) - betaPro * other.rchannel.get(k)) * (this.rchannel.get(k) - betaPro * other.rchannel.get(k));
                    } else {
                        tempR += betaPro * other.rchannel.get(k) * betaPro * other.rchannel.get(k);
                    }
                }


            } else {
                if (other.rchannel.size() >= other.lchannel.size()) {
                    for (int m = 0; m < other.rchannel.size(); m++) {
                        if (m >= other.lchannel.size()) {
                            other.lchannel.add(0.0);
                        }
                        if (m >= this.rchannel.size()) {
                            this.rchannel.add(0.0);
                        }
                        if (m >= this.lchannel.size()) {
                            this.lchannel.add(0.0);
                        } else {
                            for (int n = 0; n < other.lchannel.size(); n++) {
                                if (n >= other.rchannel.size()) {
                                    other.lchannel.add(0.0);
                                }
                                if (n >= this.rchannel.size()) {
                                    this.rchannel.add(0.0);
                                }
                                if (n >= this.lchannel.size()) {
                                    this.lchannel.add(0.0);
                                }
                            }
                        }
                        for (int y = 0; y < this.lchannel.size(); y++) {
                            upper += this.rchannel.get(y) * other.rchannel.get(y) + this.lchannel.get(y) * other.lchannel.get(y);
                            lower += other.rchannel.get(y) * other.rchannel.get(y) + other.lchannel.get(y) * other.lchannel.get(y);
                        }
                        if (lower != 0) {
                            betaPro = upper / lower;

                            for (int j = 0; j < this.lchannel.size(); j++) {

                                tempL += (this.lchannel.get(j) - betaPro * other.lchannel.get(j)) * (this.lchannel.get(j) - betaPro * other.lchannel.get(j));

                            }
                            for (int k = 0; k < this.rchannel.size(); k++) {

                                tempR += (this.rchannel.get(k) - betaPro * other.rchannel.get(k)) * (this.rchannel.get(k) - betaPro * other.rchannel.get(k));
                            }

                        }
                        if (lower == 0) {
                            for (int j = 0; j < this.lchannel.size(); j++) {

                                tempL += (this.lchannel.get(j)) * (this.lchannel.get(j));

                            }
                            for (int k = 0; k < this.rchannel.size(); k++) {

                                tempR += (this.rchannel.get(k)) * (this.rchannel.get(k));
                            }
                        }
                    }
                    temp += tempL + tempR;
                    gamma = 1 / (1 + temp);
                }
            }
        }
        return gamma;

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

    @Override
    public int compareTo(SoundWave soundWave) {
        if (this.samples==soundWave.samples){
         for (int i=0;i<this.lchannel.size();i++){
             if((!this.lchannel.get(i).equals(soundWave.lchannel.get(i)))
                     ||(!this.rchannel.get(i).equals(soundWave.rchannel.get(i)))){
                 return 1;
             }
            }
        }
        return 0;
    }
    public boolean equals(SoundWave soundWave){return this.compareTo(soundWave)==0;}
}
