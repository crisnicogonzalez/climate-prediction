package com.predictor.universe;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Planet {

    /*angularVelocity degree for day*/
    private Integer angularVelocity;
    /*initial position*/
    private Position position;
    private int radio;


    Planet(Integer angularVelocity, Integer distanceSun){
        final Position position = new Position(0,distanceSun);
        this.angularVelocity = angularVelocity;
        this.position = position;
        radio = distanceSun;
    }

    public Planet() {
    }

    /**
     * the angular velocity is degree/day then for calculate the current position
     * apply the theory of trig fuctions
    * */

    public Position getPositionForDay(int day){
        final int currentAngle = this.angularVelocity*day;
        final double x = Math.cos(Math.toRadians(currentAngle))*this.radio;
        final double y = Math.sin(Math.toRadians(currentAngle))*this.radio;
        return new Position(x,y);
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
