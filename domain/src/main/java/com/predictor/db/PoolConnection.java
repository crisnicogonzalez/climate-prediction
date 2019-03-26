package com.predictor.db;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Component
public class PoolConnection {

    private static final Logger LOGGER = LoggerFactory.getLogger(PoolConnection.class);
    private static final String USER = "meli_predictor";
    private static final String PASS = "5jnS1x";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/meli_predictor";
    private Connection connection;
    private Statement stmt;

    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS prediction_day"+
            "(day INT PRIMARY KEY NOT NULL,"+
            "weather CHAR(50) NOT NULL,"+
            "intensity DECIMAL(10,4))";

    private static String INSERT_OR_UPDATE = "INSERT INTO prediction_day(day,weather,intensity) VALUES(?,?,?)";



    @PostConstruct
    public void init(){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL,USER, PASS);
            LOGGER.info("Opened database successfully");
            this.createTable();
        } catch (Exception ex) {
            LOGGER.error("Cannot register the postgreSQL driver's", ex);
            throw new RuntimeException(ex);
        }
    }


    private void createTable(){
        try{
            final Statement statement = connection.createStatement();
            statement.execute(CREATE_TABLE_QUERY);
            statement.close();
            LOGGER.info("Created table successfully");
        }catch (Exception e){
            LOGGER.error("Cannot create table",e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
