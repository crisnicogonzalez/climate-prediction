package com.predictor.prediction;

import com.predictor.condition.*;
import com.predictor.universe.SolarSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;


@Component
public class Predictor {


    private List<WeatherCondition> conditions;

    private static final String NO_CONDITION_WAS_MET_MSG = "no condition was met";
    private static final WeatherCondition DEFAULT_CONDITION = new NoOneCondition();

    @Autowired
    public Predictor(List<WeatherCondition> conditions) {
        this.conditions = conditions;
    }

    public WeatherCondition predict(SolarSystem solarSystem,int day){
        return  conditions.stream()
                .filter( c -> c.meetsConditions(solarSystem,day))
                .findAny()
                .orElse(DEFAULT_CONDITION);
        }
}
