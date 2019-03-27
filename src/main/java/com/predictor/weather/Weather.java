package com.predictor.weather;


public enum Weather {
    DROUGHT,
    RAIN,
    OPTIMUM,
    /**this case is when the system prediction cannot predict a weather*/
    UNKNOWN,
    /**this case is when happen a error in system prediction for specific day*/
    INCALCULABLE
}
