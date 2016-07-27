
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
public class Multimedium {

    @JsonProperty("width")
    private Integer width;
    @JsonProperty("url")
    private String url;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("subtype")
    private String subtype;
    @JsonProperty("legacy")
    private Legacy legacy;
    @JsonProperty("type")
    private String type;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The width
     */
    @JsonProperty("width")
    public Integer getWidth() {
        return width;
    }

    /**
     * 
     * @param width
     *     The width
     */
    @JsonProperty("width")
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * 
     * @return
     *     The url
     */
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The height
     */
    @JsonProperty("height")
    public Integer getHeight() {
        return height;
    }

    /**
     * 
     * @param height
     *     The height
     */
    @JsonProperty("height")
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * 
     * @return
     *     The subtype
     */
    @JsonProperty("subtype")
    public String getSubtype() {
        return subtype;
    }

    /**
     * 
     * @param subtype
     *     The subtype
     */
    @JsonProperty("subtype")
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    /**
     * 
     * @return
     *     The legacy
     */
    @JsonProperty("legacy")
    public Legacy getLegacy() {
        return legacy;
    }

    /**
     * 
     * @param legacy
     *     The legacy
     */
    @JsonProperty("legacy")
    public void setLegacy(Legacy legacy) {
        this.legacy = legacy;
    }

    /**
     * 
     * @return
     *     The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
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
