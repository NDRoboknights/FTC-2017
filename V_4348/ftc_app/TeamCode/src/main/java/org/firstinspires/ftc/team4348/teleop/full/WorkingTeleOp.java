package org.firstinspires.ftc.team4348.teleop.full;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team4348.robots.WorkingBot;
import org.firstinspires.ftc.team4348.teleop.CustomTeleOp;
import org.firstinspires.ftc.team4348.utils.Direction;


@TeleOp(name="WorkingTele")
public class WorkingTeleOp extends CustomTeleOp
{
    WorkingBot bot = new WorkingBot();
    long prevTime = 0;
    int lPrevPos = 0;
    int rPrevPos = 0;
    double maxSpeed = 0.9;

    boolean butPress = false;

    @Override
    public void init()
    {
        bot.init(hardwareMap);
        bot.jewelServo.setPosition(bot.JEWEL_INIT_POS);
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

        double multiR = 1;
        double multiL = 1;

        if(lSpeed > rSpeed && rSpeed > JOYSTICK_THRESHOLD) {
            multiL = rSpeed/lSpeed;
        }
        if(rSpeed > lSpeed && lSpeed > JOYSTICK_THRESHOLD) {
            multiR = lSpeed/rSpeed;
        }

        //joysticks
        double lPower = -multiL * gamepad1.left_stick_y * maxSpeed;
        double rPower = -multiR * gamepad1.right_stick_y * maxSpeed;

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
            bot.runIntakeMotors(1);
        }
        else if(gamepad1.left_trigger >= 0.5) {
            bot.runIntakeMotors(-1);
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


        telemetry.addData("MultiL: ", multiL);
        telemetry.addData("MultiR: ", multiR);

        telemetry.update();

        prevTime = currTime;
        lPrevPos = lPos;
        rPrevPos = rPos;
    }
}