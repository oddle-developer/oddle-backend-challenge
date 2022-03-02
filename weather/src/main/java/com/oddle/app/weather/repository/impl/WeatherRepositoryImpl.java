package com.oddle.app.weather.repository.impl;

import com.oddle.app.weather.util.HibernateUtil;
import com.oddle.app.weather.WeatherErrorMap;
import com.oddle.app.weather.dto.WeatherDTO;
import com.oddle.app.weather.exception.WeatherException;
import com.oddle.app.weather.model.Weather;
import com.oddle.app.weather.repository.WeatherRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WeatherRepositoryImpl implements WeatherRepository {

    @Transactional
    public void store(Weather weather) throws WeatherException {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(weather);
            transaction = session.getTransaction();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            session.close();
            e.printStackTrace();
            throw new WeatherException(WeatherErrorMap.SYSTEM_FAILURE, "Failed to store data");
        }
    };

    @Override
    @Transactional
    public List<Weather> getWeather(String city, String from, String to) throws WeatherException{
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<Weather> list = new ArrayList<>();
            Query query = session.createNativeQuery("SELECT * FROM weather WHERE date >= '" + from + "' AND date <= '" + to + "'", "WeatherDTOMapping");
            List<WeatherDTO> resultList = query.getResultList();
            for (WeatherDTO weatherDTO : resultList) {
                list.add(weatherDTO.toModel());
            }
            session.close();
            return list;
        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw new WeatherException(WeatherErrorMap.SYSTEM_FAILURE, "Failed to get data");
        }
    }

    @Override
    public Weather getWeather(String city, String date) throws WeatherException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            NativeQuery query = session.createNativeQuery("SELECT * FROM weather WHERE city = '" + city + "' AND date = '" + date + "'", "WeatherDTOMapping");
            List<WeatherDTO> resultList = query.getResultList();
            session.close();
            if (resultList.size() == 0) {
                return null;
            }
            return resultList.get(0).toModel();
        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw new WeatherException(WeatherErrorMap.SYSTEM_FAILURE, "Failed to get data");
        }
    }

    @Transactional
    public void delete(Weather weather) throws WeatherException {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(weather);
            transaction = session.getTransaction();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            session.close();
            e.printStackTrace();
            throw new WeatherException(WeatherErrorMap.SYSTEM_FAILURE, "Failed to delete data");
        }
    };

    @Transactional
    public void update(Weather weather) throws WeatherException {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(weather);
            transaction = session.getTransaction();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            session.close();
            e.printStackTrace();
            throw new WeatherException(WeatherErrorMap.SYSTEM_FAILURE, "Failed to update data");
        }
    };

}
