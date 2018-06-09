/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database.UserPrivilegesAbstract;

import Database.UserModels.TeacherUserModel;

/**
 *
 * @author lord_
 */
public abstract class DbUserAbstract {
    //protected final String url = "jdbc:mysql://localhost:3306/KEA_Organization_DB";
    protected final String url = "jdbc:mysql://mysql46.unoeuro.com:3306/helbojensen_dk_db2";
    protected String username, password;

    public String getUrl() {
        return url;
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
}
