package com.predictor.condition;

import com.predictor.universe.Planet;
import com.predictor.universe.Position;
import com.predictor.universe.SolarSystem;
import com.predictor.universe.Sun;
import com.predictor.util.GeometryUtil;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.*;


@Test
public class DroughtConditionTest {

    @InjectMocks
    private DroughtCondition target;
    @Mock
    private GeometryUtil geometryUtil;
    private Sun sun;
    private List<Planet> planes;


    @BeforeClass
    private void init(){
        initMocks(this);
        sun = new Sun();
        planes = newArrayList(Planet.VULCANO,Planet.BETASOID,Planet.FERENGI);
    }


    @Test
    public void testMeetsConditionsForAlignedSolarSystem(){
        final SolarSystem solarSystem = new SolarSystem();

        when(geometryUtil.formALine(Mockito.anyListOf(Position.class))).thenReturn(true);
        target.getPrediction(solarSystem,0);

        assertTrue(target.meetsConditions(solarSystem,0));

    }

    @Test
    public void testMeetsConditionShouldReturnFalseSWhenTheSolarSystemIsNotAligned(){
        final SolarSystem solarSystem = new SolarSystem();
        when(geometryUtil.formALine(Mockito.anyListOf(Position.class))).thenReturn(false);
        target.getPrediction(solarSystem,0);
        assertFalse(target.meetsConditions(solarSystem,0));
    }

    @Test
    public void testGetPrediction() throws Exception {
    }
}