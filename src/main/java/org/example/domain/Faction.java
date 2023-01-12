package org.example.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class Faction {
    private final Map<String, PlayableCharacter> members;
    private final String id;


    public Faction() {
        members = new HashMap<>();
        id = UUID.randomUUID().toString();
    }

    public void join(PlayableCharacter character) {
        members.put(character.getId(), character);
        character.addFaction(this);
    }

    public void leave(PlayableCharacter character) {
        members.remove(character.getId());
        character.removeFaction(this);
    }

}
