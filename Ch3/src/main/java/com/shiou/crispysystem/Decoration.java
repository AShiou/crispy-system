package com.shiou.crispysystem;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Decoration {
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        CREAM, FRUIT, CANDY, CANDLE
    }
}
