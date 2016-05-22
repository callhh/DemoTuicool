package com.example.model.topic;

import com.google.gson.annotations.Expose;

public class Item {

    @Expose
    private Integer id;
    @Expose
    private String name;
    @Expose
    private String image;
    @Expose
    private Boolean followed;

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
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

}
