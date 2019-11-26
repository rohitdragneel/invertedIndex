import java.io.*;
import java.util.*;

class Index {
    Map<Integer,String> sources;
    HashMap<String, HashSet<Integer>> index;
    int j=0; int i = 0;
    Index(){
        sources = new HashMap<Integer,String>();
        index = new HashMap<String, HashSet<Integer>>();
    }

    public void buildIndex(String[] files){
       
        for(String fileName:files){

            
            try(BufferedReader file = new BufferedReader(new FileReader(fileName)))
            {
                
                String ln;
                
                while( (ln = file.readLine()) !=null) {
                    String[] words = ln.split("\\W+");
                    String[] paras=ln.split("\\n");
                    //use  tab for the new paragraph if not change the above code to /n
                    for(String para:paras){
                        //System.out.println(para);
                        sources.put(i,para);
                        j++;
                    
                    for(String word:words){
                        word = word.toLowerCase();
                        if (!index.containsKey(word))
                            index.put(word, new HashSet<Integer>());
                        index.get(word).add(i);
                    }
                    i++;
                }
                }
            } catch (IOException e){
                System.out.println("File "+fileName+" not found. Skip it");
            }
          
        }
        
    }

    public void find(String phrase){
        String[] words = phrase.split("\\W+");
        HashSet<Integer> res = new HashSet<Integer>(index.get(words[0].toLowerCase()));
        for(String word: words){
            res.retainAll(index.get(word));
        }

        if(res.size()==0) {
            System.out.println("Not found");
            return;
        }
        System.out.println("Found in: ");
        int limit=0;
        for(int num : res){
          if(limit==10)break;
            System.out.println("key="+num);
            System.out.println("\t"+sources.get(num));
            if(sources.get(num)!=null){
                    limit++;
            }
        }
        res.clear();
    }
}

public class InvertedIndex2 {
	

	public static void main(String args[]) throws IOException{
        Index index = new Index();
        index.buildIndex(new String[]{"abc.txt"});

        System.out.println("Print search phrase: ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String phrase = in.readLine();

        index.find(phrase);
    
	}
}
