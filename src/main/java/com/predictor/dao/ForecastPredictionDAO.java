package com.predictor.dao;


import com.predictor.dao.entity.ForecastPrediction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.predictor.util.CollectionUtils.safeStream;
import static org.hibernate.criterion.Restrictions.eq;

@Transactional
@Repository
public class ForecastPredictionDAO{

    private SessionFactory sessionFactory;

    public ForecastPredictionDAO() {
    }

    @Autowired
    @Qualifier("sessionFactory")
    public void init(LocalSessionFactoryBean factory) {
        this.sessionFactory = factory.getObject();
    }

    @SuppressWarnings("unchecked")
    public Optional<ForecastPrediction> get(long day) {
        final Session session = this.sessionFactory.getCurrentSession();
        final Criteria criteria = session.createCriteria(ForecastPrediction.class);
        criteria.add(eq("day", day));
        return safeStream(criteria.list()).findAny();
    }

    public Optional<ForecastPrediction> save(ForecastPrediction dayPrediction) {
        final Session session = this.sessionFactory.getCurrentSession();
        session.save(dayPrediction);
        return Optional.ofNullable(dayPrediction);
    }


    public void saveAll(List<ForecastPrediction> predictions){
        final Session session = this.sessionFactory.getCurrentSession();
        predictions.forEach(session::save);
    }

    public void delete(ForecastPrediction dayPrediction) {
        final Session session = this.sessionFactory.getCurrentSession();
        session.delete(dayPrediction);
    }
}
