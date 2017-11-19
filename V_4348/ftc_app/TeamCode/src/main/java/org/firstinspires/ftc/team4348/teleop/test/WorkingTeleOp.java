package org.firstinspires.ftc.team4348.teleop.test;

import org.firstinspires.ftc.team4348.robots.WorkingBot;
import org.firstinspires.ftc.team4348.teleop.CustomTeleOp;

/**
 * Created by RoboKnights on 11/18/2017.
 */

public class WorkingTeleOp extends CustomTeleOp
{
    WorkingBot bot = new WorkingBot();

    @Override
    public void init()
    {
        bot.init(hardwareMap);
        bot.jewelServo.setPosition(bot.JEWEL_INIT_POS);
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
        if(gamepad1.right_trigger >= 0.5) {
            bot.intakeMotors.setPower(0.9);
        }
        else if(gamepad1.left_trigger >= 0.5) {
            bot.intakeMotors.setPower(-0.9);
        }
        else {
            bot.intakeMotors.setPower(0);
        }

        if(gamepad1.right_bumper) {
            bot.upDownMotors.setPower(1);
        }
        else if(gamepad1.left_bumper) {
            bot.upDownMotors.setPower(-1);
        }
        else {
            bot.upDownMotors.setPower(0);
        }
    }
}
