package com.oddle.app.weather.model.internalmapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.oddle.app.weather.model.externalmapper.EWeatherCondition;
import com.oddle.app.weather.model.externalmapper.EWeatherSummary;
import com.oddle.app.weather.util.DateUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "weather")
@JsonInclude(value = Include.NON_NULL)
public class IWeatherSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "City", referencedColumnName = "Name")
    private City city;

    @Column(name = "Date")
    private String date;

    @OneToMany(mappedBy = "weather", cascade = CascadeType.ALL)
    private List<IWeatherCondition> conditions;

    @OneToOne(mappedBy = "weather", cascade = CascadeType.ALL)
    private IWeatherMain main;

    @Column(name = "Visibility")
    private BigDecimal visibility;

    @OneToOne(mappedBy = "weather", cascade = CascadeType.ALL)
    private IWeatherWind wind;

    @Column(name = "Sunrise")
    private Long sunrise;

    @Column(name = "Sunset")
    private Long sunset;

    // shift in seconds from UTC
    @Column(name = "Timezone")
    private Long timezone;

    public IWeatherSummary() {

    }

    public IWeatherSummary(EWeatherSummary weatherSummary) {
        // START: convert to internal weather object design
        City ct = new City();
        ct.setName(weatherSummary.getName());
        ct.setCountry(weatherSummary.getSys().getCountry());
        ct.setLongitude(weatherSummary.getCoord().getLon());
        ct.setLatitude(weatherSummary.getCoord().getLat());

        this.setCity(ct);
        this.setDate(DateUtil.convertEpochToDate(weatherSummary.getDt()));

        List<IWeatherCondition> conditions = new ArrayList<IWeatherCondition>();
        for (EWeatherCondition wthr : weatherSummary.getWeather()) {
            IWeatherCondition wc = new IWeatherCondition();
            wc.setCode(wthr.getId());
            wc.setMain(wthr.getMain());
            wc.setDescription(wthr.getDescription());

            conditions.add(wc);
        }

        this.setConditions(conditions);

        IWeatherMain wm = new IWeatherMain();
        wm.setTemperature(weatherSummary.getMain().getTemp());
        wm.setFeelsLike(weatherSummary.getMain().getFeels_like());
        wm.setTempMin(weatherSummary.getMain().getTemp_min());
        wm.setTempMax(weatherSummary.getMain().getTemp_max());
        wm.setPressure(weatherSummary.getMain().getPressure());
        wm.setHumidity(weatherSummary.getMain().getHumidity());

        this.setMain(wm);
        this.setVisibility(weatherSummary.getVisibility());

        IWeatherWind ww = new IWeatherWind();
        ww.setSpeed(weatherSummary.getWind().getSpeed());
        ww.setDirection(weatherSummary.getWind().getDeg());

        this.setWind(ww);
        this.setSunrise(weatherSummary.getSys().getSunrise());
        this.setSunset(weatherSummary.getSys().getSunset());
        this.setTimezone(weatherSummary.getTimezone());
        // END: convert to internal weather object design
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<IWeatherCondition> getConditions() {
        return conditions;
    }

    public void setConditions(List<IWeatherCondition> conditions) {
        for (IWeatherCondition wc : conditions) {
            wc.setWeather(this);
        }

        this.conditions = conditions;
    }

    public IWeatherMain getMain() {
        return main;
    }

    public void setMain(IWeatherMain main) {
        main.setWeather(this);
        this.main = main;
    }

    public BigDecimal getVisibility() {
        return visibility;
    }

    public void setVisibility(BigDecimal visibility) {
        this.visibility = visibility;
    }

    public IWeatherWind getWind() {
        return wind;
    }

    public void setWind(IWeatherWind wind) {
        wind.setWeather(this);
        this.wind = wind;
    }

    public Long getSunrise() {
        return sunrise;
    }

    public void setSunrise(Long sunrise) {
        this.sunrise = sunrise;
    }

    public Long getSunset() {
        return sunset;
    }

    public void setSunset(Long sunset) {
        this.sunset = sunset;
    }

    public Long getTimezone() {
        return timezone;
    }

    public void setTimezone(Long timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        return "IWeatherSummary [id=" + id + ", city=" + city + ", date=" + date + ", conditions=" + conditions
                + ", main=" + main + ", visibility=" + visibility + ", wind=" + wind + ", sunrise=" + sunrise
                + ", sunset=" + sunset + ", timezone=" + timezone + "]";
    }

    /* only generated on error occasion */

    @Transient
    private Integer code;

    @Transient
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public IWeatherSummary(int cod, String message) {
        this.code = cod;
        this.message = message;
    }

    /* only generated on error occasion */
}
