package org.example.domain;

public class MeleeFighter extends PlayableCharacter{
    private final int MAX_DAMAGE_RANGE = 2;

    public MeleeFighter() {
        super();
    }

    @Override
    public int getMaxRange() {
        return MAX_DAMAGE_RANGE;
    }

}
