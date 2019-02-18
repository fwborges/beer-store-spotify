package com.br.beer.store.beerstore.dto;

public class Track {

    private String name;

    private String artist;

    private String link;

    private Track(Builder builder) {
        this.name = builder.name;
        this.artist = builder.artist;
        this.link = builder.link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public static class Builder {

        private String name;

        private String artist;

        private String link;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withArtist(String artist) {
            this.artist = artist;
            return this;
        }

        public Builder withLink(String link) {
            this.link = link;
            return this;
        }

        public Track build() {
            return new Track(this);
        }

    }
}
