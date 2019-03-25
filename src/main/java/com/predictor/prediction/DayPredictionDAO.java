package com.predictor.prediction;


import com.predictor.weather.Weather;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DayPredictionDAO {


    @Autowired
    private SessionFactory sessionFactory;

    public void save(DayPrediction dayPrediction){}


    /**
     * Find prediction by day
     * @param day is a int not null
     * @return prediction of day
     * */
    public DayPrediction get(int day){
        final Session currentSession = this.sessionFactory.getCurrentSession();
        Query queryResult = currentSession.createQuery("from");
        return new DayPrediction(day, Weather.DROUGHT,10);
    }
}
