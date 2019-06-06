package com.springboot.enty;

import java.io.Serializable;

/**
 * <p> 
 *  b
 * </p> 
 * 
 * @author 刘彦军 2019-04-25 09:45:26 761
 */
public class B implements Serializable {
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