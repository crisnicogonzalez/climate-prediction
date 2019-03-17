package com.example.predictor.util;


import com.example.predictor.system.Position;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Test
public class GeometryUtilTest {


    private static GeometryUtil target = new GeometryUtil();

    @Test
    public void testFormALineShouldReturnTrueWhenAllPointsAreFromSameLine(){
        final List<Position> positions = this.generatePoints(1,1,3);
        final boolean result = target.formALine(positions);
        Assert.assertTrue(result);
    }

    @Test
    public void testFormALineShouldReturnFalseWhenAPointIsNotTheSameLine(){
        final List<Position> positions = this.generatePoints(1,1,3);
        final Position position = positions.get(0);

        //Change a first point
        position.setX(position.getX() + 1);

        final boolean result = target.formALine(positions);

        Assert.assertFalse(result);
    }


    @Test
    public void testFormALineShouldReturnFalseWhenAllPointsNotFormLine(){
        final List<Position> positions = this.generatePoints(1,1,3);

        final Position firstPosition = positions.get(0);
        final Position secondPosition = positions.get(1);

        firstPosition.setX(firstPosition.getX() + 1);
        secondPosition.setX(secondPosition.getX() - 1);

        final boolean result = target.formALine(positions);
        Assert.assertFalse(result);
    }


    @Test
    public void testFormALineShouldReturnTrueWhenAllPointsAreOfTheSameLineGeneric(){
        final List<Position> positions = this.generatePoints(2,3,5);
        final boolean result = target.formALine(positions);
        Assert.assertTrue(result);
    }



    private List<Position> generatePoints(double m,double b,int quantityPoints){
        final List<Position> pointsGenerated = new ArrayList<>();
        final ThreadLocalRandom randomGenerator = ThreadLocalRandom.current();
        for(int i = 0; i < quantityPoints ; i++){
            final double x = randomGenerator.nextDouble();
            final double y = m * x + b;
            pointsGenerated.add(new Position(x,y));
        }
        return pointsGenerated;
    }
}