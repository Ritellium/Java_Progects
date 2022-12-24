import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class MedicinesList
{
    ArrayList<Medicine_Belski> medicines;

    public MedicinesList()
    {
        medicines = new ArrayList<>();
    }

    public ArrayList<Medicine_Belski> firmList(String firm)
    {
        ArrayList<Medicine_Belski> oneFirm = new ArrayList<>();
        for (Medicine_Belski it: medicines)
        {
            if(it.getFirm().compareTo(firm) == 0)
            {
                oneFirm.add(it);
            }
        }
        return oneFirm;
    }

    public Medicine_Belski find(String name)
    {
        ArrayList<String> names = new ArrayList<>();
        for (Medicine_Belski it: medicines)
        {
            names.add(it.getName());
        }
        int index = Arrays.binarySearch(names.toArray(), name);

        if (index != -1)
        {
            return medicines.get(index);
        }
        else
        {
            return null;
        }
    }

    public void sort(Comparator<Medicine_Belski> comp)
    {
        medicines.sort(comp);
    }

    public void Input_File(BufferedReader fileIn)
    {
        Stream<String> stream1 = fileIn.lines();
        stream1.forEach(s -> medicines.add(new Medicine_Belski(s)));
    }

    public void Output_File(BufferedWriter fileOut) throws IOException
    {
        for (Medicine_Belski it: medicines)
        {
            it.printInFile(fileOut);
            fileOut.newLine();
        }
    }

    public void Output_CommandLine() throws IOException
    {
        for (Medicine_Belski it: medicines)
        {
            it.printCommandLine();
        }
    }
}
