package org.firstinspires.ftc.team4348.controllers.PID;

import org.firstinspires.ftc.team4348.utils.StatusChecker;

/**
 * Created by Evyn on 11/21/2017.
 */

public class CycleChecker extends StatusChecker
{
    int extraCycles;
    PIDFunctions func;

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
