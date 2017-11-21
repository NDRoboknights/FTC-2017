package org.firstinspires.ftc.team4348.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team4348.constants.Direction;
import org.firstinspires.ftc.team4348.controllers.PIDController;
import org.firstinspires.ftc.team4348.robots.Bot;
import org.firstinspires.ftc.team4348.utils.StatusChecker;

import static org.firstinspires.ftc.team4348.utils.Utilities.scalePower;

/**
 * Created by evynm on 10/3/2017.
 */

public abstract class CustomAutonomous extends LinearOpMode
{
    public static final int COLOR_THRESHOLD = 1;
}
