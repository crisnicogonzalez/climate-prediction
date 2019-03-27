package com.predictor.report;

public class Report {

    private int droughtQuantity;
    private int optimumWeatherQuantity;
    private int rainQuantity;
    private long moreRainDay;

    public void setDroughtQuantity(int droughtQuantity) {
        this.droughtQuantity = droughtQuantity;
    }

    public void setOptimumWeatherQuantity(int optimumWeatherQuantity) {
        this.optimumWeatherQuantity = optimumWeatherQuantity;
    }

    public void setRainQuantity(int rainQuantity) {
        this.rainQuantity = rainQuantity;
    }

    public void setMoreRainDay(long moreRainDay) {
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
