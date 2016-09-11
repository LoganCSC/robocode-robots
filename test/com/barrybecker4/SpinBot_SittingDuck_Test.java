package com.barrybecker4;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import robocode.BattleResults;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.testing.RobotTestBed;

/**
 *  See
 *  https://github.com/jaros/robocode/blob/master/plugins/testing/robocode.testing.api/src/main/java/robocode/control/testing/RobotTestBed.java
 */
@RunWith(JUnit4.class)
public class SpinBot_SittingDuck_Test extends RobotTestBed {

  private ResultReporter reporter = new ResultReporter();

  /**
   * The names of the robots that want battling is specified.
   * @return The names of the robots we want battling.
   */
  @Override
  public String getRobotNames() {
    return "sample.SittingDuck, com.barrybecker4.SpinBot*";
  }

  /**
   * Pick the amount of rounds that we want our robots to battle for.
   *
   * @return Amount of rounds we want to battle for.
   */
  @Override
  public int getNumRounds() {
    return 10;
  }

  /**
   * Tests to see if our robot won all rounds.
   * @param event Holds information about the battle has been completed.
   */
  @Override
  public void onBattleCompleted(BattleCompletedEvent event) {
    // Return the results in order of getRobotNames.
    BattleResults[] battleResults = event.getIndexedResults();
    reporter.showReport(battleResults);

    BattleResults results = battleResults[1];
    String robotName = results.getTeamLeaderName();
    assertEquals("Check that results[1] is SpinBot", "com.barrybecker4.SpinBot*", robotName);

    assertEquals("Check SpinBot winner", getNumRounds(), results.getFirsts());
  }
}