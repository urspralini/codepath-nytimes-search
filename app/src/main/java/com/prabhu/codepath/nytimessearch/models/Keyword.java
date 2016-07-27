
package com.prabhu.codepath.nytimessearch.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Keyword {

    @JsonProperty("rank")
    private String rank;
    @JsonProperty("is_major")
    private String isMajor;
    @JsonProperty("name")
    private String name;
    @JsonProperty("value")
    private String value;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The rank
     */
    @JsonProperty("rank")
    public String getRank() {
        return rank;
    }

    /**
     * 
     * @param rank
     *     The rank
     */
    @JsonProperty("rank")
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * 
     * @return
     *     The isMajor
     */
    @JsonProperty("is_major")
    public String getIsMajor() {
        return isMajor;
    }

    /**
     * 
     * @param isMajor
     *     The is_major
     */
    @JsonProperty("is_major")
    public void setIsMajor(String isMajor) {
        this.isMajor = isMajor;
    }

    /**
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The value
     */
    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
