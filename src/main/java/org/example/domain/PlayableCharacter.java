package org.example.domain;

import lombok.Data;
import org.example.types.CharacterType;

import java.util.HashMap;
import java.util.Map;

@Data
public abstract class PlayableCharacter extends Character {
    private final int MAX_HEALTH = 1000;
    protected int position;
    private int level;
    private Map<String, Faction> factions;

    public PlayableCharacter() {
        setHealth(MAX_HEALTH);
        level = 1;
        factions = new HashMap<>();

    }

    @Override
    public CharacterType getType() {
        return CharacterType.PLAYABLE;
    }

    public abstract int getMaxRange();

    public void addFaction(Faction faction) {
        factions.put(faction.getId(), faction);
    }

    public void removeFaction(Faction faction) {
        factions.remove(faction.getId());
    }

}
