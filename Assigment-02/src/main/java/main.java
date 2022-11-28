import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class main {

    public static String getWebpageContent(String url) throws IOException {
        Connection conn = Jsoup.connect(url);
        Document doc = conn.get();
        String result = doc.body().text();
        result = result.toLowerCase();
        return result;
    }
    public static Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } finally {
            fis.close();
        }
        return prop;
    }

    public static DoublyLinkedList addWordsToDLL(String s , ArrayList avoidArr)
    {
         DoublyLinkedList l = new DoublyLinkedList();
        String curr = s;
        // Using split function.
       s = s.replace("(", "");
       s = s.replace(")", "");
       s = s.replace(",", "");
       s = s.replace("\"", "");
       s = s.replace(".", "");
       s = s.replace(":", "");
       s = s.replace("^", "");
       s = s.replace("]", "");
       s = s.replace("!", "");
       s = s.replace("?", "");
       s = s.replace("*", "");
       s = s.replace("®","");
       
        for (String val: s.split(" ")) //n^2
            if(val.contains("-")){
                val.split("-");
            }
            else if(val.contains("/")){
            val.split("/");
             }
            else if(val.contains("[")){
                val.split("\\[");
            }
            else if(val.contains("-")){
                val.split("-");
            }
            else if(val.contains("|")){
                val.split("|");
            }
            else if(!StringUtil.isNumeric(val) && val.length() > 1) {
                if(!Character.isDigit(val.charAt(0)))
                    if(avoidChecker(val,avoidArr)) {
                            l.addNode(val);
                    }
            }
            return l;
    }
public static int totalLetters(DoublyLinkedList x){
        int count = 0;
        DoublyLinkedList.Node curr = x.head;
    while(curr != null){
           count += getLetterCount(curr.data);
           curr = curr.next;

        }
    return count;
}
public static int getLetterCount(String x){
        int count = 0;
        for(int i = 0; i < x.length(); i++){
            if(Character.isLetter(x.charAt(i))){
                count++;
            }
        }
        return count;
}


    public static boolean avoidChecker(String val, ArrayList avoidArr){
        for (int i = 0; i < avoidArr.size(); i++) {
            if (val.equals(avoidArr.get(i))) {
                return false;
            }
        }
    return true;
    }
    public static ArrayList<String> propertyToArray(String avoid){
        ArrayList<String> arr = new ArrayList<>();
        for (String val: avoid.split(",")) {
            arr.add(val);
        }
        return arr;
    }




    public static void main(String args[]) throws IOException {
        Properties prop = readPropertiesFile("/Users/rodolfosevilla/Desktop/Assigment-02/src/main/java/textanalyzer.properties");
        ArrayList<String> avoid = propertyToArray(prop.getProperty("avoid"));
        String content = getWebpageContent("https://en.wikipedia.org/wiki/Pet_door");

        DoublyLinkedList ll = addWordsToDLL(content,avoid);
        System.out.println("Total words: " + ll.totalWords + "Total letter words: " +  ll.totalLetters);
        System.out.println(ll.shortestWord.data);
        System.out.println(ll.longestWord.data);
       //ll = ll.sortListnumWords();
        ll.display();



    }

}
