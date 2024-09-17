import java.io.*;

public class LoginMenu {

    public int login(String username, String password) {

        Boolean loginStatus;

        try {
            loginStatus = this.compareEmployeeCredentials(username, password);
//            System.out.println(loginStatus);
            if (loginStatus) { //Log in as an employee
//                System.out.println("login status 1");
                return 1;
            } else {
                loginStatus = this.compareUserCredentials(username, password);
                if (loginStatus) { //Log in as a customer
//                    System.out.println("login status 2");
                    return 2;
                }
//                System.out.println("login status 0");
                return 0;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Boolean compareEmployeeCredentials(String _username, String _password) throws FileNotFoundException {
        String filePath = "./src/EmployeesData.txt";

        Boolean b = readFile(_username, _password, filePath);
//        System.out.println(b);
        return b;
    }

    private Boolean compareUserCredentials(String _username, String _password) throws FileNotFoundException {
        String filePath = "./src/CustomersData.txt";

        Boolean b = readFile(_username, _password, filePath);
//        System.out.println(b);
        return b;
    }

    private Boolean readFile(String _username, String _password, String filePath) {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(new File(filePath));
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                String password = parts[1];
                if (username.equals(_username) && password.equals(_password)) {
//                    System.out.println("Found!");
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
