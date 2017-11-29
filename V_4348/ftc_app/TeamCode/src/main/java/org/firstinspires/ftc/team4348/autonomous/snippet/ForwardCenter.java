package org.firstinspires.ftc.team4348.autonomous.snippet;

import org.firstinspires.ftc.team4348.controllers.PID.CycleChecker;
import org.firstinspires.ftc.team4348.controllers.PID.PIDController;
import org.firstinspires.ftc.team4348.controllers.PID.PIDFunctions;
import org.firstinspires.ftc.team4348.robots.WorkingBot;
import org.firstinspires.ftc.team4348.utils.TimeChecker;

import static org.firstinspires.ftc.team4348.utils.Utilities.delay;


public class ForwardCenter
{
    public void run(WorkingBot bot)
    {
        //drive forward
        bot.pidFunctions.straight(bot.AUTO_DRIVE_SPEED, new TimeChecker(500));

        delay(200);

        //center on zero
        bot.pidFunctions.goToAngle(bot.startingAngle, new CycleChecker(bot.pidFunctions, PIDController.D_EXTRACYCLES));
    }
}
