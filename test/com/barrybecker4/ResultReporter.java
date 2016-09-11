package com.barrybecker4;

import robocode.BattleResults;

/**
 * @author Barry Becker
 */
public class ResultReporter {

    public ResultReporter() {

    }

    public void showReport(BattleResults[] battleResults) {
         for (BattleResults results : battleResults) {
              System.out.println(results.getTeamLeaderName() + "\nfirsts=" + results.getFirsts()
                      + "\tscore:" + results.getScore() + "\tbulletDamage=" + results.getBulletDamage()
                      + "\tramDamage=" + results.getRamDamage() + "\tramBonus=" + results.getRamDamageBonus() + "\trank="+ results.getRank());
         }
    }
}
