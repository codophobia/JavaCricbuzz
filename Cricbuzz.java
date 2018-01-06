/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cricbuzz;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.*;
/**
 *
 * @author shivam.m
 */
public class Cricbuzz {
    
    String url = "http://synd.cricbuzz.com/j2me/1.0/livematches.xml";
    public Document getxml(String url) throws IOException {
        Document doc;
        doc = Jsoup.connect(url).get();
        return doc;
    }
    
    public Map<String,String> matchinfo(Element match) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("id",match.attr("id"));
        map.put("srs",match.attr("srs"));
        map.put("mchdesc",match.attr("mchdesc"));
        map.put("mnum",match.attr("mnum"));
        map.put("type",match.attr("type"));
        map.put("mchstate",match.select("state").attr("mchstate"));
        map.put("status",match.select("state").attr("status"));
        return map;
    }
    
    public Vector matches() throws IOException {
        Vector matches = new Vector();
        try {
            Document doc = getxml(url);
            Elements m = doc.select("match");
            for (Element x : m) {
                matches.add(matchinfo(x));
            }
            return matches;
        }
        catch (Exception e) {
            return matches;
        }
    }
    
    public Map<String,Map> livescore(String mid) throws IOException {
        Map<String,Map> score = new HashMap<String,Map>();
        try {
            Document doc = getxml(url);
            String query = String.format("match[id = %s]", mid);
            Element match = doc.select(query).get(0);
            score.put("matchinfo",matchinfo(match));

            String commurl = match.attr("datapath") + "commentary.xml";
            doc = getxml(commurl);
            Element mscr = doc.select("mscr").get(0);
            Element batting = mscr.select("bttm").get(0);
            Element bowling = mscr.select("blgtm").get(0);
            Elements batsman = mscr.select("btsmn");
            Elements bowler = mscr.select("blrs");

            HashMap<String,Vector<HashMap<String,String>>> b1 = new HashMap<String,Vector<HashMap<String,String>>>();
            Vector v1 = new Vector();
            HashMap<String,String> m1 = new HashMap<String,String>();
            m1.put("team",batting.attr("sname"));
            v1.add(m1);

            b1.put("team",v1);
            Vector v2 = new Vector();

            for(Element b: batsman) {
                HashMap<String,String> m2 = new HashMap<String,String>();
                m2.put("name", b.attr("sname"));
                m2.put("runs", b.attr("r"));
                m2.put("balls", b.attr("b"));
                m2.put("fours", b.attr("frs"));
                m2.put("six", b.attr("sxs"));
                v2.addElement(m2);
            }

            b1.put("batsman",v2);
            Elements binnings = batting.select("inngs");
            Vector v3 = new Vector();
            for(Element i: binnings) {
                HashMap<String,String> m3 = new HashMap<String,String>();
                m3.put("desc",i.attr("desc"));
                m3.put("runs",i.attr("r"));
                m3.put("wickets",i.attr("wkts"));
                m3.put("overs",i.attr("ovrs"));
                v3.add(m3);
            }
            b1.put("score",v3);
            score.put("batting",b1);

            HashMap<String,Vector<HashMap<String,String>>> b2 = new HashMap<String,Vector<HashMap<String,String>>>();
            Vector v = new Vector();

            HashMap<String,String> m = new HashMap<String,String>();
            m.put("team",bowling.attr("sname"));
            v.add(m);
            b2.put("team",v);
            //System.out.print(b2);
            Vector v5 = new Vector();
            for(Element b: bowler) {
                HashMap<String,String> m4 = new HashMap<String,String>();
                m4.put("name", b.attr("sname"));
                m4.put("overs", b.attr("ovrs"));
                m4.put("maidens", b.attr("mdns"));
                m4.put("runs", b.attr("r"));
                m4.put("wickets", b.attr("wkts"));
                v5.add(m4);
            }
            b2.put("bowler",v5);
            binnings = bowling.select("inngs");

            Vector v6 = new Vector();
            for(Element i: binnings) {
                HashMap<String,String> m5 = new HashMap<String,String>();
                m5.put("desc",i.attr("desc"));
                m5.put("runs",i.attr("r"));
                m5.put("wickets",i.attr("wkts"));
                m5.put("overs",i.attr("ovrs"));
                v6.add(m5);
            }
            b2.put("score",v6);
            score.put("bowling",b2);

            return score;
        }
        catch (Exception e) {
            return score;
        }
        
    }
    
    public Map<String,Map> commentary(String mid) throws IOException {
        Map<String,Map> score = new HashMap<String,Map>();
        try {
            Document doc = getxml(url);
            String query = String.format("match[id = %s]", mid);
            Element match = doc.select(query).get(0);
            score.put("matchinfo",matchinfo(match));

            String commurl = match.attr("datapath") + "commentary.xml";
            doc = getxml(commurl);
            Elements comm = getxml(commurl).select("c");


            HashMap<String,Vector> h = new HashMap<String,Vector>();
            Vector v = new Vector();
            for(Element c: comm) {
                v.add(c.text());
            }
            h.put("text",v);
            score.put("commentary",h);

            return score;
        }
        catch (Exception e) {
            return score;
        }
    }
        
    
}
