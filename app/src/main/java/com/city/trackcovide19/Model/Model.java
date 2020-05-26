package com.city.trackcovide19.Model;

public class Model {
    public String countryName;
    public String flag;
    public String totalCases;
    public String todayCases;
    public String totalDeath;
    public String recovered;
    public String critical;

    public Model(String countryName, String flag, String totalCases, String todayCases, String totalDeath, String recovered, String critical) {
        this.countryName = countryName;
        this.flag = flag;
        this.totalCases = totalCases;
        this.todayCases = todayCases;
        this.totalDeath = totalDeath;
        this.recovered = recovered;
        this.critical = critical;
    }

    public Model(String countryName,String flag) {
        this.countryName = countryName;
        this.flag=flag;
    }

    public Model(String countryName, String totalCases, String todayCases, String totalDeath, String recovered, String critical) {
        this.countryName = countryName;
        this.totalCases = totalCases;
        this.todayCases = todayCases;
        this.totalDeath = totalDeath;
        this.recovered = recovered;
        this.critical = critical;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(String totalCases) {
        this.totalCases = totalCases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getTotalDeath() {
        return totalDeath;
    }

    public void setTotalDeath(String totalDeath) {
        this.totalDeath = totalDeath;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }


}
