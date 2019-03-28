package com.predictor.controller;


import com.jcabi.aspects.Loggable;
import com.predictor.api.BadRequestResponse;
import com.predictor.dao.entity.Forecast;
import com.predictor.dao.ForecastDAO;
import com.predictor.api.ForecastPredictionRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class PredictionController {

    @Autowired
    private ForecastDAO forecastDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(PredictionController.class);

    @RequestMapping(value = "clima",method = RequestMethod.GET)
    @ResponseBody
    @Loggable(value = 2)
    public ResponseEntity<Object> getforecast(@ModelAttribute ForecastPredictionRequestDTO query){
        LOGGER.info("GET /clima?dia={}",query.getDia());
        final Optional<Forecast> maybeForecast = forecastDAO.get(query.getDia());
        if(maybeForecast.isPresent()){
            return new ResponseEntity<>(maybeForecast.get(), OK);
        }
        final String message = String.format("Day %d don't found",query.getDia());
        return new ResponseEntity<>(new BadRequestResponse(400,message,newArrayList("The day is not in db")), BAD_REQUEST);
    }
}
