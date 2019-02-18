package com.br.beer.store.beerstore.dto;

import javax.validation.constraints.NotNull;

public class TemperatureSearchRequest {

    @NotNull(message = "Temperature is required")
    private Double temperature;

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}
