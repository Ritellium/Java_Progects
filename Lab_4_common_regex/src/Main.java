import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/*
16.	В текстовом файле input.txt записаны слова – по одному в строке.
Необходимо разбить слова на группы так, чтобы в одну группу попали все слова,
составленные из одного и того же множества символов.
Иными словами, в каждом слове группы должны быть использованы
все символы этой группы (может быть, несколько раз).
Так, слова «окно» и «конка» не входят в одну группу, а слова «конник» и «инок» - входят.
Программа должна подсчитать количество найденных групп и выдать результат на консоль.
*/

public class Main {

    public static boolean Consist_Of(String CheckAble, String GrSample) {
        Pattern p = Pattern.compile("[" + GrSample + "]+");
        Matcher matcher = p.matcher(CheckAble);

        if (matcher.matches()) {
            return true;
        } else
            return false;
    }

    public static void main(String[] args)  throws IOException
    {
        try {
            File file = new File("input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line = new String();
            StringBuilder Build = new StringBuilder(line);

            Stream<String> stream = reader.lines();
            stream.forEach(s -> Build.append(s).append("\n"));

            String Words = Build.toString();

            Pattern Words_space = Pattern.compile("[а-яА-Я]+");
            Matcher Words_Finder = Words_space.matcher(Words);

            ArrayList<String> Groups = new ArrayList<String>();

            while(Words_Finder.find())
            {
                String CurrWord = Words.substring(Words_Finder.start(), Words_Finder.end());
                int i = 0;

                for (i = 0; i < Groups.size(); i++)
                {
                    Matcher GroupSample_Finder = Words_space.matcher(Groups.get(i));
                    GroupSample_Finder.find();
                    String GroupSample = Groups.get(i).substring(GroupSample_Finder.start(), GroupSample_Finder.end());

                    if ( Consist_Of(CurrWord, GroupSample)
                            && Consist_Of(GroupSample, CurrWord) )
                    {
                        Groups.set(i, new String(Groups.get(i) + " " + CurrWord));
                        break;
                    }
                }
                if (i == Groups.size())
                {
                    Groups.add(CurrWord);
                }
            }

            System.out.println("Количество групп: " + Integer.toString(Groups.size()));
            for (int j = 0; j<Groups.size(); j++)
            {
                System.out.println(Groups.get(j));
            }
        }
        catch (IOException e) {
            System.out.println("Ошибка чтения из файла");
        }
    }
}