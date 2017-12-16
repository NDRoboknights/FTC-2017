package org.firstinspires.ftc.team4348.autonomous.full;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team4348.autonomous.CustomAutonomous;
import org.firstinspires.ftc.team4348.robots.WorkingBot;

@Autonomous(name="Auto: DriveBackward")
public class DBackward extends CustomAutonomous
{
    WorkingBot bot = new WorkingBot();

    @Override
    public void runOpMode() throws InterruptedException
    {
        bot.init(hardwareMap);

        waitForStart();

        bot.setDrivePowerT(bot.AUTO_DRIVE_SPEED, bot.AUTO_DRIVE_SPEED, 1250);
    }
}