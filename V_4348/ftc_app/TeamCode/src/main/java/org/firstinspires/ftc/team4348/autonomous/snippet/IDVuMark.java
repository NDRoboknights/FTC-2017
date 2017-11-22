package org.firstinspires.ftc.team4348.autonomous.snippet;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.team4348.controllers.PID.PIDController;
import org.firstinspires.ftc.team4348.controllers.Vuforia;
import org.firstinspires.ftc.team4348.robots.WorkingBot;
import org.firstinspires.ftc.team4348.controllers.PID.PIDFunctions;
import org.firstinspires.ftc.team4348.utils.StatusChecker;

/**
 * Created by RoboKnights on 11/17/2017.
 */

//The same for both red and blue per the field layout
public class IDVuMark
{
    static Vuforia vuforia;
    static int cycles;
    private static VuforiaTrackable relicTemplate;

    public static void initialize(WorkingBot bot)
    {
        vuforia = new Vuforia(bot.hardwareMap);
        PIDFunctions pidFunc = new PIDFunctions(bot, new PIDController(bot.imu, bot.PIDC));

        //track the RelicVuMarks
        VuforiaTrackables relicTrackables = vuforia.localizer.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        relicTrackables.activate();

        //Assume:
        //(1) bot is facing away from relic recovery on blue, towards for red...
        //(2) phone is mounted to the left of the robot
    }

    public static RelicRecoveryVuMark getVuMark()
    {
        //get the VuMark
        return RelicRecoveryVuMark.from(relicTemplate);
    }
}