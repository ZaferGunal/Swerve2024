// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// TEST 
import frc.robot.commands.Test.Hold;
import frc.robot.commands.Test.Capture;



import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

import frc.robot.commands.auto.LeftThreePiece;
import frc.robot.commands.auto.LeftTwoPiece;
import frc.robot.commands.auto.ShootAndWait;
import frc.robot.commands.auto.ThreePiece;
import frc.robot.commands.AbsoluteDriveAdv;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.subsystems.SwerveSubsystem;


import javax.tools.StandardJavaFileManager.PathFactory;
import edu.wpi.first.networktables.NetworkTable;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.path.PathPlannerPath;
import edu.wpi.first.math.geometry.Translation2d;


import frc.robot.subsystems.IntakeSystem;
import frc.robot.subsystems.ShooterSystem;
//SequentialCommands
import frc.robot.commands.SequentialCommands.ReadyCapture;
import frc.robot.commands.SequentialCommands.ReadyCarry2Feed;
import frc.robot.commands.SequentialCommands.ReadyShootSpkr;


public class RobotContainer {
  SwerveSubsystem drivebase = new SwerveSubsystem();
  IntakeSystem intakeSystem = new IntakeSystem();
  ShooterSystem shooterSystem = new ShooterSystem();
  

  final Joystick driverXbox = new Joystick(0);

  final Joystick secondXbox = new Joystick(4);

JoystickButton n = new JoystickButton(driverXbox, 1);


  public RobotContainer() {


    configureBindings();

    Command driveFieldOrientedAnglularVelocity =new AbsoluteDriveAdv(
      drivebase,
        () -> driverXbox.getRawAxis(1) * (new JoystickButton(driverXbox, 6).getAsBoolean() ? -0.5 : -1),
        () -> driverXbox.getRawAxis(0) * (new JoystickButton(driverXbox, 6).getAsBoolean() ? -0.5 : -1),
        () -> driverXbox.getRawAxis(4) * (new JoystickButton(driverXbox, 6).getAsBoolean() ? -0.5 : -1),
        () -> new POVButton(secondXbox,0).getAsBoolean(),
        () -> new POVButton(secondXbox,90).getAsBoolean(),
        () -> new POVButton(secondXbox,180).getAsBoolean(),
        () -> new POVButton(secondXbox,270).getAsBoolean()
        );
    drivebase.setDefaultCommand(driveFieldOrientedAnglularVelocity);
  }

  private void configureBindings() {
  //  new JoystickButton(driverXbox,1).whileTrue(new ReadyCapture(intakeSystem,shooterSystem).andThen(new ReadyCarry2Feed(intakeSystem)));
     new JoystickButton(driverXbox,1).whileTrue(new ReadyCapture(intakeSystem, shooterSystem));    
     new JoystickButton(driverXbox,2).whileTrue(new ReadyCarry2Feed(intakeSystem));            
     new JoystickButton(driverXbox,6).whileTrue(new ReadyShootSpkr(intakeSystem, shooterSystem));
     
     

    
  }

  public Command getAutonomousCommand(String choosenAuton) {
    switch (choosenAuton) {
      case "mid-3":
        PathPlannerPath midpath1 = PathPlannerPath.fromPathFile("3obj-2");
        PathPlannerPath midpath2 = PathPlannerPath.fromPathFile("3obj-3");
        PathPlannerPath midpath3 = PathPlannerPath.fromPathFile("3obj-4");
        PathPlannerPath midpath4 = PathPlannerPath.fromPathFile("3obj-5");
        PathPlannerPath midpath5 = PathPlannerPath.fromPathFile("3obj-6");
        drivebase.resetOdometry(midpath1.getPreviewStartingHolonomicPose());
     //   return new ThreePiece(rollerSystem, shooterSystem, drivebase, intakeSystem, midpath1, midpath2, midpath3,
           // midpath4, midpath5);
      case "left-2":
        PathPlannerPath leftpath1 = PathPlannerPath.fromPathFile("left2obj-1");
        PathPlannerPath leftpath2 = PathPlannerPath.fromPathFile("left2obj-2");
        PathPlannerPath leftpath3 = PathPlannerPath.fromPathFile("left2obj-3");
        drivebase.resetOdometry(leftpath1.getPreviewStartingHolonomicPose());
       // return new LeftTwoPiece(rollerSystem, shooterSystem, drivebase, intakeSystem, leftpath1, leftpath2, leftpath3);
      case "shoot-wait-left":
        drivebase.resetOdometry(PathPlannerPath.fromPathFile("left2obj-1").getPreviewStartingHolonomicPose());
      //  return new ShootAndWait(shooterSystem);
      case "shoot-wait-right":
        drivebase.resetOdometry(PathPlannerPath.fromPathFile("rightpath").getPreviewStartingHolonomicPose());
      //  return new ShootAndWait(shooterSystem);
      case "shoot-wait-mid":
        drivebase.resetOdometry(PathPlannerPath.fromPathFile("3obj-2").getPreviewStartingHolonomicPose());
       // return new ShootAndWait(shooterSystem);
      case "left3-longway":
      PathPlannerPath left3_1 = PathPlannerPath.fromPathFile("left3-longway-1");
        PathPlannerPath left3_2 = PathPlannerPath.fromPathFile("left3-longway-2");
        PathPlannerPath left3_3 = PathPlannerPath.fromPathFile("left3-longway-3");
        PathPlannerPath left3_4 = PathPlannerPath.fromPathFile("left3-longway-4");
        PathPlannerPath left3_5 = PathPlannerPath.fromPathFile("left3-longway-5");
        drivebase.resetOdometry(left3_1.getPreviewStartingHolonomicPose());
        //return new LeftThreePiece(rollerSystem, shooterSystem, drivebase, intakeSystem,left3_1,left3_2,left3_3,left3_4,left3_5);
    }
    
    return null;

  }
}
