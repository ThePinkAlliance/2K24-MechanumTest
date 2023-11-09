package org.firstinspires.ftc.pinkcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Autonomous
public class Claw extends OpMode{
    DcMotor frontRight, frontLeft, backRight, backLeft;
    int distanceTraveled = 0;
    @Override
    public void init() {
        frontRight = hardwareMap.get(DcMotorEx.class, "frontR");
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontL");

        backRight = hardwareMap.get(DcMotorEx.class, "backR");
        backLeft = hardwareMap.get(DcMotorEx.class, "backL");
    }

    @Override
    public void loop() {
        distanceTraveled = distanceTraveled + 1;
        telemetry.addData("distance",distanceTraveled);
        telemetry.addData("frontRight",frontRight.getCurrentPosition());
        telemetry.addData("frontLeft",frontLeft.getCurrentPosition());
        telemetry.update();
        if (Math.abs(frontRight.getCurrentPosition())>500) {
            this.frontLeft.setPower(0);
            this.frontRight.setPower(0);
            this.backLeft.setPower(0);
            this.backRight.setPower(0);

        }
        else {
            this.frontLeft.setPower(1);
            this.frontRight.setPower(0);
            this.backLeft.setPower(0);
            this.backRight.setPower(0);
        }

    }
    public void end(){}
}
