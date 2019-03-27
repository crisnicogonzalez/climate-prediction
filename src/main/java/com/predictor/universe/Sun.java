package com.predictor.universe;


import org.springframework.stereotype.Component;

@Component
public class Sun extends Positionable {


    public Sun() {
        super(new Position(0,0));
    }
}
