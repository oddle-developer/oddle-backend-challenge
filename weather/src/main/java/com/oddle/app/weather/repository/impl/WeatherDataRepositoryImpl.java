package com.oddle.app.weather.repository.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.SelectConditionStep;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oddle.app.weather.model.weatherdata.Weather;

@Repository
public class WeatherDataRepositoryImpl {

	@Autowired
	EntityManager em;
	
	public List<Weather> getWeatherHistory(String cityName, LocalDateTime from,LocalDateTime to, int page , int size){
		
		DSLContext create = DSL.using(SQLDialect.MYSQL);
		
		SelectConditionStep<?> sql = create.select(DSL.field("a.*"))
				.from("Weather a")
				.where("a.is_active = :isActive");
		
		sql.and("a.date_time_calculated between :from and :to");
		
		boolean isHavingCityName = (cityName != null && !cityName.isEmpty());
		
		if(isHavingCityName) {
			sql.and("a.name = :name");
		}
		
		Query q = em.createNativeQuery(sql.getSQL(), Weather.class);
		q.setParameter("isActive",true);
		q.setParameter("from", from);
		q.setParameter("to", to);
        q.setFirstResult(page * size);
        q.setMaxResults(size);
		
		if(isHavingCityName) {
			q.setParameter("name", cityName);
		}
		
		List<Weather> weathers = (List<Weather>) q.getResultList();
		return weathers;
	}
	
}
