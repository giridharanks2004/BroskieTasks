package Task_4_files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;


public class Task4 {
    public static void main(String[] args) {
        File InputFile = new File("Task_4_files\\products.csv");
        File OutputFile = new File("Task_4_files\\filteredproducts.csv");
        try {
            BufferedReader inputReader = new BufferedReader(new FileReader(InputFile));
            FileWriter outputWriter = new FileWriter(OutputFile);
            String line = "";
            int count = 0;
            while((line = inputReader.readLine())!=null){
                if(count == 0){
                    count++;
                    continue;
                }
                String [] record = line.split(",");
                String name = record[0];
                int price = Integer.parseInt(record[1]);
                if(price>1000){
                    outputWriter.write(name+","+price+"\n");
                }
                System.out.println("products are written successfully");
            }
            outputWriter.close();
            inputReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
