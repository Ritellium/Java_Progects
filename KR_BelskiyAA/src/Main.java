import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws IOException {
        FileReader modelIn = new FileReader("input.txt");
        BufferedReader fileIn = new BufferedReader(modelIn);
        FileWriter modelOut1 = new FileWriter("result1.txt");
        BufferedWriter fileOut1 = new BufferedWriter(modelOut1);
        FileWriter modelOut2 = new FileWriter("result2.txt");
        BufferedWriter fileOut2 = new BufferedWriter(modelOut2);
        FileWriter modelOut3 = new FileWriter("result3.txt");
        BufferedWriter fileOut3 = new BufferedWriter(modelOut3);
        FileWriter modelOut4 = new FileWriter("result4.txt");
        BufferedWriter fileOut4 = new BufferedWriter(modelOut4);
        FileWriter modelOutXML = new FileWriter("result.xml");
        BufferedWriter fileOutXML = new BufferedWriter(modelOutXML);

        try {
            SentenceBD ch = new SentenceBD(fileIn);
            ch.writeLongNumbers(fileOut1);
            ch.writeMap(fileOut2);
            ch.outSortFirst(fileOut3);
            //ch.outDeleteSubstrings(fileOut4); // not work
            ch.serializeToXML(fileOutXML);

        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            fileIn.close();
            fileOut1.close();
            fileOut2.close();
            fileOut3.close();
            fileOutXML.close();
        }
    }
}