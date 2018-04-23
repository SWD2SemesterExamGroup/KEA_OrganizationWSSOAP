package Database.UserModels;

import Database.UserPrivilegesAbstract.DbUserAbstract;

/**
 *
 * @author lord_
 */
public class TeacherUserModel extends DbUserAbstract {

    public TeacherUserModel() {
        super();
        this.username = "teacher";
        this.password = "pw1234";
    }
}
