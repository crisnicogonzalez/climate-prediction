package com.predictor.condition;

import com.predictor.universe.Planet;
import com.predictor.universe.Position;
import com.predictor.universe.SolarSystem;
import com.predictor.universe.Sun;
import com.predictor.util.GeometryUtil;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.*;

@Test
public class OptimumWeatherConditionTest {


    @InjectMocks
    private OptimumWeatherCondition target;
    @Mock
    private GeometryUtil geometryUtil;
    private Sun sun;
    private List<Planet> planets;
    private SolarSystem solarSystem;

    @BeforeClass
    private void init(){
        initMocks(this);
        solarSystem = new SolarSystem();
    }

    @Test
    public void testMeetsConditionsShouldReturnTrueWhenPlanetsAreAlignedButTheSunIsNot() {

        final Planet planetOne = new Planet();
        final Planet planetTwo = new Planet();
        final Planet planetThree = new Planet();
        final Sun sun = new Sun();

        final Position p1 = new Position(1,0);
        final Position p2 = new Position(0,2);
        final Position p3 = new Position(0,0);
        final Position p4 = new Position(0,0);


        planetOne.setPosition(p1);
        planetTwo.setPosition(p2);
        planetThree.setPosition(p3);
        sun.setPosition(p4);

        when(geometryUtil.formALine(eq(newArrayList(p1,p2,p3)))).thenReturn(true);
        when(geometryUtil.formALine(eq(newArrayList(p1,p2,p3,p4)))).thenReturn(false);

        assertFalse(target.meetsConditions(solarSystem,0));

    }

    @Test
    public void testGetPrediction() throws Exception {
    }
}