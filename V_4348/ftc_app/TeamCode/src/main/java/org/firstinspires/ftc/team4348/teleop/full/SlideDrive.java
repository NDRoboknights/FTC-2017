package org.firstinspires.ftc.team4348.teleop.full;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team4348.robots.SlideBot;
import org.firstinspires.ftc.team4348.teleop.CustomTeleOp;

/**
 * Created by RoboKnight on 9/19/2017.
 */
@TeleOp(name="Test: SlideDrive", group = "Test")
public class SlideDrive extends CustomTeleOp
{
    SlideBot bot = new SlideBot();

    DcMotor lMotor;
    DcMotor rMotor;
    DcMotor mMotor;

    @Override
    public void init()
    {
        bot.init(hardwareMap);
        lMotor = bot.leftMotor;
        rMotor = bot.rightMotor;
        mMotor = bot.middleMotor;
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
