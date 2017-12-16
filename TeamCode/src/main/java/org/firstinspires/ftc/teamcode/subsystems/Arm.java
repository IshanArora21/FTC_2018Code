package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * @version 1.0
 * @author bhargavilanka
 * Arm movement
 */
public class Arm {

     //singleton
    private static Arm arm = null;

    public static Arm getNewArmInstance(HardwareMap hwMap) {
        if (arm == null) {
            arm = new Arm(hwMap);
        }
        return arm;
    }

    //possible states the system could be in
    private systemStates possState;
    private enum systemStates {
        SYSTEM_OFF, ARM_EXTENDED, ARM_RETRACTED, COLOR_SENSE
    };

    //color sensor
    private ColorSensor colorSensor;

    //servo motor
    private Servo armServo;

    public Arm(HardwareMap hwMap) {

        //instantiating variables
        colorSensor = hwMap.colorSensor.get("colorSensor"); //sees big bulbous balls
        armServo = hwMap.servo.get("armServo");

        //system off means arm is off
        possState = systemStates.SYSTEM_OFF;
    }

    /**
     * updates the system
     */
    public void update() {
        switch (possState) {
            case SYSTEM_OFF:
                break;
            case ARM_EXTENDED:
                armServo.setPosition(0);
                break;
            case ARM_RETRACTED:
                armServo.setPosition(0.5);
                break;
            default:
                System.out.println("Yo, you messed up, the system switch for Arm went to default");
        }
    }

    /**
     * Shuts down the entire system
     */
    public void setStop() {
        possState = systemStates.SYSTEM_OFF;
    }

     /**
     * Sets the arm angle to 90 degrees (downward)
     */
    public void setArmExtended() {
        possState = systemStates.ARM_EXTENDED;
    }

    /**
     * Pulls the arm back into the constraining box
     */
    public void setArmRetracted() {
        possState = systemStates.ARM_RETRACTED;
    }

    /**
     * Tests to see if the sensor sees red
     */
    public boolean seesRed() {
        return colorSensor.red() > 100;
    }

    /**
    * Tests to see if the sensor sees blue
    */
    public boolean seesBlue() {
        return colorSensor.blue() > 100;
    }
}
