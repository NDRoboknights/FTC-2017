package org.firstinspires.ftc.team4348.controllers.PID;


public abstract class PIDInput
{
    public abstract double getValue();
    public abstract double normalizeValue(double value);
    public abstract double normalizeError(double error);
}
