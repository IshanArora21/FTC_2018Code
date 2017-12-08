package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * @version 1.0
 * @author  chinmaysavanur, bhargavilanka
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

    //outputs
    private double[][] motorOutputs;

    //intake motors
    private DcMotor leftIntake;
    private DcMotor rightIntake;

    //belt motors
    private DcMotor topBelt;
    private DcMotor bottomBelt;

    //possible states the system could be in
    private systemStates possState;
    private enum systemStates {
        SYSTEM_OFF , INTAKE_ONLY , BELT_ONLY , INTAKE_AND_BELT
    };

    private double intakeSpeed; //speed of intake
    private double beltSpeed; //speed of belt
    private boolean reverseIntake; //if the request to reverse intake is given
    private boolean turnIntakeOff; //if the request to turn off intake is given
    private boolean turnBeltOff; //if the request to turn off belt is given

    public BoxManipulator(HardwareMap hwMap) {

        //system off means both the intake and elevator are off
        possState = systemStates.SYSTEM_OFF;

        //drive motor outputs
        motorOutputs = new double[2][2];

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
        switch (possState) {
            case SYSTEM_OFF:
                break;
            case INTAKE_ONLY:
                while(!turnIntakeOff) {
                    intakeIn(1);
                    if(reverseIntake) intakeOut(1);
                }
                if(turnIntakeOff) intakeOff();
                break;
            case BELT_ONLY:
                while(!turnBeltOff) {
                    beltUp(1);
                }
                if(turnBeltOff) beltOff();
                break;
            case INTAKE_AND_BELT:
                while(!turnIntakeOff) {
                    intakeIn(1);
                    if(reverseIntake) intakeOut(1);
                }
                if(turnIntakeOff) intakeOff();
                while(!turnBeltOff) {
                    beltUp(1);
                }
                if(turnBeltOff) beltOff();
                break;
            default:
                System.out.println("Yo, the Box manipulator dude went to a bad enum");
        }
    }

    //sets the power for the intake motors
    leftIntake.setPower(motorOutputs[0][0]);
    rightIntake.setPower(motorOutputs[0][1];

    //sets the power for the belt motors
    topBelt.setPower(motorOutputs[1][0]);
    bottomBelt.setPower(motorOutputs[1][1]);

    /**
     * Shuts down the entire system
     */
    public void setStop() {
        possState = systemStates.SYSTEM_OFF;
        this.update();
    }

    private void intakeIn(double speedInit) { //run the intake at an intital speed
        intakeSpeed = speedInit;
    }

    private void intakeOut(double speedExpel) { //run the intake backwards
        intakeSpeed = speedExpel;
    }

    private void intakeOff() { //stop running the intake
        intakeSpeed = 0;
    }

    private void beltUp(double speedInit) { //run the belt at an initial speed
        beltSpeed = speedInit;
    }

    private void beltOff() { //stop running the belt
        beltSpeed = 0;
    }
}
