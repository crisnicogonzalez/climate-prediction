package com.example.predictor.weather;

import java.math.BigDecimal;

public enum Weather {
    DROUGHT,RAIN,OPTIMUM;

    private BigDecimal intensity;

    Weather(BigDecimal intensity) {
        this.intensity = intensity;
    }

    Weather() {

    }

    public BigDecimal getIntensity() {
        return intensity;
    }

    public void setIntensity(BigDecimal intensity) {
        this.intensity = intensity;
    }
}
