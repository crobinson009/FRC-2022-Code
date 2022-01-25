package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class PIDCommand extends CommandBase {
    private final DriveSubsystem m_subsystem;
    private double m_distance;

    public PIDCommand(DriveSubsystem subsytem, double distance){
        m_subsystem = subsytem;
        addRequirements(m_subsystem);
        m_distance = inchesToTicks(distance);
    }

    public double inchesToTicks(double input) {
        return ((2048*10.71*input)/(6*Math.PI));
    }

@Override
public void initialize() {
    m_subsystem.enablepid(m_distance);
}

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {
        m_subsystem.disablepid();
    }

    @Override
    public boolean isFinished() {
        System.out.println(m_subsystem.m_left1.getClosedLoopTarget()-m_subsystem.m_left1.getSelectedSensorPosition(0));
        return Math.abs(m_subsystem.m_left1.getClosedLoopTarget()-m_subsystem.m_left1.getSelectedSensorPosition(0)) < 100 && Math.abs(m_subsystem.m_left1.getSelectedSensorVelocity(0)) < 5;
        
    }

    
}