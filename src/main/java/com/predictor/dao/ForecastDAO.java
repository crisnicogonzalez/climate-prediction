package com.predictor.dao;


import com.predictor.dao.entity.Forecast;
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
public class ForecastDAO {

    private SessionFactory sessionFactory;

    public ForecastDAO() {
    }

    @Autowired
    @Qualifier("sessionFactory")
    public void init(LocalSessionFactoryBean factory) {
        this.sessionFactory = factory.getObject();
    }

    @SuppressWarnings("unchecked")
    public Optional<Forecast> get(long day) {
        final Session session = this.sessionFactory.getCurrentSession();
        final Criteria criteria = session.createCriteria(Forecast.class);
        criteria.add(eq("day", day));
        return safeStream(criteria.list()).findAny();
    }

    public Optional<Forecast> save(Forecast dayPrediction) {
        final Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(dayPrediction);
        return Optional.ofNullable(dayPrediction);
    }


    public void saveAll(List<Forecast> predictions){
        final Session session = this.sessionFactory.getCurrentSession();
        predictions.forEach(session::save);
    }

    public void delete(Forecast dayPrediction) {
        final Session session = this.sessionFactory.getCurrentSession();
        session.delete(dayPrediction);
    }
}
