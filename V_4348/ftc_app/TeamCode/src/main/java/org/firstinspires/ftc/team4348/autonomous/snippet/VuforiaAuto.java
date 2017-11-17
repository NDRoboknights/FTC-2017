package org.firstinspires.ftc.team4348.autonomous.snippet;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.team4348.autonomous.CustomAutonomous;
import org.firstinspires.ftc.team4348.controllers.Vuforia;
import org.firstinspires.ftc.team4348.robots.WorkingBot;

/**
 * Created by RoboKnights on 11/17/2017.
 */

//The same for both red and blue per the field layout
@Autonomous(name="Snippet: Vuforia",group="Snippet")
public class VuforiaAuto extends CustomAutonomous
{
    WorkingBot bot = new WorkingBot();
    Vuforia vuforia;
    final double AUTO_SPEED = 0.5;

    @Override
    public void runOpMode() throws InterruptedException
    {
        bot.init(hardwareMap);
        bot.jewelServo.setPosition(bot.JEWEL_INIT_POS);

        vuforia = new Vuforia(hardwareMap);
        VuforiaTrackables relicTrackables = this.vuforia.localizer.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        waitForStart();

        relicTrackables.activate();

        //Assume:
        //(1) bot is facing away from relic recovery on red, towards for blue...
        //(2) phone is mounted to the right of the robot

        setPower(AUTO_SPEED, AUTO_SPEED); //drive forward until VuMark is seen

        RelicRecoveryVuMark mark;
        do {
            mark = RelicRecoveryVuMark.from(relicTemplate);
        }while(mark == RelicRecoveryVuMark.UNKNOWN);

        setPower(0, 0);
    }
}
