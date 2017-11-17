package org.firstinspires.ftc.team4348.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.team4348.constants.Direction;
import org.firstinspires.ftc.team4348.controllers.PIDController;
import org.firstinspires.ftc.team4348.robots.Bot;

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
        if(Math.abs(power) > bot.MAX_SPEED) {
            if(power < 0) {
                power = -bot.MAX_SPEED;
            }
            else {
                power = bot.MAX_SPEED;
            }
        }

        return power;
    }

    public void straight(double power, long time)
    {
        pidController.setTarget(pidController.getValue());
        long endTime = time + System.currentTimeMillis();
        pidController.start();
        while(System.currentTimeMillis() < endTime)
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

    public void turn(Direction dir, double angle)
    {
        pidController.setTarget(angle);
        pidController.start();
        while(pidController.cycles < pidController.EXTRA_CYCLES)
        {
            double lPower = dir.v * Math.abs(pidController.getOutput());
            double rPower = -dir.v * Math.abs(pidController.getOutput());
            setPower(lPower, rPower);
        }
        setPower(0,0);
        pidController.stop();
    }
}
