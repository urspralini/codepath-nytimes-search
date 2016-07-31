
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
public class Headline {

    @JsonProperty("main")
    private String main;
    @JsonProperty("content_kicker")
    private String contentKicker;
    @JsonProperty("kicker")
    private String kicker;
    @JsonProperty("print_headline")
    private String printHeadline;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The main
     */
    @JsonProperty("main")
    public String getMain() {
        return main;
    }

    /**
     * 
     * @param main
     *     The main
     */
    @JsonProperty("main")
    public void setMain(String main) {
        this.main = main;
    }

    /**
     * 
     * @return
     *     The contentKicker
     */
    @JsonProperty("content_kicker")
    public String getContentKicker() {
        return contentKicker;
    }

    /**
     * 
     * @param contentKicker
     *     The content_kicker
     */
    @JsonProperty("content_kicker")
    public void setContentKicker(String contentKicker) {
        this.contentKicker = contentKicker;
    }

    /**
     * 
     * @return
     *     The kicker
     */
    @JsonProperty("kicker")
    public String getKicker() {
        return kicker;
    }

    /**
     * 
     * @param kicker
     *     The kicker
     */
    @JsonProperty("kicker")
    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    /**
     * 
     * @return
     *     The printHeadline
     */
    @JsonProperty("print_headline")
    public String getPrintHeadline() {
        return printHeadline;
    }

    /**
     * 
     * @param printHeadline
     *     The print_headline
     */
    @JsonProperty("print_headline")
    public void setPrintHeadline(String printHeadline) {
        this.printHeadline = printHeadline;
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
