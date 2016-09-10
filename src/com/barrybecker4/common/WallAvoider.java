package com.barrybecker4.common;

import robocode.AdvancedRobot;

/**
 * @author Barry Becker
 */
public class WallAvoider {

    private static final double AVOIDANCE_TURN = 90;

    private AdvancedRobot robot;
    private double topWall;
    private double rightWall;


    public WallAvoider(AdvancedRobot robot) {
        this.robot = robot;
        topWall = robot.getBattleFieldHeight();
        rightWall = robot.getBattleFieldWidth();
    }


    public void avoidWalls() {
        double x = robot.getX();
        double y = robot.getY();
        double heading = robot.getHeading();

        if (x < rightWall/6) {        // close to left wall
            if (heading > 270) {
                robot.turnRight(AVOIDANCE_TURN);
            }
            else if (heading > 180) {
                robot.turnLeft(AVOIDANCE_TURN);
            }
        }

        else if (x > rightWall * 5.0/6.0) {  // close to right wall
            if (heading < 90) {
                robot.turnLeft(AVOIDANCE_TURN);
            }
            else if (heading < 180) {
                robot.turnRight(AVOIDANCE_TURN);
            }
        }

        else if (y < topWall / 6.0) {  // close to bottom wall
            if (heading < 180 && heading > 90) {
                robot.turnLeft(AVOIDANCE_TURN);
            }
            else if (heading > 180 && heading < 270) {
                robot.turnRight(AVOIDANCE_TURN);
            }
        }

        else if (y > 5.0 * topWall /6.0) {  // close to top wall
            if (heading < 90) {
                robot.turnRight(AVOIDANCE_TURN);
            }
            else if (heading > 270) {
                robot.turnLeft(AVOIDANCE_TURN);
            }
        }
    }
}
