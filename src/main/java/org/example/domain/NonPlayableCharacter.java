package org.example.domain;

import org.example.types.CharacterType;

public class NonPlayableCharacter extends Character {

    @Override
    public CharacterType getType() {
        return CharacterType.NON_PLAYABLE;
    }
}
