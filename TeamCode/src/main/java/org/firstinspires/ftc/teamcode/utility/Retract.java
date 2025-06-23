package org.firstinspires.ftc.teamcode.utility;

import android.util.Log;

import com.qualcomm.robotcore.util.ElapsedTime;

public class Retract {

    enum State{
        IDLE,
        GRAB,
        MOVING_BACK,
        STOP,
    }

    static State currentState = State.IDLE;
    static ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);


    public static void setupStates(Gripper gripper, Movement movement){
        if(currentState == State.GRAB){
            gripper.move_servo();
            currentState = State.MOVING_BACK;
        }
        if(currentState == State.MOVING_BACK){
            movement.goBack();
            Log.d("state", "Going back..");
        }
        if(currentState == State.MOVING_BACK && timer.time() >= 1000){
            currentState = State.STOP;
        }
        if(currentState == State.STOP){
            currentState = State.IDLE;
            Log.d("state", "Stopped moving");
        }
    }

    public static void startMechanism(){
        Log.d("state", "State 0");
        currentState = State.GRAB;
        timer.reset();
    }
}
