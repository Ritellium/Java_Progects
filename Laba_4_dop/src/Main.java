import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

/*
В текстовом файле input.txt в записаны строки, в которых есть скобки.
Удалить в каждой строке текст в одинарных скобках и эти скобки.
Если есть вложенность, текст во вложенных скобках  не изменять.
Использовать  String,  StringBuffer и StringTokenizer.

Например
В текстовом( файле input.txt в записаны строки), в которых есть скобки.
Удалить (в каждой строке ( текст в (одинарных) )скобках и эти скобки. )
Если есть  (вложенность, )текст (во вложенных )скобках ( не) изменять.
(Использовать  String,  StringBuffer и StringTokenizer.)

*/

public class Main {

    public static IntPair str_GetBracketsSubstring(String input)
    {
        int first_bracket = input.indexOf('(');
        int counter = 1;
        for(int i = first_bracket + 1; i < input.length(); i++)
        {
            if (input.charAt(i) == '(')
            {
                counter++;
            }
            else if (input.charAt(i) == ')')
            {
                counter--;
            }
            if (counter == 0)
            {
                return new IntPair(first_bracket + 1, i);
            }
        }
        return new IntPair(0, input.length());
    }
    public static String Str_ClearOfSingleBrackets(String workOn)
    {
        String result = new String();
        boolean wasRedacted = false;
        while (workOn.length() != 0) {
            IntPair InnerIterators = str_GetBracketsSubstring(workOn);
            String workOnInner = workOn.substring(InnerIterators.first, InnerIterators.second);
            String resultIn = new String();

            while (workOnInner.length() != 0) {
                IntPair Inner2Iterators = str_GetBracketsSubstring(workOnInner);
                if(Inner2Iterators.second == workOnInner.length())
                {
                    break;
                }
                resultIn += workOnInner.substring(Inner2Iterators.first - 1, Inner2Iterators.second + 1);
                workOnInner = workOnInner.substring(Inner2Iterators.second + 1);
            }
            if (InnerIterators.first > 0) {
                result += workOn.substring(0, InnerIterators.first - 1) + resultIn;
            }
            else
            {
                result += resultIn;
            }
            if(InnerIterators.first == 0)
            {
                if (wasRedacted) {
                result += workOnInner.substring(0, InnerIterators.second);
                }
                break;
            }
            workOn = workOn.substring(InnerIterators.second + 1);
            wasRedacted = true;
        }

        return result;
    }

    public static void main(String[] args)  throws IOException
    {
        try {
            File file = new File("input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line = new String();
            ArrayList<String> FileStrings = new ArrayList<String>();

            Stream<String> stream = reader.lines();
            stream.forEach(s -> FileStrings.add(s));

            for (int j = 0; j<FileStrings.size(); j++)
            {
                FileStrings.set(j, Str_ClearOfSingleBrackets(FileStrings.get(j)));
                System.out.println(FileStrings.get(j));
            }

        }
        catch (IOException e) {
            System.out.println("Ошибка чтения из файла");
        }
    }
}