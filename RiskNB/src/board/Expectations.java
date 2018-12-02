/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package board;

import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author carnival
 */
public class Expectations {

    public static double getProbability(int troops1, int troops2) {
        int samplingSize = 10000;
        int winner = 0;
        for (int i = 0; i < samplingSize; i++){
            winner += simulateAttack(troops1,troops2)[0];
        }
        return ((double)winner) / samplingSize;
    }
    
    public static double getMyTroops(int troops1, int troops2) {
        int samplingSize = 10000;
        int remainingTroops = 0;
        for (int i = 0; i < samplingSize; i++){
            remainingTroops += simulateAttack(troops1,troops2)[1];
        }
        return ((double)remainingTroops) / samplingSize;
    }
    
    private static Integer[] simulateAttack(int attackerTroops, int defenderTroops) {
        Integer[] attackLog = new Integer[3];

        int attackerDice, defenderDice;
        int[] losses;

        while (attackerTroops > 1 && defenderTroops > 0) {
            attackerDice = Math.min(attackerTroops - 1, 3); 
            defenderDice = Math.min(defenderTroops, 2);
            losses = throwDice(attackerDice, defenderDice);

            // decrement mT by losses[0]
            attackerTroops -= losses[0];
            // decrement t by losses[1]
            defenderTroops -= losses[1];
        }

        if (defenderTroops == 0) { // Attack successful
            attackLog[0] = 1;
        } else {
            attackLog[0] = 0;
        }

        attackLog[1] = attackerTroops;
        attackLog[2] = defenderTroops;

        return attackLog;
    }

    private static int[] throwDice(int dice1, int dice2) {
        // Simulates random dice throws during an attack	
        // Returns losses[] where:
        // 		losses[0] = Attacker's losses
        // 		losse[1] = Defender's losses

        Integer[] values1 = new Integer[dice1]; // 1 <= dice1 <= 3
        Integer[] values2 = new Integer[dice2]; // 1 <= dice2 <= 2

        int[] losses = new int[2];

        for (int i = 0; i < dice1; i++) {
            values1[i] = (int) (Math.random() * 6 + 1);
        }
        for (int i = 0; i < dice2; i++) {
            values2[i] = (int) (Math.random() * 6 + 1);
        }

        Arrays.sort(values1, Collections.reverseOrder());
        Arrays.sort(values2, Collections.reverseOrder());

        for (int i = 0; i < Math.min(dice1, dice2); i++) { // We know that for all dice1,dice2, dice2<=dice1            
            if (values2[i] >= values1[i]) {
                losses[0] += 1;
            } else {
                losses[1] += 1;
            }
        }
        return losses;
    }

    
}
