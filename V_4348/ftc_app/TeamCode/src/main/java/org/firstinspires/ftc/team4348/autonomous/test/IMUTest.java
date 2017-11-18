package org.firstinspires.ftc.team4348.autonomous.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team4348.autonomous.CustomAutonomous;
import org.firstinspires.ftc.team4348.controllers.PIDController;
import org.firstinspires.ftc.team4348.robots.IMUBot;

/**
 * Created by evynm on 10/4/2017.
 */

@Autonomous(name="Test: IMUTest",group="Test")
public class IMUTest extends CustomAutonomous
{
    IMUBot bot = new IMUBot();

    @Override
    public void runOpMode() throws InterruptedException
    {
        setBot(bot);
        bot.init(hardwareMap);

        waitForStart();

        //DO STUFF
        while(opModeIsActive())
        {
            telemetry.addData("IMU Angle:", bot.imu.getValue());
            telemetry.update();
        }
    }
}
