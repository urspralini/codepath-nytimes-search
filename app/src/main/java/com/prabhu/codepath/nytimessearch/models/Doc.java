
package com.prabhu.codepath.nytimessearch.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Doc {

    @JsonProperty("web_url")
    private String webUrl;
    @JsonProperty("snippet")
    private String snippet;
    @JsonProperty("lead_paragraph")
    private String leadParagraph;
    @JsonProperty("abstract")
    private String _abstract;
    @JsonProperty("print_page")
    private String printPage;
    @JsonProperty("blog")
    private List<Object> blog = new ArrayList<Object>();
    @JsonProperty("source")
    private String source;
    @JsonProperty("multimedia")
    private List<Multimedia> multimedia = new ArrayList<Multimedia>();
    @JsonProperty("headline")
    private Headline headline;
    @JsonProperty("keywords")
    private List<Keyword> keywords = new ArrayList<Keyword>();
    @JsonProperty("pub_date")
    private String pubDate;
    @JsonProperty("document_type")
    private String documentType;
    @JsonProperty("news_desk")
    private String newsDesk;
    @JsonProperty("section_name")
    private String sectionName;
    @JsonProperty("subsection_name")
    private String subsectionName;
    @JsonIgnore
    private Byline byline;
    @JsonProperty("type_of_material")
    private String typeOfMaterial;
    @JsonProperty("_id")
    private String id;
    @JsonProperty("word_count")
    private String wordCount;
    @JsonProperty("slideshow_credits")
    private Object slideshowCredits;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The webUrl
     */
    @JsonProperty("web_url")
    public String getWebUrl() {
        return webUrl;
    }

    /**
     * 
     * @param webUrl
     *     The web_url
     */
    @JsonProperty("web_url")
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    /**
     * 
     * @return
     *     The snippet
     */
    @JsonProperty("snippet")
    public String getSnippet() {
        return snippet;
    }

    /**
     * 
     * @param snippet
     *     The snippet
     */
    @JsonProperty("snippet")
    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    /**
     * 
     * @return
     *     The leadParagraph
     */
    @JsonProperty("lead_paragraph")
    public String getLeadParagraph() {
        return leadParagraph;
    }

    /**
     * 
     * @param leadParagraph
     *     The lead_paragraph
     */
    @JsonProperty("lead_paragraph")
    public void setLeadParagraph(String leadParagraph) {
        this.leadParagraph = leadParagraph;
    }

    /**
     * 
     * @return
     *     The _abstract
     */
    @JsonProperty("abstract")
    public String getAbstract() {
        return _abstract;
    }

    /**
     * 
     * @param _abstract
     *     The abstract
     */
    @JsonProperty("abstract")
    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    /**
     * 
     * @return
     *     The printPage
     */
    @JsonProperty("print_page")
    public String getPrintPage() {
        return printPage;
    }

    /**
     * 
     * @param printPage
     *     The print_page
     */
    @JsonProperty("print_page")
    public void setPrintPage(String printPage) {
        this.printPage = printPage;
    }

    /**
     * 
     * @return
     *     The blog
     */
    @JsonProperty("blog")
    public List<Object> getBlog() {
        return blog;
    }

    /**
     * 
     * @param blog
     *     The blog
     */
    @JsonProperty("blog")
    public void setBlog(List<Object> blog) {
        this.blog = blog;
    }

    /**
     * 
     * @return
     *     The source
     */
    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    /**
     * 
     * @param source
     *     The source
     */
    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 
     * @return
     *     The multimedia
     */
    @JsonProperty("multimedia")
    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    /**
     * 
     * @param multimedia
     *     The multimedia
     */
    @JsonProperty("multimedia")
    public void setMultimedia(List<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }

    /**
     * 
     * @return
     *     The headline
     */
    @JsonProperty("headline")
    public Headline getHeadline() {
        return headline;
    }

    /**
     * 
     * @param headline
     *     The headline
     */
    @JsonProperty("headline")
    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    /**
     * 
     * @return
     *     The keywords
     */
    @JsonProperty("keywords")
    public List<Keyword> getKeywords() {
        return keywords;
    }

    /**
     * 
     * @param keywords
     *     The keywords
     */
    @JsonProperty("keywords")
    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    /**
     * 
     * @return
     *     The pubDate
     */
    @JsonProperty("pub_date")
    public String getPubDate() {
        return pubDate;
    }

    /**
     * 
     * @param pubDate
     *     The pub_date
     */
    @JsonProperty("pub_date")
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    /**
     * 
     * @return
     *     The documentType
     */
    @JsonProperty("document_type")
    public String getDocumentType() {
        return documentType;
    }

    /**
     * 
     * @param documentType
     *     The document_type
     */
    @JsonProperty("document_type")
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    /**
     * 
     * @return
     *     The newsDesk
     */
    @JsonProperty("news_desk")
    public String getNewsDesk() {
        return newsDesk;
    }

    /**
     * 
     * @param newsDesk
     *     The news_desk
     */
    @JsonProperty("news_desk")
    public void setNewsDesk(String newsDesk) {
        this.newsDesk = newsDesk;
    }

    /**
     * 
     * @return
     *     The sectionName
     */
    @JsonProperty("section_name")
    public String getSectionName() {
        return sectionName;
    }

    /**
     * 
     * @param sectionName
     *     The section_name
     */
    @JsonProperty("section_name")
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    /**
     * 
     * @return
     *     The subsectionName
     */
    @JsonProperty("subsection_name")
    public String getSubsectionName() {
        return subsectionName;
    }

    /**
     * 
     * @param subsectionName
     *     The subsection_name
     */
    @JsonProperty("subsection_name")
    public void setSubsectionName(String subsectionName) {
        this.subsectionName = subsectionName;
    }

    /**
     * 
     * @return
     *     The byline
     */
    public Byline getByline() {
        return byline;
    }

    /**
     * 
     * @param byline
     *     The byline
     */
    public void setByline(Byline byline) {
        this.byline = byline;
    }

    /**
     * 
     * @return
     *     The typeOfMaterial
     */
    @JsonProperty("type_of_material")
    public String getTypeOfMaterial() {
        return typeOfMaterial;
    }

    /**
     * 
     * @param typeOfMaterial
     *     The type_of_material
     */
    @JsonProperty("type_of_material")
    public void setTypeOfMaterial(String typeOfMaterial) {
        this.typeOfMaterial = typeOfMaterial;
    }

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The _id
     */
    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The wordCount
     */
    @JsonProperty("word_count")
    public String getWordCount() {
        return wordCount;
    }

    /**
     * 
     * @param wordCount
     *     The word_count
     */
    @JsonProperty("word_count")
    public void setWordCount(String wordCount) {
        this.wordCount = wordCount;
    }

    /**
     * 
     * @return
     *     The slideshowCredits
     */
    @JsonProperty("slideshow_credits")
    public Object getSlideshowCredits() {
        return slideshowCredits;
    }

    /**
     * 
     * @param slideshowCredits
     *     The slideshow_credits
     */
    @JsonProperty("slideshow_credits")
    public void setSlideshowCredits(Object slideshowCredits) {
        this.slideshowCredits = slideshowCredits;
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
