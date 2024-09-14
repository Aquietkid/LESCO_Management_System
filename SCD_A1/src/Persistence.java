/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Ali
 */
public class Persistence {

    public void writeToFile(String fileName, String content) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(fileName);
            bw = new BufferedWriter(fw);
            bw.write(content);
            
        } catch (IOException ex) {

        } finally {
            try {
                bw.close();
                fw.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
