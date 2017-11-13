package org.firstinspires.ftc.team4348.autonomous.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.internal.system.SystemProperties;
import org.firstinspires.ftc.team4348.autonomous.CustomAutonomous;
import org.firstinspires.ftc.team4348.constants.Direction;
import org.firstinspires.ftc.team4348.controllers.PIDController;
import org.firstinspires.ftc.team4348.robots.IMUBot;

/**
 * Created by Evyn on 10/3/2017.
 */

@Autonomous(name="Test: IMUStraightTest",group="Test")
public class IMUStraightTest extends CustomAutonomous
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
        straight(bot.MAX_SPEED, 3000);
    }

    public void straight(double power, long time)
    {
        pidController.setTarget(pidController.getValue());
        long endTime = time + System.currentTimeMillis();
        pidController.start();
        while(System.currentTimeMillis() < endTime)
        {
            //find direction
            double raw = pidController.getError();

            double left = 1;
            double right = 1;

            if(raw > 0) {
                right = -1;
            }
            else if(raw < 0) {
                left = -1;
            }

            //set power
            double lPower = power + left * Math.abs(pidController.getOutput());
            double rPower = power + right * Math.abs(pidController.getOutput());

            lPower = scalePower(lPower);
            rPower = scalePower(rPower);

            setPower(lPower, rPower);
        }
        setPower(0,0);
        pidController.stop();
    }
}
