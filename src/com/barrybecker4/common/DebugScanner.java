package com.barrybecker4.common;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * @author Barry Becker
 */
public class DebugScanner {

    // The coordinates of the last scanned robot
    private int scannedX = 0;
    private int scannedY = 0;


    public void calcLastScanned(AdvancedRobot robot, ScannedRobotEvent e) {

        // Calculate the angle to the scanned robot
        double angle = Math.toRadians((robot.getHeading() + e.getBearing()) % 360);
        // Calculate the coordinates of the robot
        scannedX = (int)(robot.getX() + Math.sin(angle) * e.getDistance());
        scannedY = (int)(robot.getY() + Math.cos(angle) * e.getDistance());
    }

    public void paint(AdvancedRobot robot, Graphics2D g) {
        // Set the paint color to a red half transparent color
        g.setColor(new Color(255, 255, 0, 64));

        // Draw a line from our robot to the scanned robot
        g.drawLine(scannedX, scannedY, (int)robot.getX(), (int)robot.getY());

        // Draw a filled square on top of the scanned robot that covers it
        g.fillRect(scannedX - 20, scannedY - 20, 40, 40);
    }
}
