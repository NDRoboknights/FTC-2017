package org.firstinspires.ftc.team4348.autonomous.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.team4348.autonomous.CustomAutonomous;
import org.firstinspires.ftc.team4348.controllers.PID.PIDController;
import org.firstinspires.ftc.team4348.controllers.PID.PIDFunctions;
import org.firstinspires.ftc.team4348.robots.IMUBot;
import org.firstinspires.ftc.team4348.utils.TimeChecker;

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
        bot.init(hardwareMap);
        pidController = new PIDController(bot.imu, bot.pidc);
        PIDFunctions pidFunctions = new PIDFunctions(bot, pidController);

        waitForStart();

        //DO STUFF
        pidFunctions.straight(bot.MAX_SPEED, new TimeChecker(3000));
    }
}
