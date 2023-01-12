package org.example.util;

import org.example.domain.Character;
import org.example.domain.PlayableCharacter;
import org.example.types.CharacterType;

public class Combat {
    public static void heal(PlayableCharacter healer, PlayableCharacter healReceiver, int healAmount) {

        if (!healer.isDestroyed() && !healReceiver.isDestroyed() && (healer == healReceiver || isAllies(healer, healReceiver))) {
            int newHealth = Math.min(healReceiver.getMAX_HEALTH(), healReceiver.getHealth() + healAmount);
            healReceiver.setHealth(newHealth);
        }
    }

    public static void damage(Character damageDealer, Character damageReceiver, int damageAmount) {


        if (isPlayableCharacter(damageDealer) && isPlayableCharacter(damageReceiver)) {

            if (isValidToDamage(damageDealer, damageReceiver, damageAmount)) {
                return;
            }

            damageAmount = calculateDamage(damageDealer, damageReceiver, damageAmount);

            if (damageAmount >= damageReceiver.getHealth()) {
                damageReceiver.destroy();
            } else {
                int remainingHealth = damageReceiver.getHealth() - damageAmount;
                damageReceiver.setHealth(remainingHealth);
            }

        } else if (isPlayableCharacter(damageDealer) && !isPlayableCharacter(damageReceiver)) {

            if (damageDealer.isDestroyed() || damageReceiver.isDestroyed() || damageAmount <= 0) {
                return;
            }

            if (damageAmount >= damageReceiver.getHealth()) {
                damageReceiver.destroy();
            } else {
                int remainingHealth = damageReceiver.getHealth() - damageAmount;
                damageReceiver.setHealth(remainingHealth);
            }
        }

    }

    private static boolean isValidToDamage(Character damageDealer, Character damageReceiver, int damageAmount) {
        PlayableCharacter damager = (PlayableCharacter) damageDealer;
        PlayableCharacter receiver = (PlayableCharacter) damageReceiver;
        return damageDealer.isDestroyed() || damageAmount <= 0 || damageDealer == damageReceiver || !damageWithinRange(damager, receiver) || isAllies(damager, receiver);
    }

    private static boolean isPlayableCharacter(Character character) {
        return character.getType().equals(CharacterType.PLAYABLE);
    }

    private static boolean isAllies(PlayableCharacter damageDealer, PlayableCharacter damageReceiver) {
        for (String factionId : damageDealer.getFactions().keySet()) {
            if (damageReceiver.getFactions().containsKey(factionId)) {
                return true;
            }
        }
        return false;
    }

    private static boolean damageWithinRange(PlayableCharacter damageDealer, PlayableCharacter damageReceiver) {
        return Math.abs(damageReceiver.getPosition() - damageDealer.getPosition()) <= damageDealer.getMaxRange();
    }

    public static int calculateDamage(Character player1, Character player2, int damagePoints) {
        PlayableCharacter damageDealer = (PlayableCharacter) player1;
        PlayableCharacter damageReceiver = (PlayableCharacter) player2;

        if (damageDealer.getLevel() - damageReceiver.getLevel() >= 5) {
            damagePoints *= 1.5;
        }

        if (damageReceiver.getLevel() - damageDealer.getLevel() >= 5) {
            damagePoints /= 2;
        }

        return damagePoints;
    }

}
