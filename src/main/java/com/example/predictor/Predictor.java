package com.example.predictor;

import com.example.predictor.condition.*;
import com.example.predictor.system.SolarSystem;
import com.example.predictor.weather.Weather;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

import static com.google.common.collect.ImmutableList.of;

public class Predictor {
    @Autowired
    private RainCondition rainCondition;
    @Autowired
    private OptimumWeatherCondition optimumWeatherCondition;
    @Autowired
    private DroughtCondition droughtCondition;

    private List<? extends WeatherCondition> conditions;

    private static final String NO_CONDITION_WAS_MET_MSG = "no condition was met";

    @PostConstruct
    public void init(){
        conditions = of(rainCondition,optimumWeatherCondition,droughtCondition);
    }


    public Weather predict(SolarSystem solarSystem){
        final Optional<ConditionResult> maybeConditionResult = conditions.stream()
                .map(c -> c.meetsConditions(solarSystem))
                .filter(ConditionResult::isApply).findAny();
        if(maybeConditionResult.isPresent()){
            final ConditionResult conditionResult = maybeConditionResult.get();
            return conditionResult.getWeather();
        }else{
            throw new RuntimeException(NO_CONDITION_WAS_MET_MSG);
        }
    }
}
