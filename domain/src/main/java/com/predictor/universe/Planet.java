package com.predictor.universe;


import com.predictor.condition.OptimumWeatherCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Planet {

    VULCANO(-1,500),
    FERENGI(-3,2000),
    BETASOID(5,1000);

    private static final Logger LOGGER = LoggerFactory.getLogger(Planet.class);



    /*angularVelocity radians for day*/
    private Integer angularVelocity;
    /*initial position*/
    private Position position;
    private int radio;
    private static final int INITIAL_ANGLE = 90;

    Planet(Integer angularVelocity, Integer distanceSun){
        final Position position = new Position(0,distanceSun);
        this.angularVelocity = angularVelocity;
        this.position = position;
        radio = distanceSun;
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

    public Position getPositionForDay(int day){
        final int currentAngle = this.angularVelocity*day + INITIAL_ANGLE;
        final double x = Math.cos(Math.toRadians(currentAngle))*this.radio;
        final double y = Math.sin(Math.toRadians(currentAngle))*this.radio;
        return new Position(x,y);
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
