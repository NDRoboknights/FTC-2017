package org.firstinspires.ftc.team4348.autonomous.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team4348.autonomous.CustomAutonomous;
import org.firstinspires.ftc.team4348.controllers.PID.CycleChecker;
import org.firstinspires.ftc.team4348.utils.Direction;
import org.firstinspires.ftc.team4348.controllers.PID.PIDController;
import org.firstinspires.ftc.team4348.controllers.PID.PIDFunctions;
import org.firstinspires.ftc.team4348.robots.IMUBot;
import org.firstinspires.ftc.team4348.utils.TimeChecker;

import static org.firstinspires.ftc.team4348.utils.Utilities.delay;

@Autonomous(name="Test: PIDTest",group="Test")
public class PIDTest extends CustomAutonomous
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

        CycleChecker cChecker = new CycleChecker(pidFunctions, 100);

        //DO STUFF
        pidFunctions.turn(Direction.LEFT, 90, cChecker);
        delay(250);
        pidFunctions.turn(Direction.RIGHT, 90, cChecker);
        delay(250);
        pidFunctions.straight(bot.MAX_SPEED, new TimeChecker(1000));
    }
}
