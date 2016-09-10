package com.barrybecker4;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

/**
 * derived from http://www.ibm.com/developerworks/library/j-dodge/
 */
public class DodgeBot extends AdvancedRobot  {

    private double previousEnergy = 100;
    private int movementDirection = 1;
    private int gunDirection = 1;

    public void run() {
        setTurnGunRight(99999);
    }

    public void onScannedRobot(ScannedRobotEvent e) {

        // Stay at right angles to the opponent
        setTurnRight(e.getBearing() + 90 - 30 * movementDirection);

        // If the bot has small energy drop,
        // assume it fired
        double changeInEnergy = previousEnergy-e.getEnergy();
        if (changeInEnergy>0 && changeInEnergy<=3) {
            // Dodge!
            movementDirection = -movementDirection;
            setAhead((e.getDistance()/4+25) * movementDirection);
        }
        // When a bot is spotted, sweep the gun and radar
        gunDirection = -gunDirection;
        setTurnGunRight(99999* gunDirection);

        // Fire directly at target
        fire(2) ;

        // Track the energy level
        previousEnergy = e.getEnergy();
    }
}
