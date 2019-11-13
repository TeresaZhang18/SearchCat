package com.example.searchcat.model;

import java.util.List;

public class Cat {

    public String id;
    public String name;
    public String description;
    public String origin;
    public String temperament;
    public String life_span;
    public String dog_friendly;
    public String wikipedia_url;
    public CatWeight weight;

    public class CatWeight {
        public String imperial;
        public String metric;

        public String getImperial() {
            return imperial;
        }

        public String getMetric() {
            return metric;
        }
    }

    public Cat(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getOrigin() {
        return origin;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getLife_span() {
        return life_span;
    }

    public String getDog_friendly() {
        return dog_friendly;
    }

    public String getWikipedia_url() {
        return wikipedia_url;
    }

    public CatWeight getWeight() {
        return weight;
    }
}
