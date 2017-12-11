package org.firstinspires.ftc.teamcode.OpModes;

import org.firstinspires.ftc.teamcode.R;
import android.media.MediaPlayer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.BoxManipulator;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.RelicManipulator;

import java.sql.Time;

/**
 * Created by Connor_Hofenbitzer on 12/6/17.
 */

@Autonomous(name="Nemesis Prime Example Auto", group="Ares Auto")
public class ExampleAuto extends LinearOpMode {

    private DriveTrain driveTrain;
    private BoxManipulator boxManipulator;



    @Override
    public void runOpMode() throws InterruptedException {
        MediaPlayer mediaPlayer = MediaPlayer.create(hardwareMap.appContext, R.raw.banana);

        mediaPlayer.start();
        //init all systems
        driveTrain = DriveTrain.getNewDriveInstance(hardwareMap);
        boxManipulator = BoxManipulator.getNewBoxManipulatorInstance(hardwareMap);

        //set system stops
        driveTrain.setStop();
        boxManipulator.setStop();

        driveTrain.updateDrive(1 , 0 ,0);
        driveTrain.update();
        sleep(1000);
        driveTrain.setStop();
    }

}
