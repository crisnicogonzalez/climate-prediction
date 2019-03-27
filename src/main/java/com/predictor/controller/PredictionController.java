package com.predictor.controller;


import com.jcabi.aspects.Loggable;
import com.predictor.dao.entity.ForecastPrediction;
import com.predictor.dao.ForecastPredictionDAO;
import com.predictor.request.ForecastPredictionRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PredictionController {

    @Autowired
    private ForecastPredictionDAO forecastPredictionDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(PredictionController.class);

    @RequestMapping(value = "clima",method = RequestMethod.GET)
    @ResponseBody
    @Loggable(value = 2)
    public ForecastPrediction getforecast(@ModelAttribute ForecastPredictionRequestDTO query){
        LOGGER.info("GET /clima?dia={}",query.getDia());
        return forecastPredictionDAO.get(query.getDia()).orElseGet(ForecastPrediction::new);
    }
}
