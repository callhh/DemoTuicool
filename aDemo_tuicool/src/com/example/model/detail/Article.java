package com.example.model.detail;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Article {

    @Expose
    private String id;
    @Expose
    private String title;
    @Expose
    private String time;
    @SerializedName("feed_title")
    @Expose
    private String feedTitle;
    @Expose
    private String url;
    @Expose
    private String content;
    @Expose
    private List<Image> images = new ArrayList<Image>();
    @Expose
    private Integer cmt;
    @Expose
    private Integer lang;
    @Expose
    private String img;
    @Expose
    private List<Topic> topics = new ArrayList<Topic>();

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The time
     */
    public String getTime() {
        return time;
    }

    /**
     * 
     * @param time
     *     The time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 
     * @return
     *     The feedTitle
     */
    public String getFeedTitle() {
        return feedTitle;
    }

    /**
     * 
     * @param feedTitle
     *     The feed_title
     */
    public void setFeedTitle(String feedTitle) {
        this.feedTitle = feedTitle;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The content
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     * @return
     *     The images
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * 
     * @param images
     *     The images
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }

    /**
     * 
     * @return
     *     The cmt
     */
    public Integer getCmt() {
        return cmt;
    }

    /**
     * 
     * @param cmt
     *     The cmt
     */
    public void setCmt(Integer cmt) {
        this.cmt = cmt;
    }

    /**
     * 
     * @return
     *     The lang
     */
    public Integer getLang() {
        return lang;
    }

    /**
     * 
     * @param lang
     *     The lang
     */
    public void setLang(Integer lang) {
        this.lang = lang;
    }

    /**
     * 
     * @return
     *     The img
     */
    public String getImg() {
        return img;
    }

    /**
     * 
     * @param img
     *     The img
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * 
     * @return
     *     The topics
     */
    public List<Topic> getTopics() {
        return topics;
    }

    /**
     * 
     * @param topics
     *     The topics
     */
    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

}
