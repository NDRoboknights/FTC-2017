package org.firstinspires.ftc.team4348.controllers;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

/**
 * Allows for easy objectification of Vuforia engine.
 */
public class Vuforia
{
    /**
     * {@link #localizer} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    public VuforiaLocalizer localizer;

    public Vuforia(HardwareMap hMap)
    {
        //Display camera view on RC
//        int cameraMonitorViewId = hMap.appContext.getResources()
//                .getIdentifier("cameraMonitorViewId", "id", hMap.appContext.getPackageName());
//        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        //Do not display camera view on RC phone
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = "AZaVXY3/////AAAAGV8AvdXe40mSkhlx5ieOk+5fLIkMWaowsR8BStQ2Ez6Kb7" +
                "LgrxlKRjyHvW5h/Gctp9g0i9XxN6mE4sE8TxG9BXheD7LEPxVro1mrafX8rMtbnggt4g3eqA/TVyXCNFeln70z" +
                "+8XsNaSl8K1bLpf7zvhGtJaP5wnMeoXRnWQgM4FNMtpnu88dJ5oYYz7oHzKiHwS/LPtKqETPyNbJMUuBfEVwR+W" +
                "CnBJV1f9iwy5xXZ1KIRh7zY34yhsycbDYodagPRCcUFQc+reDbN+qYXEhlagQRD3bY+NA8Bs607UebkWL4Rc8idt" +
                "TELK3J6ND41g5j62qhLFBr6DaLmdtaobkdaRCgMZpe6rW3OsqpCTELDLZ";

        //Use the back camera
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        localizer = ClassFactory.createVuforiaLocalizer(parameters);
    }
}
