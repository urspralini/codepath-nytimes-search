
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
public class Meta {

    @JsonProperty("hits")
    private Integer hits;
    @JsonProperty("time")
    private Integer time;
    @JsonProperty("offset")
    private Integer offset;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The hits
     */
    @JsonProperty("hits")
    public Integer getHits() {
        return hits;
    }

    /**
     * 
     * @param hits
     *     The hits
     */
    @JsonProperty("hits")
    public void setHits(Integer hits) {
        this.hits = hits;
    }

    /**
     * 
     * @return
     *     The time
     */
    @JsonProperty("time")
    public Integer getTime() {
        return time;
    }

    /**
     * 
     * @param time
     *     The time
     */
    @JsonProperty("time")
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     * 
     * @return
     *     The offset
     */
    @JsonProperty("offset")
    public Integer getOffset() {
        return offset;
    }

    /**
     * 
     * @param offset
     *     The offset
     */
    @JsonProperty("offset")
    public void setOffset(Integer offset) {
        this.offset = offset;
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
