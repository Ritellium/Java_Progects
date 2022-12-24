import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException, Medicine_Belski.WrongInput
    {
        FileReader modelIn = new FileReader("input.txt");
        BufferedReader fileIn = new BufferedReader(modelIn);
        FileWriter modelOut = new FileWriter("output.txt");
        BufferedWriter fileOut = new BufferedWriter(modelOut);
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader sysIn = new BufferedReader(isr);

            MedicinesList medList = new MedicinesList();

            medList.Input_File(fileIn);
            medList.Output_File(fileOut);
            fileOut.newLine();

            Comparator<Medicine_Belski> compare;

            compare = new Medicine_Belski.MedicineComparatorName();
            medList.sort(compare);
            medList.Output_File(fileOut);
            fileOut.newLine();

            System.out.println("Введите имя лекарства для поиска: ");
            String name = sysIn.readLine();

            Medicine_Belski found = medList.find(name);
            if (found != null)
            {
                System.out.println("Элемент найден. Данные:");
                found.printCommandLine();
            }
            else
            {
                System.out.println("Элемент не найден");
            }

            compare = new Medicine_Belski.MedicineComparatorPrice();
            medList.sort(compare);
            medList.Output_File(fileOut);
            fileOut.newLine();

            System.out.println("Введите имя фирмы для вывода списка: ");
            String firm = sysIn.readLine();
            ArrayList<Medicine_Belski> oneFirm = medList.firmList(firm);

            for (Medicine_Belski it: oneFirm)
            {
                it.printCommandLine();
            }

            compare = new Medicine_Belski.MedicineComparatorManufacturer();
            medList.sort(compare);
            medList.Output_File(fileOut);
            fileOut.newLine();
        }
        catch (IOException e)
        {
            System.out.println(e.getLocalizedMessage());
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println(e.getLocalizedMessage());
        }
        finally
        {
            fileIn.close();
            fileOut.close();
        }
    }
}