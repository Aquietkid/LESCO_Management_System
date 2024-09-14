import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LoginMenu {

    public int login(String username, String password) {

        Boolean loginStatus;

        try {
            loginStatus = this.compareEmployeeCredentials(username, password);
            if(loginStatus) {
                return 1;
            }
            else {
                loginStatus = this.compareUserCredentials(username, password);
                if(loginStatus) {
                    return 2;
                }
                return 0;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            return 0;
        }


    }

    private Boolean compareEmployeeCredentials(String _username, String _password) throws FileNotFoundException {
        String filePath = "EmployeesData.txt";

        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                String password = parts[1];
                if (username.equals(_username) && password.equals(_password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    private Boolean compareUserCredentials(String _username, String _password) throws FileNotFoundException {
        String filePath = "UserData.txt";

        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                String password = parts[1];
                if (username.equals(_username) && password.equals(_password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

}
