package com.oddle.app.weather.repository.impl;

import com.oddle.app.weather.model.internalmapper.IWeatherSummary;
import com.oddle.app.weather.repository.WeatherRepositoryExtension;
import com.oddle.app.weather.util.DateUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

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

    @SuppressWarnings("unchecked")
    @Override
    public List<IWeatherSummary> getWeathers(String city, Date startDate, Date endDate, int offset, int limit) {
        String queryStr = "SELECT w.* FROM weather.weather as w WHERE w.Date";

        if (endDate != null) {
            queryStr += " BETWEEN ? AND ?";
        } else {
            queryStr += " = ?";
        }

        if (city != null) {
            queryStr += " AND w.City = ?";
        }

        queryStr += " LIMIT ? OFFSET ?";

        Query query = entityManager.createNativeQuery(queryStr, IWeatherSummary.class);
        query.setParameter(1, DateUtil.getDateInString(startDate));

        if (endDate != null) {
            query.setParameter(2, DateUtil.getDateInString(endDate));
        }

        if (endDate != null && city != null) {
            query.setParameter(3, city);
            query.setParameter(4, limit);
            query.setParameter(5, offset);
        } else if (endDate == null && city != null) {
            query.setParameter(2, city);
            query.setParameter(3, limit);
            query.setParameter(4, offset);
        } else {
            query.setParameter(2, limit);
            query.setParameter(3, offset);
        }

        return query.getResultList();
    }
}
