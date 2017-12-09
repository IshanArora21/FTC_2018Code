package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcontroller.external.samples.BasicOpMode_Iterative;
import org.firstinspires.ftc.teamcode.subsystems.BoxManipulator;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.RelicManipulator;

@TeleOp(name="Nemesis Prime 2018 Teleop", group="Ares Teleop")
public class Teleop extends OpMode
{
    private DriveTrain driveTrain;
    private BoxManipulator boxManipulator;
    private RelicManipulator relicManipulator;

    @Override
    public void init() {

        //init all systems
        driveTrain = DriveTrain.getNewDriveInstance(hardwareMap);
        boxManipulator = BoxManipulator.getNewBoxManipulatorInstance(hardwareMap);
        relicManipulator = RelicManipulator.getNewRelicManipulatorInstance(hardwareMap);

        //set system stops
        driveTrain.setStop();
        boxManipulator.setStop();
        relicManipulator.setStop();

    }

    @Override
    public void loop() {
        //updates the drive train
        driveTrain.updateDrive(gamepad1.left_stick_x , gamepad1.left_stick_y , gamepad1.right_stick_y);
        driveTrain.update();

        //updates the intake and belt speeds
        boxManipulator.setIntakeSpeed(((gamepad1.left_trigger > 0.25) != gamepad1.left_bumper)?(gamepad1.left_bumper?-1:1):0);
        boxManipulator.setBeltSpeed(((gamepad1.right_trigger > 0.25) != gamepad1.right_bumper)?(gamepad1.right_bumper?-1:1):0);
        boxManipulator.update();
    }
}
