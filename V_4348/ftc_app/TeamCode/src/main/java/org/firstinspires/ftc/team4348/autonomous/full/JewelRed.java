package org.firstinspires.ftc.team4348.autonomous.full;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team4348.autonomous.CustomAutonomous;
import org.firstinspires.ftc.team4348.autonomous.snippet.Jewel;
import org.firstinspires.ftc.team4348.robots.WorkingBot;

/**
 * Created by RoboKnights on 12/5/2017.
 */

@Autonomous(name="JewelRed")
public class JewelRed extends CustomAutonomous
{
    WorkingBot bot = new WorkingBot();
    @Override
    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);
        Jewel.redRun(bot, 5000);
    }
}
