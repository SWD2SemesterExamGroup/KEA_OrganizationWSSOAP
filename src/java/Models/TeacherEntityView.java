package Models;

import Models.Container.Entities.BaseCModel;
import java.util.List;
import java.util.ArrayList;
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
@XmlAccessorType(XmlAccessType.FIELD)
public class TeacherEntityView implements Serializable {

    private int teacherID;
    private String teacherName;
    @XmlElementWrapper
    private List<BaseCModel> courses;
    public TeacherEntityView() {
        this.courses = new ArrayList<>();
    }
    public TeacherEntityView(int id, String name) {
        this.teacherID = id;
        this.teacherName = name;
        this.courses = new ArrayList<>();
    }
    
    public int getTeacherID() {
        return teacherID;
    }
    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }
    public String getTeacherName() {
        return teacherName;
    }
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    public List<BaseCModel> getCourses() {
        return courses;
    }
    public void setCourses(List<BaseCModel> courses) {
        this.courses = courses;
    }
    
// Refactor implementation new code
    public void addCourse(BaseCModel entity) {
        this.courses.add(entity);
    }
    public void add(BaseCModel course, BaseCModel entityClass) {
        course.setClass(entityClass);
        addCourse(course);
    }
    public void addClass(BaseCModel entity) {
        this.courses.add(entity);
    }
    
    @Override
    public String toString() {
        String value = "Teacher\nId\tName\n";
        
        value += "Courses and Classes related to Teacher\n";
        value += "Course ID\tCourse Name\tClass ID\tClass Name\n";
        for (BaseCModel course: courses)
            value += course + "\n";
        return value;
    }
}
