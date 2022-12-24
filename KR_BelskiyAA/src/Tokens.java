import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokens {

    ArrayList<String> leks;

    public Tokens(String input) {

        leks = new ArrayList<>();

        Pattern toFindLong = Pattern.compile("^[1-9][0-9]*$");
        Pattern toFindTime = Pattern.compile("([0-1][0-9])|(2[0-3]):[0-5][0-9]:[0-5][0-9]");
        Pattern toFindDouble = Pattern.compile("[0-9]+[.][0-9]*");
        Pattern toFindWord = Pattern.compile("[a-zA-Z]+");

        String buffer[] = input.split("[;_-]");

        for (int i =0; i< buffer.length; i++){
            leks.add(buffer[i]);
        }
    }

    public String getKey()
    {
        return leks.get(0);
    }

    public String toString() { // 2
        StringBuilder stBuild = new StringBuilder();
        for (String it: leks)
        {
            stBuild.append(it).append(" ");
        }
        return stBuild.toString();
    }

    static class CompareFirst implements Comparator<Tokens> { // 6

        public int compare(Tokens t1, Tokens t2) {

            return t1.leks.get(0).compareTo(t2.leks.get(0));
        }
    }

    public ArrayList<String> getLongNumbers() // 4
    {
        ArrayList<String> result = new ArrayList<>();
        Pattern toFindLong = Pattern.compile("^[1-9][0-9]*$");

        for(String it: leks) {
            Matcher longFinder = toFindLong.matcher(it);
            if (longFinder.matches())
            {
                result.add(it);
            }
        }

        return result;
    }

    public String toXML()
    {
        StringBuilder stBuild = new StringBuilder();
        for (String it: leks)
        {
            stBuild.append("\t\t<token>").append(it).append("</token>").append("\n");
        }
        return stBuild.toString();
    }

    public String deleteSubstrings()
    {
        String sentence = this.toString();

        Pattern pattern1 = Pattern.compile("((?: )[0-9]+(?: ))|(^[0-9]+(?: ))");
        Matcher matcher1 = pattern1.matcher(sentence);

        while (matcher1.find()) {
            String substring = matcher1.group(2);
            if (substring != null) {
                sentence = sentence.replace(substring, "");
            }
        }

        return sentence;
    }
}
