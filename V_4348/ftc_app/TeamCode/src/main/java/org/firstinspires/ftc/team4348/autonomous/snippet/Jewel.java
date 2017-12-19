package org.firstinspires.ftc.team4348.autonomous.snippet;

import org.firstinspires.ftc.team4348.autonomous.CustomAutonomous;
import org.firstinspires.ftc.team4348.robots.WorkingBot;
import org.firstinspires.ftc.team4348.utils.Direction;
import org.firstinspires.ftc.team4348.utils.TimeChecker;

import static org.firstinspires.ftc.team4348.utils.Utilities.delay;


public class Jewel
{
    public static Direction blueRun(WorkingBot bot, long timeout)
    {
        //drop jewel servo arm
        bot.jewelServo.setPosition(0.5);

        //read color sensor and choose direction
        Direction dir = null;

        TimeChecker tChecker = new TimeChecker(timeout);
        while(dir == null && tChecker.checkStatus())
        {
            //RGBA.red returns [0,255]
            //if above threshold, choose that direction
            if (bot.cSensor1.blue() > CustomAutonomous.COLOR_THRESHOLD) {
                dir = Direction.FORWARD;
            }
            //not an else statement to make sure we have a reading
            else if (bot.cSensor1.red() > CustomAutonomous.COLOR_THRESHOLD) {
                dir = Direction.BACKWARD;
            }
        }

        if(dir != null) {
            bot.setDrivePowerT(dir.v * bot.AUTO_DRIVE_SPEED, dir.v * bot.AUTO_DRIVE_SPEED, 200);
            delay(50);
        }

        //retract arm
        bot.jewelServo.setPosition(bot.JEWEL_INIT_POS);
        return dir;
    }

    public static Direction redRun(WorkingBot bot, long timeout)
    {
        //drop jewel servo arm
        bot.jewelServo.setPosition(0.5);

        //read color sensor and choose direction
        Direction dir = null;

        TimeChecker tChecker = new TimeChecker(timeout);
        while(dir == null && tChecker.checkStatus())
        {
            //RGBA.red returns [0,255]
            //if above threshold, choose that direction
            if (bot.cSensor1.red() > CustomAutonomous.COLOR_THRESHOLD) {
                dir = Direction.FORWARD;
            }
            //not an else statement to make sure we have a reading
            else if (bot.cSensor1.blue() > CustomAutonomous.COLOR_THRESHOLD) {
                dir = Direction.BACKWARD;
            }
        }

        if(dir != null) {
            bot.setDrivePowerT(dir.v * bot.AUTO_DRIVE_SPEED, dir.v * bot.AUTO_DRIVE_SPEED, 200);
            delay(50);
        }

        //retract arm
        bot.jewelServo.setPosition(bot.JEWEL_INIT_POS);
        return dir;
    }
}
