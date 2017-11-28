package org.firstinspires.ftc.team4348.teleop.full;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team4348.robots.WorkingBot;
import org.firstinspires.ftc.team4348.teleop.CustomTeleOp;


@TeleOp(name="WorkingTele")
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
        //joysticks
        double lPower = -gamepad1.left_stick_y;
        double rPower = -gamepad1.right_stick_y;

        //drive motors
        //left
        if(Math.abs(lPower) > JOYSTICK_THRESHOLD) {
            lPower = scaleInput(lPower);
            bot.leftMotor.setPower(lPower);
        }
        else {
            bot.leftMotor.setPower(0);
        }

        //right
        if(Math.abs(rPower) > JOYSTICK_THRESHOLD) {
            rPower = scaleInput(rPower);
            bot.rightMotor.setPower(rPower);
        }
        else {
            bot.rightMotor.setPower(0);
        }


        //intakes
        if(gamepad1.right_trigger >= 0.5) {
            bot.runIntakeMotors(-1);
        }
        else if(gamepad1.left_trigger >= 0.5) {
            bot.runIntakeMotors(1);
        }
        else {
            bot.runIntakeMotors(0);
        }


        //up down of intake
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