/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.kea.organization;

import Database.DbConnector;
import Database.Privileges.UserPrivileges;
import Models.TeacherEntityView;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author lord_
 */
@WebService(serviceName = "Teachers-ws")
public class TeacherService {

    @WebMethod(operationName = "getCourseClassesString")
    public String getCourseClassesViewStringBy(@WebParam(name = "teacherID") int techerID) {
        return "teacher class course list";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "CourseClassesDataBy")
    public TeacherEntityView getCourseClassesViewModelBy(@WebParam(name = "teacherID") int teacherID) {
        
        
        return DbConnector.getInstance().getCourseClassListBy(teacherID);
    }

    // Constructor
    public TeacherService() {
        initialize();
    }
    
    // DbConnector Setup
    private void initialize() {
        DbConnector.setupInstanceBy(UserPrivileges.TEACHER.getPrivValue());
    }
}
