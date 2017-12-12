package org.firstinspires.ftc.team4348.controllers.PID;

import org.firstinspires.ftc.team4348.utils.StatusChecker;


public class CycleChecker extends StatusChecker
{
    int extraCycles;
    PIDFunctions func;

    /**
     * Used in PIDController to specify the amount of extra cycles within ACC_ERROR
     * to continue running.
     * @param func the PIDFunctions object to operate on
     * @param extraCycles how many extra cycles to continue
     */
    public CycleChecker(PIDFunctions func, int extraCycles)
    {
        this.func = func;
        this.extraCycles = extraCycles;
    }

    @Override
    public boolean checkStatus() {
        return func.pidController.cycles < extraCycles;
    }
}
