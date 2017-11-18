package org.firstinspires.ftc.team4348.autonomous.snippet;

import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.vuforia.VuMarkTarget;
import com.vuforia.VuMarkTargetResult;
import com.vuforia.VuMarkTemplate;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.team4348.autonomous.CustomAutonomous;
import org.firstinspires.ftc.team4348.constants.Direction;
import org.firstinspires.ftc.team4348.robots.WorkingBot;

/**
 * Created by RoboKnights on 11/17/2017.
 */

public class BoxAutoRed extends CustomAutonomous
{
    WorkingBot bot = new WorkingBot();
    final double AUTO_SPEED = 0.35;

    @Override
    public void runOpMode() throws InterruptedException
    {
        bot.init(hardwareMap);
        bot.jewelServo.setPosition(bot.JEWEL_INIT_POS);

        waitForStart();

        //Assume:
        //(1) bot is facing the cipher box and near it to the right
        bot.middleMotor.setPower(Direction.LEFT.v * AUTO_SPEED);

        RelicRecoveryVuMark vumark = RelicRecoveryVuMark.LEFT; //assume left for testing purposes

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

        delay(50);
        //place the block
        runIntake(-1, 1000);
    }

    private void driveMMotor(double power, long time)
    {
        bot.middleMotor.setPower(power);
        delay(time);
        bot.middleMotor.setPower(0);
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
