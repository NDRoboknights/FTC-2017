package org.firstinspires.ftc.team4348.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.team4348.controllers.PIDController;
import org.firstinspires.ftc.team4348.robots.Bot;

/**
 * Created by evynm on 10/3/2017.
 */

public abstract class CustomAutonomous extends LinearOpMode
{
    Bot bot = null;
    private final Object monitor = new Object();

    public void setBot(Bot bot)
    {
        this.bot = bot;
    }

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
}
