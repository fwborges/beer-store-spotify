package com.br.beer.store.beerstore.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BeerStyleRequest {

    @NotEmpty(message = "")
    private String beerStyle;

    @NotNull(message = "")
    private Double minTemperature;

    @NotNull(message = "")
    private Double maxTemperature;

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
}
