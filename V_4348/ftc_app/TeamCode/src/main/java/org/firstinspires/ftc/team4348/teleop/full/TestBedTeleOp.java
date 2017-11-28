package org.firstinspires.ftc.team4348.teleop.full;

import org.firstinspires.ftc.team4348.robots.TestBedBot;
import org.firstinspires.ftc.team4348.teleop.CustomTeleOp;


public class TestBedTeleOp extends CustomTeleOp
{
    TestBedBot bot = new TestBedBot();

    @Override
    public void init() {
        bot.init(hardwareMap);
    }

    @Override
    public void loop()
    {
        //joysticks
        double leftPower = gamepad1.left_stick_y;
        double rightPower = gamepad1.right_stick_y;

        //drive motors
        //left
        if(Math.abs(leftPower) > JOYSTICK_THRESHOLD) {
            leftPower = scaleInput(leftPower);
            bot.leftMotor.setPower(leftPower);
        }
        else {
            bot.leftMotor.setPower(0);
        }

        //right
        if(Math.abs(rightPower) > JOYSTICK_THRESHOLD) {
            rightPower = scaleInput(rightPower);
            bot.rightMotor.setPower(rightPower);
        }
        else {
            bot.rightMotor.setPower(0);
        }


        //middle
        if(gamepad1.dpad_left) {
            bot.middleMotor.setPower(-1);
        }
        else if(gamepad1.dpad_right) {
            bot.middleMotor.setPower(1);
        }
        else {
            bot.middleMotor.setPower(0);
        }
    }
}
