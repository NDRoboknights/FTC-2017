package org.firstinspires.ftc.team4348.autonomous.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team4348.autonomous.CustomAutonomous;
import org.firstinspires.ftc.team4348.controllers.PIDController;
import org.firstinspires.ftc.team4348.robots.IMUBot;

/**
 * Created by Evyn on 10/3/2017.
 */

@Autonomous(name="Test: PIDStraightTest",group="Test")
public class PIDStraightTest extends CustomAutonomous
{
    IMUBot bot = new IMUBot();
    PIDController pidController;

    @Override
    public void runOpMode() throws InterruptedException
    {
        setBot(bot);
        bot.init(hardwareMap);
        pidController = new PIDController(bot.imu, bot.pidc.p, bot.pidc.i, bot.pidc.d);

        waitForStart();

        //DO STUFF
        straight(bot.MAX_SPEED, new TimeChecker(3000));
    }
}
