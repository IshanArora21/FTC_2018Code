package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * @version 1.0
 * @author Harsh_Padhye
 * Slide and claw
 */
public class RelicManipulator {

    //singleton
    private static RelicManipulator relicManipulator = null;
    public static RelicManipulator getNewRelicManipulatorInstance(HardwareMap hwMap) {
        if(relicManipulator == null) {
            relicManipulator = new RelicManipulator(hwMap);
        }
        return relicManipulator;
    }

    //servo and motor variables
    private DcMotor slideMotor;
    private Servo fingerServo;
    private Servo wristServo;

    //possible states the slide/claw could be in
    private systemStates possState;
    private enum systemStates {
        SYSTEM_OFF, EXTEND, RETRACT, GRAB, RELEASE
    };

    /**
     * constructor
     * @param hwMap : map of ports for motors, servos, etc
     */
    public RelicManipulator(HardwareMap hwMap) {
        //variables
        slideMotor = hwMap.get(DcMotor.class, "slide");
        fingerServo = hwMap.get(Servo.class, "finger");
        wristServo = hwMap.get(Servo.class, "wrist");

        //enum state
        possState = systemStates.SYSTEM_OFF;
    }

    /**
     * adjusts motors and servos to updated positions
     * @param possState : current state of the mechanism
     */
    public void update() {
        switch (possState) {
            case SYSTEM_OFF:
                slideMotor.setPower(0);
                break;
            case EXTEND:
                //full power forward to extend slide
                slideMotor.setPower(1.0);
                break;
            case RETRACT:
                //reverse power to retract slide
                slideMotor.setPower(-1.0);
                break;
            case GRAB:
                //close fingers, raise claw
                fingerServo.setPosition(1.0);
                wristServo.setPosition(0);
                break;
            case RELEASE:
                //drop claw, open fingers
                wristServo.setPosition(1.0);
                fingerServo.setPosition(0);
                break;
            default:
                System.out.println("Yo, you messed up, the system switch for Relic Manipulator went to default");
        }
    }

    /**
     * shuts down the system
     */
    public void setStop() {
        possState = systemStates.SYSTEM_OFF;
    }

    /**
     * runs the arm until fully extended
     * motor runs forward
     */
    public void setSlideExtend() {
        possState = systemStates.EXTEND;
    }

    /**
     * pulls the arm back until fully retracted
     * motor runs in reverse
     */
    public void setSlideRetract() {
        possState = systemStates.RETRACT;
    }

    /**
     * grabs and raises the relic
     */
    public void setClawGrab() {
        possState = systemStates.GRAB;
    }

    /**
     * lowers and releases relic
     */
    public void setClawRelease() {
        possState = systemStates.RELEASE;
    }



}