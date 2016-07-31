
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
public class Legacy {

    @JsonProperty("thumbnailheight")
    private String thumbnailheight;
    @JsonProperty("thumbnail")
    private String thumbnail;
    @JsonProperty("thumbnailwidth")
    private String thumbnailwidth;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The thumbnailheight
     */
    @JsonProperty("thumbnailheight")
    public String getThumbnailheight() {
        return thumbnailheight;
    }

    /**
     * 
     * @param thumbnailheight
     *     The thumbnailheight
     */
    @JsonProperty("thumbnailheight")
    public void setThumbnailheight(String thumbnailheight) {
        this.thumbnailheight = thumbnailheight;
    }

    /**
     * 
     * @return
     *     The thumbnail
     */
    @JsonProperty("thumbnail")
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * 
     * @param thumbnail
     *     The thumbnail
     */
    @JsonProperty("thumbnail")
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * 
     * @return
     *     The thumbnailwidth
     */
    @JsonProperty("thumbnailwidth")
    public String getThumbnailwidth() {
        return thumbnailwidth;
    }

    /**
     * 
     * @param thumbnailwidth
     *     The thumbnailwidth
     */
    @JsonProperty("thumbnailwidth")
    public void setThumbnailwidth(String thumbnailwidth) {
        this.thumbnailwidth = thumbnailwidth;
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
