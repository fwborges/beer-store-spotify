package com.br.beer.store.beerstore.entity.beer;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "beers")
public class BeerStyle {

    @Transient
    public static final String BEERS_SEQUENCE = "beers_sequence";

    @Id
    private Long id;

    private String beerStyle;

    private Double minTemperature;

    private Double maxTemperature;

    private Double averageTemperature;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(Double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public String getBeerStyle() {
        return beerStyle;
    }

    public void setBeerStyle(String beerStyle) {
        this.beerStyle = beerStyle;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public void calculateAvarageTemperature() {

        this.averageTemperature = (this.minTemperature + this.maxTemperature) / 2;
    }

}
