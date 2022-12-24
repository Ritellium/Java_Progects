import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    private static Pattern openTagFinder;

    public static void main(String[] args)  throws IOException
    {
        FileReader fr1 = new FileReader("input.html");
        BufferedReader readerHtml = new BufferedReader(fr1);

        FileReader fr2 = new FileReader("input2.txt");
        BufferedReader readerTxt = new BufferedReader(fr2);

        FileWriter fileOut1 = new FileWriter("output1.txt");
        BufferedWriter Writer1 = new BufferedWriter(fileOut1);
        FileWriter fileOut2 = new FileWriter("output2.txt");
        BufferedWriter Writer2 = new BufferedWriter(fileOut2);
        FileWriter fileOut3 = new FileWriter("output3.txt");
        BufferedWriter Writer3 = new BufferedWriter(fileOut3);

        try {
            ArrayList<String> tags = new ArrayList<String>();

            Stream<String> stream1 = readerHtml.lines();
            stream1.forEach(s -> tags.add(s));

            ArrayList<String> toFind = new ArrayList<String>();

            Pattern toFindFinder = Pattern.compile("([a-zA-Zа-яА-Я]+(?=;))|([a-zA-Zа-яА-Я]+)");
            String line = new String();
            while(true)
            {
                line = readerTxt.readLine();
                if(line == null)
                {
                    break;
                }
                Matcher finds = toFindFinder.matcher(line);
                while(finds.find())
                {
                    toFind.add(line.substring(finds.start(), finds.end()));
                }
            }
            ArrayList<String> openTags = new ArrayList<String>();
            ArrayList<String> closeTags = new ArrayList<String>();

            Pattern openTagFinder = Pattern.compile("<[a-zA-Zа-яА-Я]+>");

            for (int i = 0; i < tags.size(); i++)
            {
                Matcher allTags = openTagFinder.matcher(tags.get(i));
                while (allTags.find()) {
                    String currentTag = tags.get(i).substring(allTags.start(), allTags.end());
                    openTags.add(currentTag);
                    Pattern closeTagFinder = Pattern.compile("</" + currentTag.substring(1, currentTag.length() - 1) + ">");
                    Matcher allClose = closeTagFinder.matcher(tags.get(i));
                    allClose.find();
                    String currCloseTag = tags.get(i).substring(allClose.start(), allClose.end());
                    closeTags.add(currCloseTag);
                }
            }

            Collections.sort(openTags, new Comparator<String>() {
                @Override
                public int compare(String one, String two) {
                    int len1 = one.length();
                    int len2 = two.length();
                    return len1 - len2;
                }
            });

            Collections.sort(closeTags, new Comparator<String>() {
                @Override
                public int compare(String one, String two) {
                    int len1 = one.length();
                    int len2 = two.length();
                    return len1 - len2;
                }
            });

            for (int i = 0; i < openTags.size(); i++)
            {
                Writer1.write(openTags.get(i) + " " + closeTags.get(i));
                Writer1.newLine();
            }


        }
        catch (IOException e) {
            System.out.println("Ошибка чтения из файла");
        }
        finally {
            Writer1.close();
            Writer2.close();
            Writer3.close();
            readerHtml.close();
            readerTxt.close();
        }
    }
}