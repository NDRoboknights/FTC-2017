package org.firstinspires.ftc.team4348.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.team4348.robots.SlideBot;
import org.firstinspires.ftc.team4348.teleop.CustomTeleOp;

/**
 * Created by RoboKnight on 9/19/2017.
 */
@TeleOp(name="TeleOp: ClawTest", group = "TeleOp")
public class ServoTest extends CustomTeleOp
{
    SlideBot bot = new SlideBot();

    DcMotor lMotor;
    DcMotor rMotor;
    DcMotor mMotor;
    DcMotor armMotor;

    Double armPos = .5;
    double handPos = .5;

    Servo handClaw;

    @Override
    public void init()
    {
        bot.init(hardwareMap);
        lMotor = bot.leftMotor;
        rMotor = bot.rightMotor;
        mMotor = bot.middleMotor;

        handClaw = hardwareMap.servo.get("handServo");
        armMotor = hardwareMap.dcMotor.get("armMotor");
    }

    @Override
    public void loop()
    {
        double leftPower = -gamepad1.left_stick_y;
        double rightPower = -gamepad1.right_stick_y;

        if ((Math.abs(leftPower) > JOYSTICK_THRESHOLD)) {
            lMotor.setPower(scaleInput(leftPower));
        } else {
            lMotor.setPower(0);
        }
        if ((Math.abs(rightPower) > JOYSTICK_THRESHOLD)) {
            rMotor.setPower(scaleInput(rightPower));
        } else {
            rMotor.setPower(0);
        }
        if (gamepad1.right_bumper)
        {
            mMotor.setPower(1);
        } else if(gamepad1.left_bumper)
        {
            mMotor.setPower(-1);
        } else {
            mMotor.setPower(0);
        }

        if(gamepad1.a)
        {
            if(armPos == 0)
            {

            } else {
                armPos -= .01;
            }
        }
        if(gamepad1.b)
        {
            if(armPos == 1) {
            } else {
                armPos += .01;
            }
        }

        if(gamepad1.x)
        {
            armMotor.setPower(.25);
        } else if(gamepad1.y)
        {
            armMotor.setPower(-.25);
        } else {
            armMotor.setPower(0);
        }

        handClaw.setPosition(armPos);


        if(gamepad1.x)
        {
            lMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            lMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        telemetry.addData("Left Drive Pos: ", lMotor.getCurrentPosition());
        telemetry.addData("Right Drive Pos: ", rMotor.getCurrentPosition());

        telemetry.addData("Left Drive Pow: ", scaleInput(leftPower));
        telemetry.addData("Right Drive Pow: ", scaleInput(rightPower));
        telemetry.update();
    }
}
