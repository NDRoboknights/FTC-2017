package org.firstinspires.ftc.team4348.partstest;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team4348.robots.ServoBot;
import org.firstinspires.ftc.team4348.teleop.CustomTeleOp;


@TeleOp(name="ServoTest: Jewel",group="ServoTest")
public class ServoTest extends CustomTeleOp
{
    ServoBot bot = new ServoBot();
    double pos = bot.JEWEL_INIT_POS;
    double oldPos = bot.JEWEL_INIT_POS;
    double dValue = 0.01;

    @Override
    public void init()
    {
        bot.init(hardwareMap);
    }

    @Override
    public void loop()
    {
        if(Math.abs(gamepad1.right_stick_y) > JOYSTICK_THRESHOLD )
        {
            if(-gamepad1.right_stick_y > 0) {
                pos += dValue;
            }
            else {
                pos -= dValue;
            }
        }

        if(gamepad1.a) {
            bot.jewelServo.setPosition(bot.JEWEL_INIT_POS);
            pos = bot.JEWEL_INIT_POS;
            oldPos = pos;
        }

        if(gamepad1.b) {
            bot.jewelServo.setPosition(oldPos);
        }

        bot.jewelServo.setPosition(pos);
        telemetry.addData("Position: ", bot.jewelServo.getPosition());
        telemetry.update();
    }
}
