package org.firstinspires.ftc.team4348.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team4348.constants.Direction;
import org.firstinspires.ftc.team4348.controllers.PIDController;
import org.firstinspires.ftc.team4348.robots.Bot;
import org.firstinspires.ftc.team4348.utils.StatusChecker;

import static org.firstinspires.ftc.team4348.utils.Utilities.scalePower;

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
        int extraCycles;

        public CycleChecker(int extraCycles)
        {
            this.extraCycles = extraCycles;
        }

        @Override
        public boolean checkStatus() {
            return pidController.cycles < extraCycles;
        }
    }
}
