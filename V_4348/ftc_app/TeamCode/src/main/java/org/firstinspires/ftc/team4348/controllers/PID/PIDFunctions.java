package org.firstinspires.ftc.team4348.controllers.PID;

import org.firstinspires.ftc.team4348.utils.Direction;
import org.firstinspires.ftc.team4348.robots.Bot;
import org.firstinspires.ftc.team4348.utils.StatusChecker;

import static org.firstinspires.ftc.team4348.utils.Utilities.scalePower;

/**
 * Created by RoboKnights on 11/20/2017.
 */

public class PIDFunctions
{
    Bot bot;
    PIDController pidController;

    public PIDFunctions(Bot bot, PIDController pidController)
    {
        this.bot = bot;
        this.pidController = pidController;
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

            //scale to make sure not over 1.0 max
            double[] powers = scalePower(lPower, rPower);

            bot.setDrivePower(powers[0], powers[1]);
        }
        bot.setDrivePower(0,0);
        pidController.stop();
    }

    public void goToAngle(double angle, StatusChecker statusChecker)
    {
        pidController.setTarget(angle);
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
            double lPower = left * Math.abs(pidController.getOutput());
            double rPower = right * Math.abs(pidController.getOutput());

            //scale to make sure not over 1.0 max
            double[] powers = scalePower(lPower, rPower);

            bot.setDrivePower(powers[0], powers[1]);
        }
        bot.setDrivePower(0,0);
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

            double[] powers = scalePower(lPower, rPower);
            bot.setDrivePower(powers[0], powers[1]);
        }
        bot.setDrivePower(0,0);
        pidController.stop();
    }

    public static class PIDStraightThread
    {
        PIDFunctions func;
        public Thread thread;
        boolean isRunning = false;
        double power;

        public PIDStraightThread(PIDFunctions func, double power)
        {
            this.func = func;
            thread = new Thread(new PIDStraightRunnable());
            this.power = power;
        }

        class PIDStraightRunnable implements Runnable
        {
           RunningChecker rChecker = new RunningChecker();
            @Override
            public void run() {
                func.straight(power, rChecker);
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
}