package com.predictor.prediction;

import com.predictor.condition.*;
import com.predictor.system.SolarSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.google.common.collect.ImmutableList.of;


@Component
public class Predictor {


    private List<? extends WeatherCondition> conditions;

    private static final String NO_CONDITION_WAS_MET_MSG = "no condition was met";


    @Autowired
    public Predictor(List<WeatherCondition> conditions) {
        this.conditions = conditions;
    }

    public Prediction predict(SolarSystem solarSystem,int day){
        return  conditions.stream()
                .filter( c -> c.meetsConditions(solarSystem) )
                .map(c -> c.getPrediction(solarSystem))
                .findAny()
                .orElseThrow(() -> new RuntimeException(NO_CONDITION_WAS_MET_MSG));
        }
}

