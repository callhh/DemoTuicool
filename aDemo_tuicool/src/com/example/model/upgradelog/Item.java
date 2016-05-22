package com.example.model.upgradelog;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

// 关系对象映射 添加表名     ； 
// Item类 继承Model  ; 
// 添加列名 @Column 可直接修改列名 如：@Column(name="version_number")
@Table(name="upgradelog")
public class Item extends Model {

    @Expose
    @Column
    private Integer version;
    @Expose
    @Column(name="version_number")
    private String name;
    @Expose
    @Column
    private String date;
    @Expose
    @Column
    private String log;

    /**
     * 
     * @return
     *     The version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     *     The version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getVersionName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setVersionName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The date
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 
     * @return
     *     The log
     */
    public String getLog() {
        return log;
    }

    /**
     * 
     * @param log
     *     The log
     */
    public void setLog(String log) {
        this.log = log;
    }

}
