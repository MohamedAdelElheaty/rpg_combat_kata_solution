import org.example.domain.MeleeFighter;
import org.example.domain.NonPlayableCharacter;
import org.example.domain.PlayableCharacter;
import org.example.domain.Prop;
import org.example.util.Combat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PropTest {

    @Test
    public void damage_damageProp_reduceHealth() {
        PlayableCharacter damageDealer = new MeleeFighter();
        NonPlayableCharacter tree = new Prop(2000, false);

        Combat.damage(damageDealer, tree, 100);

        assertEquals(1900, tree.getHealth());
    }

}
