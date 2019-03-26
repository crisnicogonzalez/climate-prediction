package com.predictor.dao;


import com.predictor.weather.Weather;
import org.springframework.stereotype.Component;

@Component
public class DayPredictionDAO {


    //@Autowired
    //private SessionFactory sessionFactory;

    public void save(DayPrediction dayPrediction){}


    /**
     * Find prediction by day
     * @param day is a int not null
     * @return prediction of day
     * */
    public DayPrediction get(int day){
        //final Session currentSession = this.sessionFactory.getCurrentSession();
        //Query queryResult = currentSession.createQuery("from");
        return new DayPrediction(day, Weather.DROUGHT,10);
    }
}
