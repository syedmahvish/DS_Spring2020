import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/***
 * @author : Syed Mahvish (CWID : 10456845)
 * This program finds anagram of given word and its hashcode.
 * It maintains table to store word and hashcode.
 * Words with same hashcode stored in arraylist.
 */

public class Anagrams {
    /***
     * primes : is an Integer array stores first 26 prime number. Prime numbers are used to minimize collision while calculating hashcode.
     * letterTable : is a map of <Character,Integer> stores each alphabet letter as key and corresponding prime number (from primes array) as value.
     * anagramTable : is a map of <Long, ArrayList<String>> stores hashcode as key and words with same hashcode as list.
     */
    final Integer[] primes;
    Map<Character,Integer> letterTable;
    Map<Long, ArrayList<String>> anagramTable;

    /***
     * Constructor will initialize array and map.
     * It gives call to buildLetterTable() that builds letterTable which map character and prime number.
     */
    public Anagrams (){
        letterTable = new HashMap<>();
        anagramTable = new HashMap<>();
        primes = new Integer[]{2 , 3 , 5 , 7, 11 , 13 , 17 , 19 , 23 , 29 , 31 , 37 , 41 , 43 , 47 , 53 , 59 , 61 ,  67 , 71 , 73 , 79 , 83 , 89 , 97 , 101};
        buildLetterTable();
    }

    /***
     * This methods build the hash table letterTable which consists of the entries of alphabetic letter and prime number.
     * This table is to be used in myHashCode() method, for constructing the hash code of strings.
     */
    private void buildLetterTable(){
        char start = 'a';
        for(int i = 0 ; i < primes.length ; i++) {
            letterTable.put(start, primes[i]);
            start = (char) (start + 1);
        }
    }

    /***
     * This method should compute the hash code of the string s passed as argument, and should
     * add this word to the hash table anagramTable.
     * To compute  hash code of the string s , it calls myHashCode() method.
     * The words with same hashcode are stored with same key.
     * @param s word or string that need to be add in anagramTable along with its hashcode.
     */
    private void addWord(String s){
        Long hashcode = myHashCode(s);
        ArrayList<String> wordlist = new ArrayList<>();
        if(anagramTable.containsKey(hashcode)){
            wordlist =  anagramTable.get(hashcode);
       }
        wordlist.add(s);
        anagramTable.put(hashcode, wordlist);
    }

    /***
     * This method, given a string s, compute its hash code
     *  A requirement for myHashCode is that all the anagrams of a word should receive the same hash code.
     *  The hashcode is computed using unique factorization theorem (s[0] * 31^(n-1) * s[1] * 31^(n-2) * .... * s[n-1]  where n is length of given string).
     * @param s the word whose hashcode need to be compute.
     * @return hashcode of type Long for given word.
     */
    private Long myHashCode(String s){
        Long result = new Long(1);
        int count = 1;
       for(int i = 0 ; i < s.length() ; i++) {
           result = result * s.charAt(i) * ((long) Math.pow(31, s.length() - count)) ;
           count++;
       }
       return  result;
    }


    /***
     * The main method is processFile which receives the name of a text file containing words, one
     * per line, and builds the hash table anagramTable. For that it uses the addWord method.
     * @param s word that need to be add in anagramTable after computing its hashcode.
     * @throws IOException If given input file is not found throws exception.
     */
    public void processFile(String s) throws IOException {
            FileInputStream fstream = new FileInputStream (s );
            BufferedReader br = new BufferedReader ( new InputStreamReader( fstream ));
            String strLine ;
            while (( strLine = br . readLine ()) != null ) {
                 this.addWord (strLine);
            }
            br . close ();
    }

    /***
     * This method should return the entries in the anagramTable that have the largest number of
     * anagrams. It returns a list of them since there can be more than one list of anagrams with
     * a maximal size. It will be called by the main method.
     * @return ArrayList of map entries with maximum size.
     */
    private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries(){
        if(!anagramTable.isEmpty()) {
            long max = 0;
            ArrayList<Map.Entry<Long,ArrayList<String>>> wordlist = new ArrayList<>();

            for (Map.Entry<Long,ArrayList<String>> entry : anagramTable.entrySet()){
                long size = entry.getValue().size();
                if(size >= max) max = size;
            }

            for (Map.Entry<Long,ArrayList<String>> entry : anagramTable.entrySet()){
                long size = entry.getValue().size();
                if(size == max) {
                    wordlist.add(entry);
                }
            }

            return wordlist;
        }
       return null;
    }


    public static void main(String[] args){
        Anagrams a = new Anagrams ();
         final long startTime = System.nanoTime();
        try {
             a.processFile ("words_alpha.txt");
        } catch ( IOException e1 ) {
             e1 .printStackTrace ();
        }
         ArrayList <Map.Entry <Long , ArrayList < String > >> maxEntries = a.getMaxEntries ();
         final long estimatedTime = System.nanoTime() - startTime ;
         final double seconds = (( double ) estimatedTime /1000000000);
         System . out . println (" Time : "+ seconds );

        for (Map.Entry<Long,ArrayList<String>> entry : maxEntries){
            System . out . println (" Key of max anagrams : "+ entry.getKey());
            System . out . println (" List of max anagrams : "+ entry.getValue());
            System . out . println ("Length of list of max anagrams : "+ entry.getValue().size() );
        }

    }
}
