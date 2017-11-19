package org.firstinspires.ftc.team4348.autonomous.snippet;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.vuforia.VuMarkTarget;
import com.vuforia.VuMarkTargetResult;
import com.vuforia.VuMarkTemplate;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.team4348.autonomous.CustomAutonomous;
import org.firstinspires.ftc.team4348.constants.Direction;
import org.firstinspires.ftc.team4348.controllers.PIDController;
import org.firstinspires.ftc.team4348.robots.WorkingBot;

import static org.firstinspires.ftc.team4348.utils.Utilities.delay;

/**
 * Created by RoboKnights on 11/17/2017.
 */

@Autonomous(name="Red,Snippet: Box",group="Red,Snippet")
public class BoxAutoRed extends CustomAutonomous
{
    WorkingBot bot = new WorkingBot();
    final double AUTO_SPEED = 0.35;

    @Override
    public void runOpMode() throws InterruptedException
    {
        bot.init(hardwareMap);
        bot.jewelServo.setPosition(bot.JEWEL_INIT_POS);
        setPidController(new PIDController(bot.imu, bot.PIDC.p, bot.PIDC.i, bot.PIDC.d));

        waitForStart();

        //Assume:
        //(1) bot is facing the cipher box and near it to the right
        PIDStraightThread sThread = new PIDStraightThread(0);
        sThread.thread.start();

        bot.middleMotor.setPower(Direction.LEFT.v * AUTO_SPEED);

        RelicRecoveryVuMark vumark = RelicRecoveryVuMark.LEFT; //assume for testing purposes

        //get the target of how many columns to scan
        int target;
        if(vumark.equals(RelicRecoveryVuMark.LEFT)) {
            target = 4;
        }
        else if(vumark.equals(RelicRecoveryVuMark.CENTER)) {
            target = 3;
        }
        else {
            target = 2;
        }

        //find correct column
        int count = 0;
        boolean wasColor = false;
        while(count < target)
        {
            NormalizedRGBA RGBA = bot.cSensor2.getNormalizedColors();
            if(wasColor) {
                wasColor = false;
                count++;
            }
            else if(RGBA.red >= 0.5) {
                wasColor = true;
                do {
                    RGBA = bot.cSensor2.getNormalizedColors();
                }while(RGBA.red >= 0.5);
            }
        }
        bot.middleMotor.setPower(0);

        sThread.setRunning(false);
        sThread.thread.join();

        //drive forward
        straight(AUTO_SPEED, new TimeChecker(100));

        delay(50);
        //place the block
        runIntake(-1, 1000);

        delay(50);
        //back up
        straight(-AUTO_SPEED, new TimeChecker(100));
    }

    private void runIntake(double power, long time)
    {
        bot.intakeMotor1.setPower(power);
        bot.intakeMotor2.setPower(power);
        delay(time);
        bot.intakeMotor1.setPower(0);
        bot.intakeMotor2.setPower(0);
    }
}
