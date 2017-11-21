package org.firstinspires.ftc.team4348.partstest;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team4348.robots.ServoBot;
import org.firstinspires.ftc.team4348.teleop.CustomTeleOp;

/**
 * Created by RoboKnights on 11/20/2017.
 */

@TeleOp(name="ServoTest: Jewel",group="ServoTest")
public class ServoTest extends CustomTeleOp
{
    ServoBot bot = new ServoBot();
    double pos = bot.JEWEL_INIT_POS;

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
                pos += 0.01;
            }
            else {
                pos -= 0.01;
            }
        }

        bot.jewelServo.setPosition(pos);
        telemetry.addData("Position: ", bot.jewelServo.getPosition());
        telemetry.update();
    }
}
