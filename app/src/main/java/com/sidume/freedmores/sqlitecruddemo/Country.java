package com.sidume.freedmores.sqlitecruddemo;

import java.util.Currency;

public class Country {
    int id;
    private String country_name;
    private String country_continent;
    private String currency;
    private double population;

    public Country() {
    }

    public Country(String country_name,String country_continent, long population, String currency) {
        this.country_name = country_name;
        this.country_continent = country_continent;
        this.currency = currency;
        this.population = population;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getCountry_continent() {
        return country_continent;
    }

    public void setCountry_continent(String country_continent) {
        this.country_continent = country_continent;
    }

    public String getCurrency() {
        return currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(Double population) {
        this.population = population;
    }
}
