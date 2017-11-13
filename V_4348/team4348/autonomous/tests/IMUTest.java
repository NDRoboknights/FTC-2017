package org.firstinspires.ftc.team4348.autonomous.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team4348.autonomous.CustomAutonomous;
import org.firstinspires.ftc.team4348.constants.Direction;
import org.firstinspires.ftc.team4348.controllers.PIDController;
import org.firstinspires.ftc.team4348.robots.IMUBot;

/**
 * Created by evynm on 10/4/2017.
 */

@Autonomous(name="Test: IMUTest",group="Autonomous")
public class IMUTest extends CustomAutonomous
{
    IMUBot bot = new IMUBot();
    PIDController pidController;

    @Override
    public void runOpMode() throws InterruptedException
    {
        setBot(bot);
        bot.init(hardwareMap);
        pidController = new PIDController(bot.imu, 0.001, 0, 0);

        waitForStart();

        //DO STUFF
        while(opModeIsActive())
        {
            telemetry.addData("IMU Angle:", bot.imu.getValue());
            telemetry.update();
        }
    }
}
