package com.predictor.util;

import com.predictor.universe.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Math.sqrt;


@Component
public class GeometryUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeometryUtil.class);
    private static final String COMPARE_SLOPE_MSG = "Pivot slope {} and calculated slope {}";
    private static final String CALCULATE_SLOPE_OF_POSITIONS_MSG = "P1 {} and P2 {}";
    private final static double EPSILON = .00001;
    private static final String CANNOT_CALCULATE_PERIMETER_MSG = "Can not calculate the perimeter of the points  p1: {} p2: {} p3: {}";
    private static final String POSITIONS_FORM_A_TRIANGLE_MSG = "Calculate if the points form a triangle  p1: {} p2: {} p3: {}";
    private static final String POSITION_INSIDE_OF_TRIANGLE_MSG = "Calculate if the point is inside of a triangle  p1: {} p2: {} p3: {} p4:{}";



    /**
     * check if positions form a straight line. For that, calculate slope for
     * each pair of points
     * @param positions 'are' the points of the straight line
     * @pre positions size's should be at least two
     * @return return true if all pair points have the same slope else false
     * */
    public boolean formALine(List<Position> positions){
        final Position pivot = positions.get(0);
        final double slope = this.calculateSlope(pivot,positions.get(1));
        return positions.subList(1,positions.size()).stream().allMatch( p-> {
            final double slopeCalculated = this.calculateSlope(pivot,p);
            LOGGER.debug(COMPARE_SLOPE_MSG,slope,slopeCalculated);
            return this.compareDouble(slope,slopeCalculated);
        });
    }

    private boolean compareDouble(double d1,double d2){
        return d1 == d2 || Math.abs(d1 - d2) < EPSILON;
    }

    private double calculateSlope(Position p1, Position p2) {
        LOGGER.debug(CALCULATE_SLOPE_OF_POSITIONS_MSG,p1,p2);
        final double x1 = p1.getX();
        final double y1 = p1.getY();

        final double x2 = p2.getX();
        final double y2 = p2.getY();

        final double dy = y2 - y1;
        final double dx = x2 - x1;
        return dy/dx;
    }


    /**
     *
     * @param p1,p2,p3 are the vertices of the triangle
     * @pre planetsPositions size's should be three
     * @return if the planetsPositions form a triangle return true else false.
     * If planetsPositions size's less of three return false
     * */
    public boolean formATriangle(Position p1,Position p2,Position p3){
        LOGGER.debug(POSITIONS_FORM_A_TRIANGLE_MSG,p1,p2,p3);
        return !this.formALine(newArrayList(p1,p2,p3));
    }


    /**
     *
     * @param p1,p2 and p3 are the vertices of the triangle
     * @param p is the point you want to find out if it is inside the triangle
     * @return true if the point is inside or the edge another case false
     * */

    public boolean pointInsideOfTriangle(Position p1,Position p2,Position p3,Position p){
        LOGGER.debug(POSITION_INSIDE_OF_TRIANGLE_MSG,p1,p2,p3,p);
        final boolean d1 = this.calculateOrientation(p,p1,p2);
        final boolean d2 = this.calculateOrientation(p,p2,p3);
        final boolean d3 = this.calculateOrientation(p,p3,p1);

        return d1 == d2 &&  d2 == d3;

    }

    private boolean calculateOrientation(Position p1,Position p2,Position p3){
        final double p1x_P3x = p1.getX() - p3.getX();
        final double p1y_P3y = p1.getY() - p3.getY();
        final double p2x_P3x = p2.getX() - p3.getX();
        final double p2y_P3y = p2.getY() - p3.getY();
        return (p1x_P3x*p2y_P3y - p1y_P3y*p2x_P3x) < 0;
    }


    private boolean isHigher(double d1,double d2,double d3){
        return  d1 > d2 && d1 > d3;
    }


    public double calculatePerimeterOfTriangle(Position p1,Position p2,Position p3){
        if(!this.formATriangle(p1,p2,p3)){
            LOGGER.error(CANNOT_CALCULATE_PERIMETER_MSG,p1,p2,p3);
            throw new RuntimeException("Calculate perimeter error, is a invalid triangle");
        }

        final double distanceP1P2 = this.calculateDistanceBetweenPoints(p1,p2);
        final double distanceP2P3 = this.calculateDistanceBetweenPoints(p2,p3);
        final double distanceP3P1 = this.calculateDistanceBetweenPoints(p3,p1);
        return distanceP1P2 + distanceP2P3 + distanceP3P1;
    }


    private double calculateDistanceBetweenPoints(Position p1,Position p2){
        final double x1 = p1.getX();
        final double y1 = p1.getY();
        final double x2 = p2.getX();
        final double y2 = p2.getY();
        return sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

}
