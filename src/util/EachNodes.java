package util;

import entity.ConzonInfo;
import javafx.util.Pair;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Collections.sort;

public class EachNodes
{
    private final Map<Integer, String> conzonDict;
    private final List<List<ConzonInfo>> adjacent;
    private final Map<String, Pair<Integer, Integer>> conzonID;
    public EachNodes()
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("assets/conzon_info.csv"), "EUC-KR"));
            conzonDict = new HashMap<>();
            adjacent = new ArrayList<>();
            conzonID = new HashMap<>();
            for (int i = 0 ; i < 1100; i++)
            {
                adjacent.add(new ArrayList<>());
            }

            Stream<String> lines = br.lines();
            br.readLine();
            lines.forEach(str -> parse_conzon(str, conzonDict ,adjacent));

        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    void parse_conzon(String str, Map<Integer, String> dict, List<List<ConzonInfo>> adja)
    {
        String[] element = str.split(",");
        try
        {
            int idx = Integer.parseInt(element[3]);
            String par = element[9];
            String[] split = par.split("-");

            if(!dict.containsKey(idx))
                dict.put(idx, split[0]);

            int from = Integer.parseInt(element[3]);
            int to = Integer.parseInt(element[4]);
            int dist = (int)Double.parseDouble(element[1]);
            adja.get(from).add(new ConzonInfo(to, dist));
            conzonID.put(element[0], new Pair<>(from, to));
        }catch (NumberFormatException ignored) {}
    }

    public Map<Integer, String> getConzonDict()
    {
        return conzonDict;
    }

    public List<List<ConzonInfo>> getAdjacent()
    {
        return adjacent;
    }

    public Map<String, Pair<Integer, Integer>> getConzonID()
    {
        return conzonID;
    }
}
