package LeetCode.struct;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : linhos
 * @Time : Created in 上午11:31 17-10-12
 * @Site :
 * @PACKAGE : LeetCode.struct
 * @File : MapSum.java
 */
//677. Map Sum Pairs
class MapSum {
    int val;
    private Map<Character, MapSum> dictionary;

    /**
     * Initialize your data structure here.
     */
    public MapSum() {
        dictionary = new HashMap<>();
        val=0;
    }

    public void insert(String key, int val) {
        if (key.length() > 0) {
            char start = key.charAt(0);
            if (!dictionary.containsKey(start))
                dictionary.put(start, new MapSum());

            MapSum tmp =dictionary.get(start);

            if(key.length()==1) tmp.val=val;
            else tmp.insert(key.substring(1,key.length()),val);

        }
    }

    public int sum(String prefix) {
        MapSum tmp =null;
       if(prefix.length()>0){
           char start =prefix.charAt(0);
           tmp =dictionary.get(start);
           if(tmp==null) return 0;
           if(prefix.length()==1){
               return tmp.subSum();
           }else {
               return  tmp.sum(prefix.substring(1,prefix.length()));
           }
       }
       return 0;
    }

    private int subSum(){
        final int[] sum = {0};
        sum[0]+=this.val;
        dictionary.forEach((k,v)->{
            sum[0]+=v.subSum();
        });
        return sum[0];
    }
}
