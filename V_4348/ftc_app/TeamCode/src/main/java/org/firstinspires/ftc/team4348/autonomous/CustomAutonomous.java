package org.firstinspires.ftc.team4348.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team4348.constants.Direction;
import org.firstinspires.ftc.team4348.controllers.PIDController;
import org.firstinspires.ftc.team4348.robots.Bot;
import org.firstinspires.ftc.team4348.utils.StatusChecker;

/**
 * Created by evynm on 10/3/2017.
 */

public abstract class CustomAutonomous extends LinearOpMode
{
    Bot bot = null;
    private final Object monitor = new Object();
    PIDController pidController = null;

    public void setBot(Bot bot)
    {
        this.bot = bot;
    }

    public void setPidController(PIDController controller) {this.pidController = pidController;}

    public void delay(long millis)
    {
        try {
            monitor.wait(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setPower(double leftPower, double rightPower)
    {
        for(DcMotor d : bot.leftMotors)
        {
            d.setPower(leftPower);
        }
        for(DcMotor d : bot.rightMotors)
        {
            d.setPower(rightPower);
        }
    }

    public void setPowerT(double leftPower, double rightPower, long time)
    {
        setPower(leftPower, rightPower);
        delay(time);
        setPower(0,0);
    }

    public double scalePower(double power)
    {
        if(power > bot.MAX_SPEED) {
            power = bot.MAX_SPEED;
        }
        else if(power < bot.MAX_SPEED) {
            power = -bot.MAX_SPEED;
        }

        return power;
    }

    public void straight(double power, StatusChecker statusChecker)
    {
        pidController.setTarget(pidController.getValue());
        pidController.start();
        while(statusChecker.checkStatus())
        {
            //find direction
            double raw = pidController.getError();

            double left = 1;
            double right = 1;

            if(raw > 0) {
                right = -1;
            }
            else if(raw < 0) {
                left = -1;
            }

            //set power
            double lPower = power + left * Math.abs(pidController.getOutput());
            double rPower = power + right * Math.abs(pidController.getOutput());

            lPower = scalePower(lPower);
            rPower = scalePower(rPower);

            setPower(lPower, rPower);
        }
        setPower(0,0);
        pidController.stop();
    }

    public void turn(Direction dir, double angle, StatusChecker sChecker)
    {
        pidController.setTarget(angle);
        pidController.start();
        while(sChecker.checkStatus())
        {
            double lPower = dir.v * Math.abs(pidController.getOutput());
            double rPower = -dir.v * Math.abs(pidController.getOutput());
            setPower(lPower, rPower);
        }
        setPower(0,0);
        pidController.stop();
    }

    public class PIDStraightThread
    {
        public Thread thread;
        boolean isRunning = false;
        double power;

        public PIDStraightThread(double power)
        {
            thread = new Thread(new PIDStraightRunnable());
            this.power = power;
        }

        class PIDStraightRunnable implements Runnable
        {
            RunningChecker rChecker = new RunningChecker();
            @Override
            public void run() {
                straight(power, rChecker);
            }
        }

        public void setRunning(boolean nValue)
        {
            isRunning = nValue;
        }

        class RunningChecker extends StatusChecker
        {
            @Override
            public boolean checkStatus() {
                return isRunning;
            }
        }
    }

    public class TimeChecker extends StatusChecker
    {
        long endTime;
        public TimeChecker(long time)
        {
            this.endTime = System.currentTimeMillis() + time;
        }

        @Override
        public boolean checkStatus() {
            return System.currentTimeMillis() < endTime;
        }
    }

    public class CycleChecker extends StatusChecker
    {
        PIDController controller;
        int extraCycles;

        public CycleChecker(PIDController pController, int extraCycles)
        {
            this.controller = pController;
            this.extraCycles = extraCycles;
        }

        @Override
        public boolean checkStatus() {
            return pidController.cycles < extraCycles;
        }
    }
}
