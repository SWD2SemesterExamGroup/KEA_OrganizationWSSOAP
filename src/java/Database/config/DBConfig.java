/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database.config;

import Database.Privileges.UserPrivileges;
import Database.UserModels.TeacherUserModel;
import Database.UserPrivilegesAbstract.DbUserAbstract;

/**
 *
 * @author lord_
 */
public class DBConfig {
    private DbUserAbstract user;

    public DBConfig(int privilegesValue) {
        user = setupUser(privilegesValue);
    }
    
    private DbUserAbstract setupUser(int privValue) {
        UserPrivileges priv = UserPrivileges.GUEST;
        DbUserAbstract value = null;
        for (UserPrivileges up: UserPrivileges.values())
            if (up.getPrivValue() == privValue)
                priv = up;
        if (priv == UserPrivileges.TEACHER)
            value = new TeacherUserModel();
        return value;
    }
    
    public TeacherUserModel getConfigModel() { 
        return (TeacherUserModel)this.user; 
    }
}