package org.firstinspires.ftc.team4348.autonomous.snippet;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.team4348.autonomous.CustomAutonomous;
import org.firstinspires.ftc.team4348.controllers.PID.PIDFunctions;
import org.firstinspires.ftc.team4348.robots.WorkingBot;
import org.firstinspires.ftc.team4348.utils.Direction;
import org.firstinspires.ftc.team4348.utils.TimeChecker;

import static org.firstinspires.ftc.team4348.utils.Utilities.delay;


public class CryptoBox
{
    public static void blueRun(WorkingBot bot, RelicRecoveryVuMark vuMark) throws InterruptedException {
        //For blue, we go from left to right

        //find target count
        int target;
        if(vuMark.equals(RelicRecoveryVuMark.RIGHT)) {
            target = 3;
        }
        else if(vuMark.equals(RelicRecoveryVuMark.CENTER)) {
            target = 2;
        }
        else {
            target = 1;
        }

        PIDFunctions.PIDStraightThread sThread = new PIDFunctions.PIDStraightThread(bot.pidFunctions, 0);
        sThread.thread.start();

        bot.middleMotor.setPower(Direction.RIGHT.v * bot.AUTO_MEDIUM_SPEED);
        int count = 0;

        TimeChecker tChecker = new TimeChecker(2500);
        boolean prevColor = false;
        while(count < target && tChecker.checkStatus())
        {
            if(!prevColor && bot.cSensor1.blue() > CustomAutonomous.COLOR_THRESHOLD) {
                count++;
                prevColor = true;
            }
            else if(prevColor && bot.cSensor1.blue() < CustomAutonomous.COLOR_THRESHOLD) {
                prevColor = false;
            }
        }
        bot.middleMotor.setPower(0);
        sThread.setRunning(false);
        sThread.thread.join();

        bot.runIntakeMotors(-1);
        delay(1500);
        bot.runIntakeMotors(0);
    }

    public static void redRun(WorkingBot bot, RelicRecoveryVuMark vuMark) throws InterruptedException {
        //For red, we go from right to left

        //find target count
        int target;
        if(vuMark.equals(RelicRecoveryVuMark.LEFT)) {
            target = 4;
        }
        else if(vuMark.equals(RelicRecoveryVuMark.CENTER)) {
            target = 3;
        }
        else {
            target = 2;
        }

        PIDFunctions.PIDStraightThread sThread = new PIDFunctions.PIDStraightThread(bot.pidFunctions, 0);
        sThread.thread.start();

        bot.middleMotor.setPower(Direction.LEFT.v * bot.AUTO_MEDIUM_SPEED);
        int count = 0;

        TimeChecker tChecker = new TimeChecker(2500);
        boolean prevColor = false;
        while(count < target && tChecker.checkStatus())
        {
            if(!prevColor && bot.cSensor1.blue() > CustomAutonomous.COLOR_THRESHOLD) {
                count++;
                prevColor = true;
            }
            else if(prevColor && bot.cSensor1.blue() < CustomAutonomous.COLOR_THRESHOLD) {
                prevColor = false;
            }
        }
        bot.middleMotor.setPower(0);
        sThread.setRunning(false);
        sThread.thread.join();

        bot.runIntakeMotors(-1);
        delay(1500);
        bot.runIntakeMotors(0);
    }
}
