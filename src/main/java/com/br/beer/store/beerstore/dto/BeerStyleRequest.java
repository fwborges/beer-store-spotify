package com.br.beer.store.beerstore.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BeerStyleRequest {

    @NotEmpty(message = "Style is required")
    private String beerStyle;

    @NotNull(message = "Minimal Temperature is required")
    private Double minTemperature;

    @NotNull(message = "Max Temperature is required")
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
