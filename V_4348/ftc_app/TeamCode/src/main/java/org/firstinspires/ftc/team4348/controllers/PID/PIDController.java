package org.firstinspires.ftc.team4348.controllers.PID;

import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.team4348.controllers.ADAFruitIMU;

/**
 * Created by Evyn on 10/3/2017.
 */

public class PIDController
{
    PIDInput pidInput = null;
    PIDCoefficients pidc = null;
    final double ACC_ERR = 0.2;

    public Thread PIDThread = null;
    boolean isRunning = false;
    public double target;
    public double output;
    public int cycles = 0;
    public final int D_EXTRACYCLES = 100;

    public PIDController(PIDInput imu, PIDCoefficients pidc)
    {
        this.pidInput = imu;
        this.pidc = pidc;
    }

    public void start()
    {
        if(!isRunning) {
            PIDThread = new Thread(new PIDRunnable());
            isRunning = true;
            PIDThread.start();
        }
        else {
            stop();
            start();
        }
    }

    public void stop()
    {
        isRunning = false;
        PIDThread = null;
    }

    public double getValue()
    {
        return pidInput.getValue();
    }

    public void setTarget(double value)
    {
        target = pidInput.normalizeValue(value);
    }

    public double getOutput()
    {
        return output;
    }

    public void setPidc(double p, double i, double d)
    {
        pidc = new PIDCoefficients(p, i, d);
    }

    public double getError()
    {
        double error = target - pidInput.getValue();
        return pidInput.normalizeError(error);
    }

    public class PIDRunnable implements Runnable
    {
        @Override
        public void run()
        {
            cycles = 0; //cycle counter
            double currErr; //current error
            double totErr = 0; //total error
            double prevTime = System.nanoTime() * 10E-9; //starting time
            double dTime; //change in time

            while(isRunning)
            {
                currErr = Math.abs(getError());

                if(currErr < ACC_ERR) { //if current errror < acceptable error
                    cycles++; //add counter for extra cycles
                }

                totErr += currErr;
                dTime = (System.nanoTime() * 10E-9) - prevTime; //convert from nanosec -> sec

                double p = pidc.p * currErr;
                double i = pidc.i * totErr * dTime;
                double d = (pidc.d * currErr) / dTime;

                output = p + i + d;

                prevTime += dTime;
            }
        }
    }
}
