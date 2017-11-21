package org.firstinspires.ftc.team4348.controllers.PID;

/**
 * Created by RoboKnights on 11/20/2017.
 */

public abstract class PIDInput
{
    public abstract double getValue();
    public abstract double normalizeValue(double value);
    public abstract double normalizeError(double error);
}
