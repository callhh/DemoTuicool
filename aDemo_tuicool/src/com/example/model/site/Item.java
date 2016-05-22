package com.example.model.site;

import com.google.gson.annotations.Expose;

public class Item {

    @Expose
    private String id;
    @Expose
    private Boolean followed;
    @Expose
    private String name;
    @Expose
    private String image;
    @Expose
    private Integer lang;
    @Expose
    private String cover;

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
     *     The followed
     */
    public Boolean getFollowed() {
        return followed;
    }

    /**
     * 
     * @param followed
     *     The followed
     */
    public void setFollowed(Boolean followed) {
        this.followed = followed;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The image
     */
    public String getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    public void setImage(String image) {
        this.image = image;
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
     *     The cover
     */
    public String getCover() {
        return cover;
    }

    /**
     * 
     * @param cover
     *     The cover
     */
    public void setCover(String cover) {
        this.cover = cover;
    }

}
