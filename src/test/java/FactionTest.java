import org.example.util.Combat;
import org.example.domain.Faction;
import org.example.domain.MeleeFighter;
import org.example.domain.PlayableCharacter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactionTest {


    @Test
    public void damage_damageWithinSameFiction_noDamageOccur() {
        PlayableCharacter damageDealer = new MeleeFighter();
        PlayableCharacter damageReceiver = new MeleeFighter();

        Faction fireFaction = new Faction();

        fireFaction.join(damageDealer);
        fireFaction.join(damageReceiver);

        Combat.damage(damageDealer, damageReceiver, 100);

        assertEquals(1000, damageReceiver.getHealth());
    }

    @Test
    public void damage_damageWithinNotSameFiction_DamageOccur() {
        PlayableCharacter damageDealer = new MeleeFighter();
        PlayableCharacter damageReceiver = new MeleeFighter();

        Faction fireFaction = new Faction();
        Faction waterFaction = new Faction();

        fireFaction.join(damageDealer);
        waterFaction.join(damageReceiver);

        Combat.damage(damageDealer, damageReceiver, 100);

        assertEquals(900, damageReceiver.getHealth());
    }

    @Test
    public void damage_damageWithinSameFictionWithMultipleFaction_DamageOccur() {
        PlayableCharacter damageDealer = new MeleeFighter();
        PlayableCharacter damageReceiver = new MeleeFighter();

        Faction fireFaction = new Faction();
        Faction airFaction = new Faction();
        Faction waterFaction = new Faction();

        fireFaction.join(damageDealer);
        airFaction.join(damageDealer);

        waterFaction.join(damageReceiver);

        Combat.damage(damageDealer, damageReceiver, 100);

        assertEquals(900, damageReceiver.getHealth());
    }

    @Test
    public void damage_damageWithinSameFictionWithMultipleFactionWithCommonFaction_noDamageOccur() {
        PlayableCharacter damageDealer = new MeleeFighter();
        PlayableCharacter damageReceiver = new MeleeFighter();

        Faction fireFaction = new Faction();
        Faction airFaction = new Faction();
        Faction waterFaction = new Faction();

        fireFaction.join(damageDealer);
        airFaction.join(damageDealer);

        waterFaction.join(damageReceiver);
        airFaction.join(damageReceiver);

        Combat.damage(damageDealer, damageReceiver, 100);

        assertEquals(1000, damageReceiver.getHealth());
    }

    @Test
    public void damage_damageWithinNotSameFictionByLeave_DamageOccur() {
        PlayableCharacter damageDealer = new MeleeFighter();
        PlayableCharacter damageReceiver = new MeleeFighter();

        Faction fireFaction = new Faction();

        fireFaction.join(damageDealer);
        fireFaction.join(damageReceiver);

        fireFaction.leave(damageDealer);

        Combat.damage(damageDealer, damageReceiver, 100);

        assertEquals(900, damageReceiver.getHealth());
    }


    @Test
    public void heal_healWithinSameFiction_healOccur() {
        PlayableCharacter healer = new MeleeFighter();
        PlayableCharacter healReceiver = new MeleeFighter();

        healReceiver.setHealth(500);

        Faction fireFaction = new Faction();

        fireFaction.join(healer);
        fireFaction.join(healReceiver);

        Combat.heal(healer, healReceiver, 100);

        assertEquals(600, healReceiver.getHealth());
    }

    @Test
    public void heal_healNoSameFiction_healOccur() {
        PlayableCharacter healer = new MeleeFighter();
        PlayableCharacter healReceiver = new MeleeFighter();

        healReceiver.setHealth(500);

        Faction fireFaction = new Faction();

        fireFaction.join(healer);

        Combat.heal(healer, healReceiver, 100);

        assertEquals(500, healReceiver.getHealth());
    }

}
