import java.io.*;
import java.util.ArrayList;

public class EmployeePersistence {
    public static void writeToFile(String _username, String _password) {
        ArrayList<Employee> employees = new ArrayList<>();
        final String fileName = "./src/EmployeesData.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String username = data[0];
                String password;
                if (username.equals(_username)) {
                    password = _password;
                } else password = data[1];

                Employee employee = new Employee(username, password);
                employees.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Employee e : employees) {
                bw.write(e.toFileString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
