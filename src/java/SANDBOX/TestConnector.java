/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SANDBOX;

import Database.DbConnector;
import Models.Container.Entities.BaseCModel;
import Models.TeacherEntityView;
import java.util.List;
/**
 *
 * @author lord_
 */
public class TestConnector {
    public static void main(String[] args) {
        DbConnector.setupInstanceBy(4);
        TeacherEntityView teacher = DbConnector.getInstance().getCourseClassListBy(1);
        //displayTeachers(teacher);
        //System.out.println(teacher);
        
    }
    
    private static void displayTeachers(TeacherEntityView entity) {
        System.out.println("Teacher ID \tTeacher Name");
        System.out.println( entity.getTeacherID() + "\t" + entity.getTeacherName());
        
        System.out.println("Course ID \tCourse Name \tClass ID \tClass Name");
        for (BaseCModel course: entity.getCourses())
            System.out.println(course.getID() + "\t" + course.getTitle() + "\t" + course.getCourseClass().getID()  + "\t" + course.getCourseClass().getTitle());
    }
}
