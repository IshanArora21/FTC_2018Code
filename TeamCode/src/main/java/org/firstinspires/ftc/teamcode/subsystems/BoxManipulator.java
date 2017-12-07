package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * @version 1.0
 * @author  (put your names here)
 * Intake and elevator combination
 */
public class BoxManipulator {

    //singleton
    private static BoxManipulator boxManipulator = null;
    public static BoxManipulator getNewBoxManipulatorInstance(HardwareMap hwMap) {
        if(boxManipulator == null) {
            boxManipulator = new BoxManipulator(hwMap);
        }
        return boxManipulator;
    }


    //possible states the system could be in
    private systemStates possState;
    private enum systemStates {
        SYSTEM_OFF
    };

    public BoxManipulator(HardwareMap hwMap) {

        //system off means both the intake and elevator are off
        possState = systemStates.SYSTEM_OFF;
    }

    /**
     * updates the system
     */
    public void update() {
        switch (possState) {
            case SYSTEM_OFF:
                break;
            default:
                System.out.println("Yo, the Box manipulator dude went to a bad enum");
        }
    }

    /**
     * Shuts down the entire system
     */
    public void setStop() {
        possState = systemStates.SYSTEM_OFF;
        this.update();
    }

}
