package com.predictor.report;

import com.predictor.condition.DroughtCondition;
import com.predictor.condition.OptimumWeatherCondition;
import com.predictor.condition.RainCondition;
import com.predictor.condition.WeatherCondition;
import com.predictor.prediction.Prediction;
import org.springframework.stereotype.Component;

@Component
public class ReportBuilder {


    private int droughtQuantity = 0;
    private int optimumWeatherQuantity = 0;
    private int rainQuantity = 0;
    private int moreRainyDay = 0;
    private double currentIntensityRain = 0;


    public void register(WeatherCondition condition, Prediction prediction){}

    public void register(DroughtCondition condition, Prediction prediction){
        droughtQuantity++;
    }

    public void register(RainCondition condition,Prediction prediction){
        rainQuantity++;
        if(prediction.getIntensity() > currentIntensityRain){
            currentIntensityRain = prediction.getIntensity();
            moreRainyDay = 0;
        }
    }

    public void register(OptimumWeatherCondition condition,Prediction prediction){
        optimumWeatherQuantity++;
    }

    public Report doReport(){
        Report report = new Report();
        report.setDroughtQuantity(this.droughtQuantity);
        report.setOptimumWeatherQuantity(this.optimumWeatherQuantity);
        report.setRainQuantity(this.rainQuantity);
        report.setMoreRainDay(this.moreRainyDay);
        return report;
    }





}
