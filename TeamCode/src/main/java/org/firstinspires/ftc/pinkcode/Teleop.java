package org.firstinspires.ftc.pinkcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Teleop extends OpMode {

    DcMotor frontLeft, backLeft, frontRight, backRight;

    @Override
    public void init() {
        frontLeft = hardwareMap.dcMotor.get("frontL");
        backLeft = hardwareMap.dcMotor.get("backL");
        frontRight = hardwareMap.dcMotor.get("frontR");
        backRight = hardwareMap.dcMotor.get("backR");
    }

    @Override
    public void loop() {
       frontLeft.setPower(gamepad1.right_stick_y*-1);
       backLeft.setPower(gamepad1.right_stick_y*-1);
       frontRight.setPower(gamepad1.right_stick_y);
       backRight.setPower(gamepad1.right_stick_y);
    }
}
