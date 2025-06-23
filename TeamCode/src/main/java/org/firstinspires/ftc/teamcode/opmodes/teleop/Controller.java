package org.firstinspires.ftc.teamcode.opmodes.teleop;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.utility.Gripper;
import org.firstinspires.ftc.teamcode.utility.Movement;
import org.firstinspires.ftc.teamcode.utility.Retract;


@SuppressWarnings("unused")
@TeleOp(name = "KEBAB")
public class Controller extends OpMode {
    public final Gamepad prevGamepad1 = new Gamepad();
    private final Gamepad prevGamepad2 = new Gamepad();

    private Movement movement;
    private Gripper gripper;

    @Override
    public void init() {
        movement = new Movement(hardwareMap);
        gripper  = new Gripper(hardwareMap);
    }

    // Initialize components after start to prevent movement in init
    @Override
    public void start() {

    }


    @Override
    public void loop() {

        movement.control_movement(gamepad1);

        if(getGamepad1().cross && !prevGamepad1.cross){
            gripper.move_servo();
        }
        if(getGamepad1().square  && !prevGamepad1.square){
            Log.d("state", "init state machine");
            Retract.startMechanism();
        }

        prevGamepad1.copy(getGamepad1());

        Retract.setupStates(gripper, movement);

    }

    protected Gamepad getGamepad1() {
        return gamepad1;
    }
    protected Gamepad getGamepad2() {
        return gamepad2;
    }
    protected Gamepad getConfigGamepad() {
        return new Gamepad();
    }

}