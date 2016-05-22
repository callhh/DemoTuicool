package com.example.model;
import com.google.gson.annotations.Expose;

public class Cats {

    @Expose
    private Integer id;
    @Expose
    private String desc;
    @Expose
    private String seo;

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
     *     The desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 
     * @param desc
     *     The desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 
     * @return
     *     The seo
     */
    public String getSeo() {
        return seo;
    }

    /**
     * 
     * @param seo
     *     The seo
     */
    public void setSeo(String seo) {
        this.seo = seo;
    }

}
