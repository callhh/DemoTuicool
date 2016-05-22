package com.example.model;
import java.io.Serializable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//关系对象映射 添加表名     ； 
//Item类 继承Model  ; 
//添加列名 @Column 可直接修改列名
@Table(name="article")
public class Article extends Model implements Serializable,Cloneable{

	private static final long serialVersionUID = 760583053473603611L;
	// 实现Cloneable接口  防止报错
	@Override
	public Article clone() 
	{
		try
		{
			return (Article) super.clone();
		} catch (CloneNotSupportedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Expose
    @Column(name="article_id")
    private String id;
    @Expose
    @Column
    private String title;
    @Expose
    @Column
    private String time;
    @Expose
    @Column
    private String rectime;
    @Expose
    @Column
    private long uts;
    @SerializedName("feed_title")
    @Expose
    private String feedTitle;
    @Expose
    @Column
    private String img;
    @Expose
    @Column
    private String abs;
    @Expose
    @Column
    private Integer cmt;
    @Expose
    @Column
    private Integer st;
    @Expose
    @Column
    private Integer go;

    /**
     * 
     * @return
     *     The id
     */
    public String getArticleId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setArticleId(String id) {
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
     *     The rectime
     */
    public String getRectime() {
        return rectime;
    }

    /**
     * 
     * @param rectime
     *     The rectime
     */
    public void setRectime(String rectime) {
        this.rectime = rectime;
    }

    /**
     * 
     * @return
     *     The uts
     */
    public long getUts() {
        return uts;
    }

    /**
     * 
     * @param uts
     *     The uts
     */
    public void setUts(Integer uts) {
        this.uts = uts;
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
     *     The abs
     */
    public String getAbs() {
        return abs;
    }

    /**
     * 
     * @param abs
     *     The abs
     */
    public void setAbs(String abs) {
        this.abs = abs;
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
     *     The st
     */
    public Integer getSt() {
        return st;
    }

    /**
     * 
     * @param st
     *     The st
     */
    public void setSt(Integer st) {
        this.st = st;
    }

    /**
     * 
     * @return
     *     The go
     */
    public Integer getGo() {
        return go;
    }

    /**
     * 
     * @param go
     *     The go
     */
    public void setGo(Integer go) {
        this.go = go;
    }

}
