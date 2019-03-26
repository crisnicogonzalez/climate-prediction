package com.predictor.dao;


import com.predictor.db.PoolConnection;
import com.predictor.weather.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Component
public class DayPredictionDAO implements Dao<DayPrediction>{

    private static String INSERT_OR_UPDATE = "INSERT INTO prediction_day(day,weather,intensity) VALUES(?,?,?)";
    private static String DELETE= "INSERT INTO prediction_day(day,weather,intensity) VALUES(?,?,?)";
    private static String GET_BY_ID= "SELECT * FROM prediction_day where id=?";
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS prediction_day"+
            "(day INT PRIMARY KEY NOT NULL,"+
            "weather CHAR(50) NOT NULL,"+
            "intensity DECIMAL(10,4))";
    @Autowired
    private PoolConnection poolConnection;



    @Override
    public Optional<DayPrediction> get(long id) {
        try{
            final Statement statement = poolConnection.getConnection().createStatement();
            final ResultSet resultSet = statement.executeQuery(GET_BY_ID);
            return Optional.empty();
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public List<DayPrediction> getAll() {
        return null;
    }

    @Override
    public void save(DayPrediction dayPrediction) {

    }

    @Override
    public void update(DayPrediction dayPrediction, String[] params) {

    }

    @Override
    public void delete(DayPrediction dayPrediction) {

    }
}
