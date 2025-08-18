package Task_5_files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class InvalidFormatExecption extends Exception{
    InvalidFormatExecption(String message){
        super(message);
    }
}
public class Task5_withErrors {

    public static void main(String[] args) {
        File InputFile = new File("Task_5_files\\productswithmissingdata.csv");
        File OutputFile = new File("Task_5_files\\filteredproducts.csv");
        FileReader InputReader = null; 
        FileWriter outputWriter = null;
        BufferedReader BuffReader = null;
        try {
            InputReader = new FileReader(InputFile);
            outputWriter = new FileWriter(OutputFile);
            BuffReader = new BufferedReader(InputReader);
            String line = "";
            int count = 0;
            while((line = BuffReader.readLine())!=null){
                if(count == 0){
                    count++;
                    continue;
                }
                String [] record = line.split(",");
                if(record.length==2){
                    if(record[0] == null || record[1] == null){
                        outputWriter.close();
                        throw new InvalidFormatExecption("no record "+line);
                    }
                    String name = record[0];
                    int price = Integer.parseInt(record[1]);
                    if(price>1000)
                        outputWriter.write(name+","+price+"\n");
                }
                else{
                    outputWriter.close();
                    throw new InvalidFormatExecption("Records not matiching convention record lenght"+record.length+" != "+2);
                }
            
            }

        } catch (FileNotFoundException exception) {
            
        }
        catch(IOException exception){
            System.out.println(exception.getMessage());
        }
        catch(InvalidFormatExecption exception){
            System.out.println(exception.getMessage());
        }
        catch(NumberFormatException exception){
            System.out.println(exception.getMessage());
        }
        finally{
            try {
                BuffReader.close();
                outputWriter.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}