package org.firstinspires.ftc.team4348.autonomous.full;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team4348.autonomous.CustomAutonomous;
import org.firstinspires.ftc.team4348.autonomous.snippet.Jewel;
import org.firstinspires.ftc.team4348.robots.WorkingBot;

@Autonomous(name="JewelRed")
public class JewelRed extends CustomAutonomous
{
    WorkingBot bot = new WorkingBot();

    @Override
    public void runOpMode() throws InterruptedException
    {
        bot.init(hardwareMap);
        waitForStart();

        Jewel.redRun(bot, 5000);
    }
}