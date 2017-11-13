package org.firstinspires.ftc.team4348.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.team4348.constants.HardwareName;
import org.firstinspires.ftc.team4348.teleop.CustomTeleOp;

/**
 * Created by evynm on 10/5/2017.
 */

@TeleOp(name="Test: TestTeleOp", group="Test")
public class TestTeleOp extends CustomTeleOp
{
    DcMotor intake1;
    DcMotor intake2;
    DcMotor up1;
    DcMotor up2;

    double intakePower = 0.7;
    double upPower = 0.7;

    @Override
    public void init()
    {
        intake1 = hardwareMap.dcMotor.get(HardwareName.INTAKE_MOTOR_ONE.name);
        intake2 = hardwareMap.dcMotor.get(HardwareName.INTAKE_MOTOR_TWO.name);
        up1 = hardwareMap.dcMotor.get(HardwareName.UP_MOTOR_ONE.name);
        up2 = hardwareMap.dcMotor.get(HardwareName.UP_MOTOR_TWO.name);

        intake2.setDirection(DcMotorSimple.Direction.REVERSE);
        up1.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop()
    {
        boolean a = gamepad1.a;
        boolean b = gamepad1.b;

        boolean dPadUp = gamepad1.dpad_up;
        boolean dPadDown = gamepad1.dpad_down;

        if(a) {
            intake1.setPower(intakePower);
            intake2.setPower(intakePower);
        }
        else {
            intake1.setPower(0);
            intake2.setPower(0);
        }

        if(b) {
            up1.setPower(upPower);
            up2.setPower(upPower);
        }
        else {
            up1.setPower(0);
            up2.setPower(0);
        }

        if(dPadUp) {
            upPower += 0.05;
        }

        if(dPadDown) {
            upPower -= 0.05;
        }
    }
}
