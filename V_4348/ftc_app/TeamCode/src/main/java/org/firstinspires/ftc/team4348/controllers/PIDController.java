package org.firstinspires.ftc.team4348.controllers;

import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Created by Evyn on 10/3/2017.
 */

public class PIDController
{
    ADAFruitIMU imu = null;
    PIDCoefficients pidc = null;
    final double ACC_ERR = 0.2;

    public Thread PIDThread = null;
    boolean isRunning = false;
    public double target;
    public double output;
    public final int EXTRA_CYCLES = 100;
    public int cycles = 0;

    public PIDController(ADAFruitIMU imu, double p, double i, double d)
    {
        this.imu = imu;
        pidc = new PIDCoefficients(p, i, d);
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

    public void setTarget(double value)
    {
        target = imu.normalizeInput(value);
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
        double error = target - getValue();
        if(error < -180) {
            error += 360;
        }
        else if(error > 180) {
            error -= 360;
        }
        return error;
    }

    public double getValue()
    {
        return imu.normalizeInput(imu.getValue());
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

            do {
                currErr = Math.abs(getError());

                if(currErr < ACC_ERR) { //if current errror < acceptable error
                    cycles++; //add counter for extra cycles
                }

                totErr += currErr;
                dTime = (System.nanoTime() - prevTime) * 10E-9; //convert from nanosec -> sec

                double p = pidc.p * currErr;
                double i = pidc.i * (totErr * dTime);
                double d = (pidc.d * currErr) / dTime;

                output = p + i + d;

                prevTime += dTime;
            }while(isRunning);
        }
    }
}
