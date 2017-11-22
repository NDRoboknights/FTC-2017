package org.firstinspires.ftc.team4348.autonomous.snippet;

import org.firstinspires.ftc.team4348.controllers.PID.CycleChecker;
import org.firstinspires.ftc.team4348.controllers.PID.PIDController;
import org.firstinspires.ftc.team4348.controllers.PID.PIDFunctions;
import org.firstinspires.ftc.team4348.robots.WorkingBot;
import org.firstinspires.ftc.team4348.utils.TimeChecker;

import static org.firstinspires.ftc.team4348.utils.Utilities.delay;

/**
 * Created by Evyn on 11/21/2017.
 */

public class ForwardCenter
{
    public void run(WorkingBot bot)
    {
        PIDController pidController = new PIDController(bot.imu, bot.PIDC);
        PIDFunctions pidFunctions = new PIDFunctions(bot, pidController);

        //drive forward
        pidFunctions.straight(bot.AUTO_DRIVE_SPEED, new TimeChecker(500));

        delay(200);

        //center on zero
        pidFunctions.goToAngle(0, new CycleChecker(pidFunctions, pidController.D_EXTRACYCLES));
    }
}
