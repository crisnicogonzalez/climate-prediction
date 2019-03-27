package com.predictor.report;

public class Report {

    private int droughtQuantity;
    private int optimumWeatherQuantity;
    private int rainQuantity;
    private int moreRainDay;

    public void setDroughtQuantity(int droughtQuantity) {
        this.droughtQuantity = droughtQuantity;
    }

    public void setOptimumWeatherQuantity(int optimumWeatherQuantity) {
        this.optimumWeatherQuantity = optimumWeatherQuantity;
    }

    public void setRainQuantity(int rainQuantity) {
        this.rainQuantity = rainQuantity;
    }

    public void setMoreRainDay(int moreRainDay) {
        this.moreRainDay = moreRainDay;
    }

    @Override
    public String toString() {
        return "Report{" +
                "Cantidad de periodos con sequia=" + droughtQuantity +
                ", Cantidad de periodos con clima optimo=" + optimumWeatherQuantity +
                ", Cantidad de dias con lluvia=" + rainQuantity +
                ", Dia mas lluvioso=" + moreRainDay +
                '}';
    }
}
