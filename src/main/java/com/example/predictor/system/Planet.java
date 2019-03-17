package com.example.predictor.system;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

public enum Planet {

    VULCANO(1,500),
    FERENGI(3,2000),
    BETASOID(5,1000);

    private Integer angularVelocity;
    private Position position;

    Planet(Integer angularVelocity, Integer distanceSun){
        final Position position = new Position(ZERO,BigDecimal.valueOf(distanceSun));
        this.angularVelocity = angularVelocity;
        this.position = position;
    }

    public Integer getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(Integer angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
