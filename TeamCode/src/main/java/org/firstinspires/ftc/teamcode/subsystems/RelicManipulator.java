package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * @version 1.0
 * @author harshpadhye, bhargavilanka
 * Scissor lift and claw combo
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

    //possible states the system could be in
    private systemStates possState;
    private enum systemStates {
        SYSTEM_OFF, EXTEND, RETRACT, GRAB, RELEASE
    };

    //variables
    private DcMotor slideMotor;
    private Servo clawFingers;
    private Servo clawWrist;

    public RelicManipulator(HardwareMap hwMap) {
        //instantiate variables
        //slideMotor = new DcMotor(DcMotor.class, "slideMotor");
        slideMotor = hwMap.get(DcMotor.class, "slide_Motor");
        //clawFingers = new Servo(Servo.class, "clawFingers");
        clawFingers = hwMap.get(Servo.class, "claw_Fingers");
        //clawWrist = new Servo(Servo.class, "clawWrist");
        clawWrist = hwMap.get(Servo.class, "claw_Wrist");
        possState = systemStates.SYSTEM_OFF;
    }

    /**
     * Runs appropriate motors based on
     * current systme state
     */
    public void update() {
        switch (possState) {
            case SYSTEM_OFF:
                break;
            case EXTEND:
                slideMotor.setPower(1);
                break;
            case RETRACT:
                slideMotor.setPower(-1);
                break;
            case GRAB:
                //close, then lift
                /*
                    SERVO POSITIONAL VALUES
                    SUBJECT TO CHANGE
                    AKA WE HAVE NO IDEA
                    UNTIL IT'S BUILT
                 */
                clawFingers.setPosition(1);
                clawWrist.setPosition(0);
                break;
            case RELEASE:
                //drop, then open
                clawWrist.setPosition(1);
                clawFingers.setPosition(0);
                break;
            default:
                System.out.println("Yo, you messed up, the system switch for Relic Manipulator went to default");
        }
    }

    /**
     * changes system states
     */

    public void setStop() {
        possState = systemStates.SYSTEM_OFF;
    }

    public void setExtended() {
        possState = systemStates.EXTEND;
    }

    public void setRetract() {
        possState = systemStates.RETRACT;
    }

    public void setGrab() {
        possState = systemStates.GRAB;
    }

    public void setRelease() {
        possState = systemStates.RELEASE;
    }

}
