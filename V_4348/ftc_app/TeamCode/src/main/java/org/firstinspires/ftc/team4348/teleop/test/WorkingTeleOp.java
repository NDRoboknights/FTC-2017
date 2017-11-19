package org.firstinspires.ftc.team4348.teleop.test;

import org.firstinspires.ftc.team4348.robots.WorkingBot;
import org.firstinspires.ftc.team4348.teleop.CustomTeleOp;
import org.firstinspires.ftc.team4348.utils.Utilities;

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
        double lPower = gamepad1.left_stick_y;
        double rPower = gamepad1.right_stick_y;

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

        if(gamepad1.dpad_left) {
            bot.middleMotor.setPower(-1);
        }
        else if(gamepad1.dpad_right) {
            bot.middleMotor.setPower(1);
        }
        else {
            bot.middleMotor.setPower(0);
        }

        //ball system
        if(gamepad1.right_trigger >= 0.5) {
            bot.runIntakeMotors(1);
        }
        else if(gamepad1.left_trigger >= 0.5) {
            bot.runIntakeMotors(-1);
        }
        else {
            bot.runIntakeMotors(0);
        }

        if(gamepad1.right_bumper) {
            bot.runUpMotors(1);
        }
        else if(gamepad1.left_bumper) {
            bot.runUpMotors(-1);
        }
        else {
            bot.runUpMotors(0);
        }
    }
}
