package com.predictor.universe;

public class Positionable {
    private Position position;

    public Positionable(Position position) {
        this.position = position;
    }


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
