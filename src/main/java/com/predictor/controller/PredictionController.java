package com.predictor.controller;


import com.predictor.prediction.DayPrediction;
import com.predictor.util.GeometryUtil;
import com.predictor.weather.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class PredictionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PredictionController.class);

    @RequestMapping(value = "clima",method = RequestMethod.GET)
    @ResponseBody
    public DayPrediction getforecast(@ModelAttribute ForecastPredictionQuery query){
        LOGGER.info("Request received for day {}",query.getDia());
        return new DayPrediction(10, Weather.DROUGHT,10.0);

    }
}
