package org.firstinspires.ftc.team4348.partstest;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team4348.robots.IntakeBot;
import org.firstinspires.ftc.team4348.teleop.CustomTeleOp;

@TeleOp(name="Test: Intake")
public class IntakeTest extends CustomTeleOp
{
    IntakeBot bot = new IntakeBot();

    @Override
    public void init() {
        bot.init(hardwareMap);
    }

    @Override
    public void loop() {
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
    }
}
