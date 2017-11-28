package org.firstinspires.ftc.team4348.robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeBot extends Bot
{
    public DcMotor intakeMotor1;
    public DcMotor intakeMotor2;

    @Override
    public void init(HardwareMap hMap)
    {
        this.hardwareMap = hMap;

        //other motors
        intakeMotor1 = hMap.dcMotor.get(HardwareName.INTAKE_MOTOR_ONE.name);
        intakeMotor2 = hMap.dcMotor.get(HardwareName.INTAKE_MOTOR_TWO.name);
        intakeMotor2.setDirection(DcMotor.Direction.REVERSE);

        otherMotors.add(intakeMotor1);
        otherMotors.add(intakeMotor2);
    }

    public void runIntakeMotors(double power)
    {
        intakeMotor1.setPower(power);
        intakeMotor2.setPower(power);
    }
}
