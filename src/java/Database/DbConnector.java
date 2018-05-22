package Database;

import Database.config.DBConfig;
import Models.Container.Entities.BaseCModel;
import Models.TeacherEntityView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class DbConnector {
    private static DbConnector instance;
    private static DBConfig config;
    private Connection conn;
    // if needed with alot of functions remove and implement in new class
    private PreparedStatement stmtPre;
    //private Statement stmt;
    private ResultSet rs;
    
    public TeacherEntityView getCourseClassListBy(int teacherID) {
        TeacherEntityView value = null;
        // Field Setup
        BaseCModel course = null;
        BaseCModel cls = null;
        
        
        // Retrieve data from SQL
        try {
            String sql = "select * from v_getcourseclasslist where TeacherID = ?;";
            stmtPre = this.conn.prepareStatement(sql);
            stmtPre.setInt(1, teacherID);

            rs = stmtPre.executeQuery();
            
            // TODO: Refactor
            if (rs.next()){
                value = new TeacherEntityView(rs.getInt(1), rs.getString(2));
                
                if (rs.getString(4) != null) {
                    course = new BaseCModel(rs.getLong(3), rs.getString(4));
                }
                if (rs.getString(6) != null) {
                    cls = new BaseCModel(rs.getLong(5), rs.getString(6));
                }
                value.add(course, cls);
                // Get rest of list
                while (rs.next()) {
                    if (rs.getString(4) != null) {
                    course = new BaseCModel(rs.getLong(3), rs.getString(4));
                    }
                    if (rs.getString(6) != null) {
                        cls = new BaseCModel(rs.getLong(5), rs.getString(6));
                    }
                    value.add(course, cls);
                }
            }
        } catch (SQLException eSQL) {
            System.out.println("Get Course Class List SQL Exception");
            eSQL.printStackTrace();
        } finally {
            /*try {
                this.conn.close();
            } catch (SQLException eSQL) {
                System.out.println("Get Course Class List SQL Exception\n" +
                                   "Closing Connection Exception");
                eSQL.printStackTrace();
            } finally {
                System.out.println("Data Retrieved");
            }*/
        }
        
        // Transform List interfaces
        try {
            JAXBContext jaxbc = JAXBContext.newInstance(TeacherEntityView.class, BaseCModel.class);
            Marshaller marshaller = jaxbc.createMarshaller();
            
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(value, System.out);
        } catch (JAXBException eJAXB) {
            System.out.println("Marshaller Exception");
            eJAXB.printStackTrace();
        } finally {
            System.out.println("Marshaller done");
        }
        
        //System.out.println(value);
        return value;
    }
    
    
    /**
     * Remember to setup Instance by privileges value 0 Lowest, 1 - Highest
     * @return this as instance
     */
    public static DbConnector getInstance() {
        if (instance == null) {
            instance = new DbConnector();
        }
        return instance;
    }
    
    public static void setupInstanceBy(int privilegesValue) {
        config = new DBConfig(privilegesValue);
    }
    
    private DbConnector() {
        
        try {
            // Adding the class forname for library reference
            //Class.forName("com.mysql.jdbc.driver");
            conn = DriverManager.getConnection(
                    config.getConfigModel().getUrl(),
                    config.getConfigModel().getUsername(),
                    config.getConfigModel().getPassword());
        } /*catch (ClassNotFoundException eCNF) {
            eCNF.printStackTrace();
        }*/ catch (SQLException eSQL) {
            eSQL.printStackTrace();
            System.out.println("Can't connect to Database");
        }
    }
}
