package org.firstinspires.ftc.team4348.teleop.full;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team4348.robots.TestBedBot;
import org.firstinspires.ftc.team4348.teleop.CustomTeleOp;
import org.firstinspires.ftc.team4348.utils.Utilities;

@TeleOp(name="TestBed: Tele")
public class TestBedTeleOp extends CustomTeleOp
{
    TestBedBot bot = new TestBedBot();
    long prevTime = 0;
    int lPrevPos = 0;
    int rPrevPos = 0;

    @Override
    public void init()
    {
        bot.init(hardwareMap);
    }

    @Override
    public void loop()
    {
        long currTime = System.nanoTime();
        int lPos = bot.leftMotor.getCurrentPosition();
        int rPos = bot.rightMotor.getCurrentPosition();

        double dTime = (currTime - prevTime) * 10E-9;
        double dLPos = lPos - lPrevPos;
        double dRPos = rPos - rPrevPos;

        double lSpeed = Math.abs(dLPos/dTime);
        double rSpeed = Math.abs(dRPos/dTime);

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

        if(gamepad1.a)
        {
            Utilities.resetEncoderValues(bot.leftMotor, bot.rightMotor);
        }

        telemetry.addData("L: ", lPos);
        telemetry.addData("R: ", rPos);
        telemetry.addData("L Speed: ", lSpeed);
        telemetry.addData("R Speed: ", rSpeed);

        telemetry.update();

        prevTime = currTime;
        lPrevPos = lPos;
        rPrevPos = rPos;
    }
}
