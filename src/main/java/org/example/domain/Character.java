package org.example.domain;

import lombok.Data;
import org.example.types.CharacterType;

import java.util.UUID;

@Data
public abstract class Character {
    private final String id;
    private int health;
    private boolean isDestroyed;

    public Character() {
        isDestroyed = false;
        id = UUID.randomUUID().toString();
    }

    public void destroy() {
        health = 0;
        isDestroyed = true;
    }

    public abstract CharacterType getType();


}
