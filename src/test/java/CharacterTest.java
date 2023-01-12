import org.example.util.Combat;
import org.example.domain.MeleeFighter;
import org.example.domain.PlayableCharacter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {

    @Test
    public void newCreatedCharacterWithInitPoints_valid() {
        PlayableCharacter character = new MeleeFighter();

        assertEquals(1000, character.getHealth());
        assertEquals(1, character.getLevel());
        assertFalse(character.isDestroyed());
    }

    @Test
    public void heal_playerHealAnotherPlayerEqualMaxHealth_AnotherPlayerHealthIncreased() {
        PlayableCharacter leftCharacter = new MeleeFighter();

        leftCharacter.setHealth(500);

        Combat.heal(leftCharacter, leftCharacter, 500);

        assertEquals(1000, leftCharacter.getHealth());
    }

    @Test
    public void heal_playerHealAnotherPlayerLessThanMaxHealth_AnotherPlayerHealthIncreased() {
        PlayableCharacter leftCharacter = new MeleeFighter();

        leftCharacter.setHealth(500);

        Combat.heal(leftCharacter, leftCharacter, 400);

        assertEquals(900, leftCharacter.getHealth());
    }

    @Test
    public void heal_playerHealAnotherPlayerMoreThanMaxHealth_AnotherPlayerHealthIncreased() {
        PlayableCharacter leftCharacter = new MeleeFighter();

        leftCharacter.setHealth(500);

        Combat.heal(leftCharacter, leftCharacter, 5000);

        assertEquals(1000, leftCharacter.getHealth());
    }

    @Test
    public void heal_playerCanOnlyHealHimself_valid() {
        PlayableCharacter leftCharacter = new MeleeFighter();
        PlayableCharacter rightCharacter = new MeleeFighter();

        rightCharacter.setHealth(500);

        Combat.heal(leftCharacter, rightCharacter, 200);

        assertEquals(500, rightCharacter.getHealth());
    }
}
