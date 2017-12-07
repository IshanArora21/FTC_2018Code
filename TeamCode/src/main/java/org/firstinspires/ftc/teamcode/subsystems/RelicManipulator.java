package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * @version 1.0
 * @author (put your names here)
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
        SYSTEM_OFF
    };

    public RelicManipulator(HardwareMap hwMap) {
        possState = systemStates.SYSTEM_OFF;
    }

    public void update() {
        switch (possState) {
            case SYSTEM_OFF:
                break;
            default:
                System.out.println("Yo, you messed up, the system switch for Relic Manipulator went to default");
        }
    }

    public void setStop() {
        possState = systemStates.SYSTEM_OFF;
    }
}
