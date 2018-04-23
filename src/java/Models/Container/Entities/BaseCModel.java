/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Container.Entities;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lord_
 */
@XmlRootElement
public class BaseCModel implements Serializable {
    
    private long ID;
    private String title;
    
    private BaseCModel courseClass;
    
    public BaseCModel() {
        this.title = "";
    }
    public BaseCModel(long ID, String title) {
        this.ID = ID;
        this.title = title;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCourseClass(BaseCModel courseClass) {
        this.courseClass = courseClass;
    }

    public long getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }
    
    public void setClass(BaseCModel entity) {
        this.courseClass = entity;
    }
    
    public BaseCModel getCourseClass() { return this.courseClass; }
    
    @Override
    public String toString() {
        return this.ID + "\t\t" + this.title + "\t" + 
                this.courseClass.getID() + "\t\t" + this.courseClass.getTitle();
    }
}
