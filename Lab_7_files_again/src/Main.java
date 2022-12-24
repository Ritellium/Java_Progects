import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        FileReader isr2 = new FileReader("input.csv");
        BufferedReader fileIn = new BufferedReader(isr2);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader sysIn = new BufferedReader(isr);

        FileWriter file1txt = new FileWriter("logfile.txt", true);
        BufferedWriter loggerTxt = new BufferedWriter(file1txt);

        FileWriter file1xml = new FileWriter("out.xml");
        BufferedWriter xmlWrite = new BufferedWriter(file1xml);
        FileWriter file1json = new FileWriter("out.json");
        BufferedWriter jsonWrite = new BufferedWriter(file1json);

        ArrayList<FakeCompany> companiesFile = new ArrayList<>();
        Stream<String> inputs = fileIn.lines();
        inputs.forEach(s -> companiesFile.add(new FakeCompany(s, ";")));

        companiesFile.forEach(obj -> {
            try {
                xmlWrite.write(obj.toString());
                xmlWrite.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        xmlWrite.close();

        companiesFile.forEach(obj -> {
            try {
                jsonWrite.write(obj.toString());
                jsonWrite.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        jsonWrite.close();

        loggerTxt.write("Запуск: <" + formatter.format(date) + ">");
        loggerTxt.newLine();

        Integer counter = 0;

        System.out.println("Введите имя компании для поиска: ");
        String name = sysIn.readLine().toLowerCase();
        System.out.println("Результаты");
        for (FakeCompany u: companiesFile)
        {
            if (u.shortTitle.toLowerCase().compareTo(name) == 0) {
                System.out.println(u);
                counter++;
            }
        }
        System.out.println();

        loggerTxt.write("Искалось по имени: <" + name + ">, найдено: " + counter);
        loggerTxt.newLine();

        counter = 0;

        System.out.println("Введите отрасль компании для поиска: ");
        String branch = sysIn.readLine().toLowerCase();
        System.out.println("Результаты");
        for (FakeCompany u: companiesFile)
        {
            if (u.branch.toLowerCase().compareTo(branch) == 0) {
                System.out.println(u);
                counter++;
            }
        }
        System.out.println();

        loggerTxt.write("Искалось по отрасли: <" + branch + ">, найдено: " + counter);
        loggerTxt.newLine();

        counter = 0;

        System.out.println("Введите вид деятельности компании для поиска: ");
        String activity = sysIn.readLine().toLowerCase();
        System.out.println("Результаты");
        for (FakeCompany u: companiesFile)
        {
            if (u.activity.toLowerCase().compareTo(activity) == 0) {
                System.out.println(u);
                counter++;
            }
        }
        System.out.println();

        loggerTxt.write("Искалось по роду деятельности: <" + activity + ">, найдено: " + counter);
        loggerTxt.newLine();

        counter = 0;

        System.out.println("Введите дату основания компании для поиска: ");
        System.out.print("Дата с: ");
        Integer firstData = Integer.parseInt(sysIn.readLine());
        System.out.print("Дата по: ");
        Integer lastData = Integer.parseInt(sysIn.readLine());
        System.out.println("Результаты");
        for (FakeCompany u: companiesFile)
        {
            if (u.dateFoundation >= firstData && u.dateFoundation <= lastData) {
                System.out.println(u);
                counter++;
            }
        }
        System.out.println();

        loggerTxt.write("Искалось по дате основания в диапазоне [" + firstData + "," + lastData + "], найдено: " + counter);
        loggerTxt.newLine();

        counter = 0;

        System.out.println("Введите численности сотрудников компании для поиска: ");
        System.out.print("Минимум: ");
        Integer min = Integer.parseInt(sysIn.readLine());
        System.out.print("Максимум: ");
        Integer max = Integer.parseInt(sysIn.readLine());
        System.out.println("Результаты");
        for (FakeCompany u: companiesFile)
        {
            if (u.countEmployees >= min && u.countEmployees <= max) {
                System.out.println(u);
                counter++;
            }
        }
        System.out.println();

        loggerTxt.write("Искалось по численности сотрудников в диапазоне [" + min + "," + max + "], найдено: " + counter);
        loggerTxt.newLine();

        loggerTxt.close();
    }
}