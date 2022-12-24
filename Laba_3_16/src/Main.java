/*
Бельский Алексей

16.	В текстовом файле input.txt записаны слова – по одному в строке.
Необходимо разбить слова на группы так, чтобы в одну группу попали все слова,
составленные из одного и того же множества символов.
Иными словами, в каждом слове группы должны быть использованы все символы этой группы (может быть, несколько раз).
Так, слова «окно» и «конка» не входят в одну группу, а слова «конник» и «инок» - входят.
Программа должна подсчитать количество найденных групп и выдать результат на консоль.
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;
import java.io.IOException;
import java.io.*;
import java.text.*;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {

    public static boolean Consist_Of(String CheckAble, String GrSample)
    {
        for (int i = 0; i < CheckAble.length(); i++)
        {
            if (GrSample.indexOf(CheckAble.charAt(i)) == -1)
            {
                return false;
            }
        }
        return true;
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
            StringTokenizer Words_Token = new StringTokenizer(Words);

            ArrayList<String> Groups = new ArrayList<String>();

            while(Words_Token.hasMoreTokens())
            {
                String CurrWord = Words_Token.nextToken();
                int i = 0;

                for (i = 0; i < Groups.size(); i++)
                {
                    StringTokenizer GroupSample_Token = new StringTokenizer(Groups.get(i));
                    String GroupSample = GroupSample_Token.nextToken();

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


