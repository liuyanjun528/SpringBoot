package com.springboot.enty;

import java.io.Serializable;

/**
 * <p> 
 *  a
 * </p> 
 * 
 * @author 95303 2018-11-01 11:00:29 155
 */
public class A implements Serializable {
    /**
     * id 描述:
     */
    private Integer id;

    /**
     * name 描述:
     */
    private String name;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = (name == null || name.trim().isEmpty() ) ? null : name.trim();
    }
}