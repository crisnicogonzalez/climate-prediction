package com.predictor.util;


import com.predictor.universe.Position;
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

    @Test
    public void testFormATriangleShouldReturnFalseWhenThePositionsAreFromALine(){
        final List<Position> positions = this.generatePoints(2,3,3);

        final Position p1 = positions.get(0);
        final Position p2 = positions.get(1);
        final Position p3 = positions.get(2);

        final boolean result = target.formATriangle(p1,p2,p3);

        Assert.assertFalse(result);
    }

    @Test
    public void testFormATriangleShouldReturnTrueWhenAPointIsNotFromTheLine(){
        final List<Position> positions = this.generatePoints(2,3,3);

        final Position p1 = positions.get(0);
        p1.setX(p1.getX() + 1);

        final Position p2 = positions.get(1);

        final Position p3 = positions.get(2);

        final boolean result = target.formATriangle(p1,p2,p3);
        Assert.assertTrue(result);
    }


    @Test
    public void testPointInsideOfTriangleShouldReturnTrue(){
        final Position p1 = new Position(0,0);
        final Position p2 = new Position(1,0);
        final Position p3 = new Position(0,1);
        final Position insidePosition = new Position(0.5,0.5);

        Assert.assertTrue(target.pointInsideOfTriangle(p1,p2,p3,insidePosition));
    }

    @Test
    public void testPointInsideOfTriangleShouldReturnFalse(){
        final Position p1 = new Position(0,0);
        final Position p2 = new Position(1,0);
        final Position p3 = new Position(0,1);
        final Position insidePosition = new Position(1,1);

        Assert.assertFalse(target.pointInsideOfTriangle(p1,p2,p3,insidePosition));
    }


    @Test
    public void testPointInsideOfTriangleShouldReturnFalseBecauseThePointAreTheEdge(){
        final Position p1 = new Position(0,0);
        final Position p2 = new Position(1,0);
        final Position p3 = new Position(0,1);
        final Position insidePosition = new Position(0,1);

        Assert.assertTrue(target.pointInsideOfTriangle(p1,p2,p3,insidePosition));
    }



    @Test
    public void testCalculatePerimeterOfTriangleShouldReturnCorrectly(){
        final Position p1 = new Position(0,0);
        final Position p2 = new Position(1,0);
        final Position p3 = new Position(0,1);

        final double difference = Math.abs(target.calculatePerimeterOfTriangle(p1,p2,p3) - 3.4142);
        Assert.assertTrue(difference < 0.001);

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