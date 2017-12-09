package org.firstinspires.ftc.teamcode.OpModes;

import android.media.MediaPlayer;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.subsystems.BoxManipulator;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.RelicManipulator;

@TeleOp(name="Nemesis Prime 2018 Teleop", group="Ares Teleop")
public class Teleop extends OpMode {

    private boolean started;
    private MediaPlayer mediaPlayer;
    private DriveTrain driveTrain;
    private BoxManipulator boxManipulator;
    private RelicManipulator relicManipulator;

    @Override
    public void init() {
        started = false;
        mediaPlayer = MediaPlayer.create(hardwareMap.appContext, R.raw.banana);

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
        driveTrain.updateDrive(-gamepad1.left_stick_x/2 , gamepad1.left_stick_y/2 , gamepad1.right_stick_x/2);
        driveTrain.update();


    }
}
