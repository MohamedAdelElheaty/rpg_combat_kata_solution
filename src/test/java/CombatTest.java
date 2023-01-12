import org.example.util.Combat;
import org.example.domain.MeleeFighter;
import org.example.domain.PlayableCharacter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CombatTest {
    @Test
    public void damage_playerDamageOpponentLessThanMaxHealth_opponentHealthReduced() {
        PlayableCharacter leftCharacter = new MeleeFighter();
        PlayableCharacter rightCharacter = new MeleeFighter();

        Combat.damage(leftCharacter, rightCharacter, 500);

        assertEquals(500, rightCharacter.getHealth());
    }

    @Test
    public void damage_playerDamageOpponentMoreThanMaxHealth_opponentHealthReduced() {
        PlayableCharacter leftCharacter = new MeleeFighter();
        PlayableCharacter rightCharacter = new MeleeFighter();

        Combat.damage(leftCharacter, rightCharacter, 5000);

        assertEquals(0, rightCharacter.getHealth());
        assertTrue(rightCharacter.isDestroyed());
    }

    @Test
    public void damage_playerDamageOpponentEqualMaxHealth_opponentHealthReduced() {
        PlayableCharacter leftCharacter = new MeleeFighter();
        PlayableCharacter rightCharacter = new MeleeFighter();

        Combat.damage(leftCharacter, rightCharacter, rightCharacter.getHealth());

        assertEquals(0, rightCharacter.getHealth());
        assertTrue(rightCharacter.isDestroyed());
    }

    @Test
    public void damage_playerDamageOpponentWithMultipleDamageLessThanMaxHealth_opponentHealthReduced() {
        PlayableCharacter leftCharacter = new MeleeFighter();
        PlayableCharacter rightCharacter = new MeleeFighter();

        Combat.damage(leftCharacter, rightCharacter, 100);
        Combat.damage(leftCharacter, rightCharacter, 200);

        Combat.damage(rightCharacter, leftCharacter, 200);
        Combat.damage(rightCharacter, leftCharacter, 200);


        assertEquals(700, rightCharacter.getHealth());
        assertFalse(rightCharacter.isDestroyed());

        assertEquals(600, leftCharacter.getHealth());
        assertFalse(leftCharacter.isDestroyed());
    }

    @Test
    public void damage_deadPlayerCannotDamage_opponentHealthIsSame() {
        PlayableCharacter leftCharacter = new MeleeFighter();
        PlayableCharacter rightCharacter = new MeleeFighter();

        leftCharacter.destroy();

        Combat.damage(leftCharacter, rightCharacter, 100);

        assertEquals(1000, rightCharacter.getHealth());
    }

    @Test
    public void damage_characterCannotDamageHimself_valid() {
        PlayableCharacter leftCharacter = new MeleeFighter();

        Combat.damage(leftCharacter, leftCharacter, 100);

        assertEquals(1000, leftCharacter.getHealth());
    }

    @Test
    public void damage_characterLevelIsMoreThanOpponentWithFiveLevel_valid() {
        PlayableCharacter leftCharacter = new MeleeFighter();
        PlayableCharacter rightCharacter = new MeleeFighter();

        leftCharacter.setLevel(6);

        Combat.damage(leftCharacter, rightCharacter, 100);

        assertEquals(850, rightCharacter.getHealth());
    }

    @Test
    public void damage_characterLevelIsLessThanOpponentWithFiveLevel_valid() {
        PlayableCharacter leftCharacter = new MeleeFighter();
        PlayableCharacter rightCharacter = new MeleeFighter();

        rightCharacter.setLevel(6);

        Combat.damage(leftCharacter, rightCharacter, 100);

        assertEquals(950, rightCharacter.getHealth());
    }

    @Test
    public void damage_meleeFighterDamageMeleeFighterWithinRange_valid() {
        PlayableCharacter damageDealer = new MeleeFighter();
        PlayableCharacter damageReceiver = new MeleeFighter();

        damageDealer.setPosition(10);
        damageReceiver.setPosition(11);

        Combat.damage(damageDealer, damageReceiver, 100);

        assertEquals(900, damageReceiver.getHealth());
    }

    @Test
    public void damage_meleeFighterDamageMeleeFighterOutSideRange_valid() {
        PlayableCharacter damageDealer = new MeleeFighter();
        PlayableCharacter damageReceiver = new MeleeFighter();

        damageDealer.setPosition(10);
        damageReceiver.setPosition(damageDealer.getPosition() + damageDealer.getMaxRange() + 1);

        Combat.damage(damageDealer, damageReceiver, 100);

        assertEquals(1000, damageReceiver.getHealth());
    }

    @Test
    public void damage_meleeFighterDamageMeleeFighterOutSideRangeInOtherDirection_valid() {
        PlayableCharacter damageDealer = new MeleeFighter();
        PlayableCharacter damageReceiver = new MeleeFighter();

        damageDealer.setPosition(10);
        damageReceiver.setPosition(damageDealer.getPosition() - damageDealer.getMaxRange() - 1);

        Combat.damage(damageDealer, damageReceiver, 100);

        assertEquals(1000, damageReceiver.getHealth());
    }
}
