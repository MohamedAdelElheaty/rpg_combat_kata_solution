package org.example.domain;

public class RangedFighter extends PlayableCharacter {

    private final int MAX_DAMAGE_RANGE = 20;

    public RangedFighter() {
        super();
    }

    @Override
    public int getMaxRange() {
        return MAX_DAMAGE_RANGE;
    }
}
