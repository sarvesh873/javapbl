import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;


public class RMIServer extends UnicastRemoteObject implements RMIInterface {

    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/students";
    String username = "root"; 
        String password = "Mysql@873";
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs;
    PreparedStatement prst;
    ResultSetMetaData rsmd;

    public RMIServer() throws RemoteException {

    }

    public String addContact(String contactName, String phoneNo) throws RemoteException {
        String phoneNumber = "", val = "";
        try {
        
        
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DB_URL, username, password);
            System.out.println("Connection Established successfully");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM contact WHERE phoneno = '" + phoneNo + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                phoneNumber = rs.getString("phoneno");
            }

            if (phoneNo.intern().equals(phoneNumber)) {
                val = "0"; //Duplicate
            } else if (!phoneNo.intern().equals(phoneNumber)) {
                stmt = conn.createStatement();
                String sql1 = "INSERT INTO contact(contactname, phoneno) VALUES('" + contactName + "','" + phoneNo + "')";
                stmt.executeUpdate(sql1);
                val = "1"; //Successfully Registered
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return val;
    }

    public ArrayList searchContact(String contactName ) throws RemoteException {
        ArrayList array = new ArrayList();
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            // String sql = "SELECT *FROM contact WHERE phoneno ='" + phoneNo + "'";
            String sql = "SELECT *FROM contact WHERE contactname ='" + contactName + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                for (int i = 0; i < 2; i++) {
                    array.add(rs.getString(i + 1));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return array;
    }

    public ArrayList viewAllContacts(int i) throws RemoteException {
        ArrayList array = new ArrayList();
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "SELECT contactname, phoneno FROM contact ORDER BY contactname DESC";
            stmt.execute(sql);
            rs = stmt.getResultSet();
            //rsmd = rs.getMetaData();
            while (rs.next()) {
                array.add(rs.getObject(i + 1));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return array;
    }

    public String updateContact(String contactName, String phoneNo) throws RemoteException {
        String val = "", CPHONE = "";
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "SELECT *FROM contact WHERE contactname = '" + contactName + "'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                CPHONE = rs.getString("contactName");
            }
            // System.out.println(CPHONE);
            if (!contactName.intern().equals(CPHONE)) {
                val = "0";
            } else if (contactName.intern().equals(CPHONE)) {
                prst = conn.prepareStatement("UPDATE contact SET phoneno = '" +  phoneNo + "' WHERE contactname = '" +  contactName+ "'");
                // System.out.println(prst);
                prst.executeUpdate();
                val = "1";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return val;
    }

    public String deleteContact(String contactName) throws RemoteException {
        String val = "", CPHONE = "";
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "SELECT *FROM contact WHERE contactname = '" + contactName + "'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                CPHONE = rs.getString("contactName");
            }
            if (!contactName.intern().equals(CPHONE)) {
                val = "0";
            }

            else if (contactName.intern().equals(CPHONE)) {
                stmt = conn.createStatement();
                String sql2 = "DELETE FROM contact WHERE contactname = '"+contactName+"'";
                stmt.executeUpdate(sql2);
                val = "1";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return val;
    }

    public static void main(String[] args)throws Exception {
        try {
            Registry r = LocateRegistry.createRegistry(8081);
            RMIServer rs = new RMIServer();
            r.rebind("x", rs);
            System.out.println("Server Ready!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
