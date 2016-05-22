package com.example.model.detail;

import com.google.gson.annotations.Expose;

public class HotDetail {

    @Expose
    private Boolean success;
    @Expose
    private Article article;
    @Expose
    private String like;
    @Expose
    private Site site;

    /**
     * 
     * @return
     *     The success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * 
     * @param success
     *     The success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * 
     * @return
     *     The article
     */
    public Article getArticle() {
        return article;
    }

    /**
     * 
     * @param article
     *     The article
     */
    public void setArticle(Article article) {
        this.article = article;
    }

    /**
     * 
     * @return
     *     The like
     */
    public String getLike() {
        return like;
    }

    /**
     * 
     * @param like
     *     The like
     */
    public void setLike(String like) {
        this.like = like;
    }

    /**
     * 
     * @return
     *     The site
     */
    public Site getSite() {
        return site;
    }

    /**
     * 
     * @param site
     *     The site
     */
    public void setSite(Site site) {
        this.site = site;
    }

}
