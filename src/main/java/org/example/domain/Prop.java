package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Prop extends NonPlayableCharacter{
    private int health;
    private boolean isDestroyed;
}
