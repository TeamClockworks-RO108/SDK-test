package org.firstinspires.ftc.teamcode.utility;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Movement {
    private DcMotor front_right;
    private DcMotor front_left;
    private DcMotor back_right;
    private DcMotor back_left;

    private final static double BACK_POWER = -0.2;

    public Movement(HardwareMap hardwaremap) {
        front_right = hardwaremap.get(DcMotor.class, "front_right");
        front_left = hardwaremap.get(DcMotor.class, "front_left");
        back_left = hardwaremap.get(DcMotor.class, "back_left");
        back_right = hardwaremap.get(DcMotor.class, "back_right");
        front_left.setDirection(DcMotorSimple.Direction.REVERSE);
        back_left.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void control_movement(Gamepad gamepad1){
        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;

        double frontLeftPower = (y + x + rx);
        double backLeftPower = (y - x + rx) ;
        double frontRightPower = (y - x - rx);
        double backRightPower = (y + x - rx) ;

        front_left.setPower(frontLeftPower);
        front_right.setPower(frontRightPower);
        back_left.setPower(backLeftPower);
        back_right.setPower(backRightPower);

    }


    public void goBack(){
        front_left.setPower(BACK_POWER);
        front_right.setPower(BACK_POWER);
        back_left.setPower(BACK_POWER);
        back_right.setPower(BACK_POWER);
    }

    public void stopAll(){
        front_left.setPower(0);
        front_right.setPower(0);
        back_left.setPower(0);
        back_right.setPower(0);
    }

}
