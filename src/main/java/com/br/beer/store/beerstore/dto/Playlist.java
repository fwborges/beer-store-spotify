package com.br.beer.store.beerstore.dto;

import java.util.List;

public class Playlist {

    private String name;

    private List<Track> tracks;

    private Playlist(Builder builder) {
        this.name = builder.name;
        this.tracks = builder.tracks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public static class Builder {

        private String name;

        private List<Track> tracks;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withTracks(List<Track> tracks) {
            this.tracks = tracks;
            return this;
        }

        public Playlist build() {
            return new Playlist(this);
        }

    }
}
