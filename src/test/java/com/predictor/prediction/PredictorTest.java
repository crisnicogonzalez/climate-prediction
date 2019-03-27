package com.predictor.prediction;


import com.predictor.universe.Planet;
import com.predictor.universe.Position;
import com.predictor.universe.SolarSystem;
import com.predictor.universe.Sun;
import com.predictor.util.GeometryUtil;
import com.predictor.weather.Weather;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;


@Test
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
public class PredictorTest extends AbstractTestNGSpringContextTests{

    @Autowired
    private Predictor predictor;
    @Autowired
    private GeometryUtil geometryUtil;

    @Mock
    private Planet p1;
    @Mock
    private Planet p2;
    @Mock
    private Planet p3;
    @Mock
    private Sun sun;

    private SolarSystem solarSystem;

    @BeforeClass
    public void init(){
        initMocks(this);
        solarSystem = new SolarSystem(newArrayList(p1,p2,p3),sun);
    }


    @BeforeMethod
    public void reset(){
        Mockito.reset(p1,p2,p3,sun);
    }





    @Test
    public void testPredictorShouldReturnRainWhenThePlanetsFormATriangleAndTheSunIsInside() {
        final Position pos1 = new Position(0,0);
        final Position pos2 = new Position(3,0);
        final Position pos3 = new Position(2,2);
        final Position sunPos = new Position(2,1);

        final int day = 1;
        when(p1.getPositionForDay(day)).thenReturn(pos1);
        when(p2.getPositionForDay(day)).thenReturn(pos2);
        when(p3.getPositionForDay(day)).thenReturn(pos3);

        when(sun.getPosition()).thenReturn(sunPos);



        final WeatherPrediction predict = predictor.predict(solarSystem, day);
        assertEquals(predict.getWeather(), Weather.RAIN);
    }


    @Test
    public void testPredictorShouldReturnRainWhenThePlanetsFormATriangleAndTheSunIsInTheEdge() {
        final Position pos1 = new Position(0,0);
        final Position pos2 = new Position(3,0);
        final Position pos3 = new Position(2,2);
        final Position sunPos = new Position(2,0);

        final int day = 1;
        when(p1.getPositionForDay(day)).thenReturn(pos1);
        when(p2.getPositionForDay(day)).thenReturn(pos2);
        when(p3.getPositionForDay(day)).thenReturn(pos3);

        when(sun.getPosition()).thenReturn(sunPos);



        final WeatherPrediction predict = predictor.predict(solarSystem, day);
        assertEquals(predict.getWeather(), Weather.RAIN);
    }

    @Test
    public void testPredictorShouldReturnUnknownWhenThePlanetsFormATriangleAndTheSunIsNotInside() {
        final Position pos1 = new Position(0,0);
        final Position pos2 = new Position(3,0);
        final Position pos3 = new Position(2,2);
        final Position sunPos = new Position(10,10);

        final int day = 1;
        when(p1.getPositionForDay(day)).thenReturn(pos1);
        when(p2.getPositionForDay(day)).thenReturn(pos2);
        when(p3.getPositionForDay(day)).thenReturn(pos3);

        when(sun.getPosition()).thenReturn(sunPos);



        final WeatherPrediction predict = predictor.predict(solarSystem, day);
        assertEquals(predict.getWeather(), Weather.UNKNOWN);
    }


    /**The line is x + 1*/
    @Test
    public void testPredictorShouldReturnDroughtWhenSolarSystemIsAligned(){
        final Position pos1 = new Position(1,1);
        final Position pos2 = new Position(2,2);
        final Position pos3 = new Position(3,3);
        final Position sunPos = new Position(4,4);

        final int day = 1;
        when(p1.getPositionForDay(day)).thenReturn(pos1);
        when(p2.getPositionForDay(day)).thenReturn(pos2);
        when(p3.getPositionForDay(day)).thenReturn(pos3);
        when(sun.getPosition()).thenReturn(sunPos);

        final WeatherPrediction predict = predictor.predict(solarSystem, day);

        assertEquals(predict.getWeather(),Weather.DROUGHT);
    }


    /**
     *
     * x----x-----x-----x
     * */

    @Test
    public void testPredictorShouldReturnDroughtWhenSolarSystemIsAlignedIsLineX(){
        final Position pos1 = new Position(0,0);
        final Position pos2 = new Position(1,0);
        final Position pos3 = new Position(2,0);
        final Position sunPos = new Position(3,0);

        final int day = 1;
        when(p1.getPositionForDay(day)).thenReturn(pos1);
        when(p2.getPositionForDay(day)).thenReturn(pos2);
        when(p3.getPositionForDay(day)).thenReturn(pos3);
        when(sun.getPosition()).thenReturn(sunPos);

        final WeatherPrediction predict = predictor.predict(solarSystem, day);

        assertEquals(predict.getWeather(),Weather.DROUGHT);
    }



    /**
     *
     * --------------------s-------------
     * ----------p1---------p2---------p3
     *
     * */

    @Test
    public void testPredictorShouldOptimumWhenThePlanetsAreAlignedButTheSunIsNot(){
        final Position pos1 = new Position(0,0);
        final Position pos2 = new Position(1,0);
        final Position pos3 = new Position(2,0);

        final Position sunPos = new Position(2,2);

        final int day = 1;
        when(p1.getPositionForDay(day)).thenReturn(pos1);
        when(p2.getPositionForDay(day)).thenReturn(pos2);
        when(p3.getPositionForDay(day)).thenReturn(pos3);
        when(sun.getPosition()).thenReturn(sunPos);


        final WeatherPrediction predict = predictor.predict(solarSystem, day);


        assertEquals(predict.getWeather(),Weather.OPTIMUM);
    }
}