
package com.prabhu.codepath.nytimessearch.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Byline {

    @JsonProperty("person")
    private List<Person> person = new ArrayList<Person>();
    @JsonProperty("original")
    private String original;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The person
     */
    @JsonProperty("person")
    public List<Person> getPerson() {
        return person;
    }

    /**
     * 
     * @param person
     *     The person
     */
    @JsonProperty("person")
    public void setPerson(List<Person> person) {
        this.person = person;
    }

    /**
     * 
     * @return
     *     The original
     */
    @JsonProperty("original")
    public String getOriginal() {
        return original;
    }

    /**
     * 
     * @param original
     *     The original
     */
    @JsonProperty("original")
    public void setOriginal(String original) {
        this.original = original;
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
