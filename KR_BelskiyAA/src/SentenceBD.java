import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class SentenceBD {
    ArrayList<Tokens> sentenceList;

    public SentenceBD(BufferedReader reader) {
        sentenceList = new ArrayList<>();

        StringBuilder stBuild = new StringBuilder();
        Stream<String> stream1 = reader.lines();
        stream1.forEach(s -> stBuild.append(s));

        String in = stBuild.toString();
        String splice[] = in.split("[!?]");

        for (int i = 0; i < splice.length; i++) {
            {
                sentenceList.add(new Tokens(splice[i]));
            }
        }
    }

    public void writeLongNumbers(BufferedWriter writer) throws IOException // 4
    {
        for (Tokens it : sentenceList) {
            ArrayList<String> numbers = it.getLongNumbers();
            for (String num : numbers) {
                writer.write(num);
                writer.write(" ");
            }
            writer.newLine();
        }
    }

    public void writeMap(BufferedWriter writer) throws IOException {
        HashMap<String, Tokens> map = new HashMap<>();
        for (Tokens it : sentenceList) {
            map.put(it.getKey(), it);
        }

        Collection<Tokens> res = map.values();

        for (Tokens it : res) {
            writer.write(it.toString());
            writer.newLine();
        }
    }

    public void outSortFirst(BufferedWriter writer) throws IOException {
        sentenceList.sort(new Tokens.CompareFirst());
        for (Tokens it : sentenceList) {
            writer.write(it.toString());
            writer.newLine();
        }
    }

    public void serializeToXML(BufferedWriter writer) throws IOException {
        writer.write("<SentenceBD>");
        writer.newLine();

        for (Tokens token : sentenceList) {
            writer.write("\t<Tokens>");
            writer.newLine();

            writer.write(token.toXML());

            writer.write("\t</Tokens>");
            writer.newLine();
        }

        writer.write("</SentenceBD>");
        writer.newLine();
    }

    public void outDeleteSubstrings(BufferedWriter writer) throws IOException {

        for (Tokens token : sentenceList) {
            writer.write(token.deleteSubstrings());
            writer.newLine();
        }
    }
}
