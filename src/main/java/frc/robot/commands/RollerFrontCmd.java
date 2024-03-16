package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSystem;
import frc.robot.subsystems.RollerSubsystem;
public class RollerFrontCmd  extends Command{

RollerSubsystem intakeSystem;
 int voltage ;
    public RollerFrontCmd(RollerSubsystem intakeSystem_, int voltage_){
        intakeSystem = intakeSystem_;
      voltage = voltage_;
    }

    @Override
    public void initialize() {}
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        intakeSystem.setRollerSpeed(voltage);
    
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      intakeSystem.stopRoller();
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
}
    
}