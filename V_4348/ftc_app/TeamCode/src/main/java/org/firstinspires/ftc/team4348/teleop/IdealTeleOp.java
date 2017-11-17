package org.firstinspires.ftc.team4348.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.internal.android.dx.cf.code.Machine;
import org.firstinspires.ftc.team4348.robots.IdealBot;

/**
 * Created by RoboKnights on 11/13/2017.
 */

@TeleOp(name="Real: Ideal",group="Real")
public class IdealTeleOp extends CustomTeleOp
{
    IdealBot bot = new IdealBot();
    private double armPos = .5;

    @Override
    public void init()
    {
        bot.init(hardwareMap);
        bot.clawServo.setPosition(armPos);
    }

    @Override
    public void loop()
    {
        //drive
        double lPower = -gamepad1.left_stick_y;
        double rPower = -gamepad1.right_stick_y;

        if(Math.abs(lPower) > JOYSTICK_THRESHOLD) {
            lPower = scaleInput(lPower);
            bot.leftMotor.setPower(lPower);
        }
        else {
            bot.leftMotor.setPower(0);
        }
        if(Math.abs(rPower) > JOYSTICK_THRESHOLD) {
            rPower = scaleInput(rPower);
            bot.rightMotor.setPower(rPower);
        }
        else {
            bot.rightMotor.setPower(0);
        }

        //arm
        if(gamepad1.a)
        {
            if (armPos != 0) {
                armPos -= .01;
            }
        }
        if(gamepad1.b)
        {
            if (armPos != 1) {
                armPos += .01;
            }
        }

        if(gamepad1.x)
        {
            bot.armMotor.setPower(.25);
        } else if(gamepad1.y)
        {
            bot.armMotor.setPower(-.25);
        } else {
            bot.armMotor.setPower(0);
        }

        bot.clawServo.setPosition(armPos);
    }
}
