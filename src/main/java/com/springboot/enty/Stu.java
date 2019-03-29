package com.springboot.enty;

import java.io.Serializable;

/**
 * <p> 
 *  stu
 * </p> 
 * 
 * @author 95303 2018-09-06 21:46:46 907
 */
public class Stu implements Serializable {
    /**
     * id 描述:
     */
    private Integer id;

    /**
     * stuname 描述:
     */
    private String stuname;

    /**
     * stusex 描述:
     */
    private String stusex;

    /**
     * stuage 描述:
     */
    private String stuage;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = (stuname == null || stuname.trim().isEmpty() ) ? null : stuname.trim();
    }

    public String getStusex() {
        return stusex;
    }

    public void setStusex(String stusex) {
        this.stusex = (stusex == null || stusex.trim().isEmpty() ) ? null : stusex.trim();
    }

    public String getStuage() {
        return stuage;
    }

    public void setStuage(String stuage) {
        this.stuage = (stuage == null || stuage.trim().isEmpty() ) ? null : stuage.trim();
    }

	@Override
	public String toString() {
		return "Stu [id=" + id + ", stuname=" + stuname + ", stusex=" + stusex + ", stuage=" + stuage + "]";
	}
    
}