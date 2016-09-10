/*******************************************************************************
 * Copyright (c) 2001-2013 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://robocode.sourceforge.net/license/epl-v10.html
 *******************************************************************************/
package com.barrybecker4;


import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;

import java.awt.Color;
/**
 * Best so far.
 * SpinBot - a sample robot by Mathew Nelson.
 * <p/>
 * Moves in a circle, firing hard when an enemy is detected.
 *
 * @author Mathew A. Nelson (original)
 * @author Flemming N. Larsen (contributor)
 */
public class SpinBot extends AdvancedRobot {

	public void run() {
		// Set colors
		setBodyColor(Color.orange);
		setGunColor(Color.blue);
		setRadarColor(Color.black);
		setScanColor(Color.orange);

		// Loop forever
		while (true) {
			setTurnRight(100);
			setMaxVelocity(20);
			setAhead(110);
            while(getDistanceRemaining() > 0)
                execute();
		}
	}

	/**
	 * onScannedRobot: Fire hard!
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		fire(determineFirepower(e.getDistance()));
	}

	/**
	 * If it's our fault, we'll stop turning and moving, so we need to turn again to keep spinning.
	 */
	public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() > -10 && e.getBearing() < 10) {
			fire(3);
		}
		if (e.isMyFault()) {
			turnRight(10);
		}
	}


    private double determineFirepower(double distance) {
        double fp =  Math.min(3.0, 600.0 / (distance + 80.0));
        //System.out.println("dist="+ distance + " firePower=" + fp);
        return fp;
    }
}
