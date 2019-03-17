package com.example.predictor.condition;

import com.example.predictor.prediction.Prediction;

public class ConditionResult {

    private boolean apply;
    private Prediction prediction;


    public ConditionResult(boolean apply, Prediction prediction) {
        this.apply = apply;
        this.prediction = prediction;
    }

    public ConditionResult(boolean apply) {
        this.apply = apply;
    }

    public boolean isApply() {
        return apply;
    }

    public void setApply(boolean apply) {
        this.apply = apply;
    }

    public Prediction getPrediction() {
        return prediction;
    }

    public void setPrediction(Prediction prediction) {
        this.prediction = prediction;
    }
}
