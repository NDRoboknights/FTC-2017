package org.firstinspires.ftc.team4348.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team4348.robots.ClawBot;

@TeleOp(name="Test: ClawTest", group = "Test")
public class ClawTest extends CustomTeleOp
{
    private ClawBot bot = new ClawBot();

    private DcMotor armMotor;
    private Servo handClaw;

    private double armPos = .5;

    @Override
    public void init()
    {
        bot.init(hardwareMap);

        handClaw = bot.clawServo;
        armMotor = bot.armMotor;
    }

    @Override
    public void loop()
    {
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
            armMotor.setPower(.25);
        } else if(gamepad1.y)
        {
            armMotor.setPower(-.25);
        } else {
            armMotor.setPower(0);
        }

        handClaw.setPosition(armPos);

        telemetry.addData("arm pos: ", armPos);
        telemetry.update();
    }
}
