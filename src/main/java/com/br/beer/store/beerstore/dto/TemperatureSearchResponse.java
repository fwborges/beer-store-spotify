package com.br.beer.store.beerstore.dto;

public class TemperatureSearchResponse {

    private String beerStyle;

    private Playlist playlist;

    private TemperatureSearchResponse(Builder builder) {
        beerStyle = builder.beerStyle;
        playlist = builder.playlist;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public String getBeerStyle() {
        return beerStyle;
    }

    public void setBeerStyle(String beerStyle) {
        this.beerStyle = beerStyle;
    }

    public static class Builder {

        private String beerStyle;

        private Playlist playlist;

        public Builder(String beerStyle) {
            this.beerStyle = beerStyle;
        }

        public Builder withPlaylist(Playlist playlist) {

            this.playlist = playlist;
            return this;
        }

        public TemperatureSearchResponse build() {
            return new TemperatureSearchResponse(this);
        }
    }

}
