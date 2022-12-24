import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Medicine_Belski
{
    static public class WrongInput extends Throwable {}

    private String name;
    private Integer warranty_year;
    private String manufacturer;
    private Integer amount_left;
    private Double price_for_one;

    public String getName()
    {
        return name;
    }

    public String getFirm()
    {
        return manufacturer;
    }
    public Medicine_Belski(String input)
    {
        Pattern toFindName = Pattern.compile("(\"[a-zA-Zа-яА-Я]+\")");
        Pattern toFindManufacturer = Pattern.compile("(<[a-zA-Zа-яА-Я]+>)");
        Pattern toFindWarranty = Pattern.compile("(2022)|(2023)|(2024)|(2025)");
        Pattern toFindAmount = Pattern.compile("( 100 )|( [1-9][0-9] )|( [1-9] )");
        Pattern toFindPrice = Pattern.compile("([0-9]+[.][0-9]+)");

        Matcher findsName = toFindName.matcher(input);
        findsName.find();
        name = input.substring(findsName.start() + 1, findsName.end() - 1);

        Matcher findsManufacturer = toFindManufacturer.matcher(input);
        findsManufacturer.find();
        manufacturer = input.substring(findsManufacturer.start() + 1, findsManufacturer.end() - 1);

        Matcher findsWarranty = toFindWarranty.matcher(input);
        findsWarranty.find();
        warranty_year = Integer.parseInt(input.substring(findsWarranty.start(), findsWarranty.end()));

        Matcher findsAmount = toFindAmount.matcher(input);
        findsAmount.find();
        amount_left = Integer.parseInt(input.substring(findsAmount.start() + 1, findsAmount.end() - 1));

        Matcher findsPrice = toFindPrice.matcher(input);
        findsPrice.find();
        price_for_one = Double.parseDouble(input.substring(findsPrice.start(), findsPrice.end()));
    }

    public void printInFile(BufferedWriter fileOut) throws IOException
    {
        fileOut.write(name);
        fileOut.write(warranty_year.toString());
        fileOut.write(manufacturer);
        fileOut.write(amount_left.toString());
        fileOut.write(price_for_one.toString());
    }

    public void printCommandLine() throws IOException
    {
        System.out.println(name);
        System.out.println(warranty_year.toString());
        System.out.println(manufacturer);
        System.out.println(amount_left.toString());
        System.out.println(price_for_one.toString());
    }

    static class MedicineComparatorName implements Comparator<Medicine_Belski> {

        public int compare(Medicine_Belski med1, Medicine_Belski med2){

            return med1.name.compareTo(med2.name);
        }
    }

    static class MedicineComparatorManufacturer implements Comparator<Medicine_Belski> {

        public int compare(Medicine_Belski med1, Medicine_Belski med2){

            return med1.manufacturer.compareTo(med2.manufacturer);
        }
    }

    static class MedicineComparatorPrice implements Comparator<Medicine_Belski> {

        public int compare(Medicine_Belski med1, Medicine_Belski med2){

            return med2.price_for_one.compareTo(med1.price_for_one);
        }
    }
}
