package com.oddle.app.weather.repository.impl;

import com.oddle.app.weather.model.internalmapper.IWeatherSummary;
import com.oddle.app.weather.repository.WeatherRepositoryExtension;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@Transactional(readOnly = true)
public class WeatherRepositoryImpl implements WeatherRepositoryExtension {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public IWeatherSummary getWeatherByCityAndDate(String city, String date) {
        Query query = entityManager.createNativeQuery("SELECT w.* FROM weather.weather as w " +
                "WHERE w.City = ? AND w.Date = ?", IWeatherSummary.class);
        query.setParameter(1, city);
        query.setParameter(2, date);

        return (IWeatherSummary) query.getResultList().stream().findFirst().orElse(null);
    }
}
