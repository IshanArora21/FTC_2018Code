package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * @version 1.1
 * @author  chinmaysavanur
 * Intake and elevator combination
 */
public class BoxManipulator {

    //singleton
    private static BoxManipulator boxManipulator = null;

    public static BoxManipulator getNewBoxManipulatorInstance(HardwareMap hwMap) {
        if (boxManipulator == null) {
            boxManipulator = new BoxManipulator(hwMap);
        }
        return boxManipulator;
    }

    //intake motors
    private DcMotor leftIntake;
    private DcMotor rightIntake;

    //belt motors
    private DcMotor topBelt;
    private DcMotor bottomBelt;

    public double intakeSpeed;
    public double beltSpeed;

    public BoxManipulator(HardwareMap hwMap) {

        //intake motors
        leftIntake = hwMap.get(DcMotor.class, "left_Intake");
        rightIntake = hwMap.get(DcMotor.class, "right_Intake");

        //belt motors
        topBelt = hwMap.get(DcMotor.class, "top_Belt");
        bottomBelt = hwMap.get(DcMotor.class, "bottom_Belt");
    }

    /**
     * updates the system
     */
    public void update() {

        //sets the power for the front motors
        leftIntake.setPower(intakeSpeed);
        rightIntake.setPower(intakeSpeed);

        //sets the power for the back motors
        topBelt.setPower(beltSpeed);
        bottomBelt.setPower(beltSpeed);
    }

    /**
     * Shuts down the entire system
     */
    public void setStop() {
        setIntakeSpeed(0);
        setBeltSpeed(0);
        update();
    }

    /**
     * Changes intake speed to input from game-pad
     */
    public void setIntakeSpeed(double spd) {
        intakeSpeed = spd;
        update();
    }

    /**
     * Changes belt speed to input from game-pad
     */
    public void setBeltSpeed(double spd) {
        beltSpeed = spd;
        update();
    }
}
