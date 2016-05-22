package com.example.model
;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TuikuHot {

    @Expose
    private Boolean success;
    @SerializedName("has_next")
    @Expose
    private Boolean hasNext;
    @Expose
    private List<Article> articles = new ArrayList<Article>();
    @Expose
    private Integer lang;
    @Expose
    private Integer pn;
    @Expose
    private Cats cats;
    @Expose
    private Integer cid;

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
     *     The hasNext
     */
    public Boolean getHasNext() {
        return hasNext;
    }

    /**
     * 
     * @param hasNext
     *     The has_next
     */
    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    /**
     * 
     * @return
     *     The articles
     */
    public List<Article> getArticles() {
        return articles;
    }

    /**
     * 
     * @param articles
     *     The articles
     */
    public void setArticles(List<Article> articles) {
        this.articles = articles;
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
     *     The pn
     */
    public Integer getPn() {
        return pn;
    }

    /**
     * 
     * @param pn
     *     The pn
     */
    public void setPn(Integer pn) {
        this.pn = pn;
    }

    /**
     * 
     * @return
     *     The cats
     */
    public Cats getCats() {
        return cats;
    }

    /**
     * 
     * @param cats
     *     The cats
     */
    public void setCats(Cats cats) {
        this.cats = cats;
    }

    /**
     * 
     * @return
     *     The cid
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * 
     * @param cid
     *     The cid
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

}
