import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface RMIInterface extends Remote {

    public String addContact(String contactName, String phoneNo) throws RemoteException;

    public ArrayList searchContact(String phoneNo) throws RemoteException;

    public ArrayList viewAllContacts(int i) throws RemoteException;

    public String updateContact(String contactName, String phoneNo) throws RemoteException;

    public String deleteContact(String phoneNo) throws RemoteException;

}
