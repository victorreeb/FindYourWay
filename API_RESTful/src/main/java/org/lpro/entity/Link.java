/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.entity;

/**
 *
 * @author remaki
 */
public class Link {
    
    
     private String href;
    private String rel;

    public Link() {
    }

    public Link(String rel, String uri) {
        this.rel = rel;
        this.href = uri;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
    
    
    
    
    
    
    
    
    
}
