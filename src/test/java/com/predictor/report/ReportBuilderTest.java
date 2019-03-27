package com.predictor.report;

import com.predictor.prediction.WeatherPrediction;
import com.predictor.weather.Weather;
import org.mockito.InjectMocks;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Map;

import static com.predictor.weather.Weather.RAIN;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class ReportBuilderTest {

    @InjectMocks
    private ReportBuilder reportBuilder;

    @BeforeClass
    public void init(){
        initMocks(this);
        reportBuilder.init();
    }



    @Test
    public void testReportBuilderShouldReturnCorrectly() {
        reportBuilder.reset();

        final int quantityOfDayRain = 10;
        final double sameIntensity = 10;

        this.register(reportBuilder, RAIN,sameIntensity,quantityOfDayRain);


        final Map<Weather, Integer> quantityOfDaysByWeather = reportBuilder.getQuantityOfDaysByWeather();
        final long moreRainyDay = reportBuilder.getMoreRainyDay();


        Assert.assertEquals(quantityOfDayRain,(int) quantityOfDaysByWeather.get(RAIN));
        Assert.assertEquals(moreRainyDay,0);
    }


    @Test
    public void testReportBuilderShouldReturnCorrectlyIfExistADayMoreIntensity() {
        reportBuilder.reset();
        final int quantityOfDayRain = 10;
        final double sameIntensity = 10;
        final double higherIntensity = 20;
        final int dayMoreRainExcepted = 21;

        this.register(reportBuilder, RAIN,sameIntensity,quantityOfDayRain);

        reportBuilder.register(new WeatherPrediction(RAIN,higherIntensity),dayMoreRainExcepted);

        final Map<Weather, Integer> quantityOfDaysByWeather = reportBuilder.getQuantityOfDaysByWeather();
        final long moreRainyDayActual = reportBuilder.getMoreRainyDay();


        Assert.assertEquals(quantityOfDayRain + 1,(int) quantityOfDaysByWeather.get(RAIN));
        Assert.assertEquals(moreRainyDayActual,dayMoreRainExcepted);
    }



    private void register(ReportBuilder reportBuilder,Weather weather,double intensity,int quantityDays){
        for (int i = 0; i<quantityDays;i++){
            reportBuilder.register(new WeatherPrediction(weather,intensity),i);
        }
    }
}