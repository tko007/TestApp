package at.tko.collector.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Created by tko007 on 2017. 02. 12..
 */
public class YamlFile {

    @JsonProperty
    private String driver;

    @JsonProperty
    private float limit;

    @JsonProperty
    private Map<String, List<String>> parties;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Map<String, List<String>> getParties() {
        return parties;
    }

    public void setParties(Map<String, List<String>> parties) {
        this.parties = parties;
    }

    public float getLimit() {
        return limit;
    }

    public void setLimit(float limit) {
        this.limit = limit;
    }
}
