package com.example.predictor.util;

import com.example.predictor.system.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

public class GeometryUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeometryUtil.class);
    private static final String COMPARE_SLOPE_MSG = "Pivot slope {} and calculated slope {}";
    private static final String CALCULATE_SLOPE_OF_POSITIONS_MSG = "P1 {} and P2 {}";
    private final static double EPSILON = .00001;

    public boolean formALine(List<Position> positions){
        final Position pivot = positions.get(0);
        final double slope = this.calculateSlope(pivot,positions.get(1));
        return positions.subList(1,positions.size()).stream().allMatch( p-> {
            final double slopeCalculated = this.calculateSlope(pivot,p);
            LOGGER.info(COMPARE_SLOPE_MSG,slope,slopeCalculated);
            return this.compareDouble(slope,slopeCalculated);
        });
    }

    private boolean compareDouble(double d1,double d2){
        return d1 == d2 || Math.abs(d1 - d2) < EPSILON;
    }

    private double calculateSlope(Position p1, Position p2) {
        LOGGER.info(CALCULATE_SLOPE_OF_POSITIONS_MSG,p1,p2);
        final double x1 = p1.getX();
        final double y1 = p1.getY();

        final double x2 = p2.getX();
        final double y2 = p2.getY();

        final double dy = y2 - y1;
        final double dx = x2 - x1;
        return dy/dx;
    }



    public boolean formATriangle(List<Position> planetsPositions) {
        return false;
    }

    public boolean pointInsideOfTriangle(Position position, List<Position> planetsPositions) {
        return false;
    }
}
