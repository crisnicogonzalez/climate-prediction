package com.predictor.controller;


import com.predictor.request.ForecastPredictionRequestDTO;
import com.predictor.dao.DayPrediction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.predictor.service.PredictionService;

@RestController
public class PredictionController {

    @Autowired
    private PredictionService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(PredictionController.class);

    @RequestMapping(value = "clima",method = RequestMethod.GET)
    @ResponseBody
    public DayPrediction getforecast(@ModelAttribute ForecastPredictionRequestDTO query){
        LOGGER.info("Request received for day {}",query.getDia());
        return service.getPrediction(query.getDia());
    }
}
