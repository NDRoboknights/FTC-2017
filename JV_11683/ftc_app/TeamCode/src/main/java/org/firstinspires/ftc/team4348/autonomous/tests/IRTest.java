package org.firstinspires.ftc.team4348.autonomous.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team4348.autonomous.CustomAutonomous;
import org.firstinspires.ftc.team4348.robots.IRBot;

/**
 * Created by evynm on 10/9/2017.
 */

@Autonomous(name="Test: IRTest",group="Autonomous")
public class IRTest extends CustomAutonomous
{
    IRBot bot = new IRBot();

    @Override
    public void runOpMode() throws InterruptedException
    {
        setBot(bot);
        bot.init(hardwareMap);

        waitForStart();

        while(opModeIsActive())
        {
            telemetry.addData("IRSensor: ", bot.irSensor.getStrength());
            telemetry.update();
        }
    }
}
