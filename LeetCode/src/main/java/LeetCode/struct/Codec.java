package LeetCode.struct;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : linhos
 * @Time : Created in 下午1:37 17-10-10
 * @Site :
 * @PACKAGE : LeetCode.struct
 * @File : Codec.java
 */
// 将长url 转换成短url
    //http://www.cnblogs.com/shanelau/p/7128746.html
public class Codec {
    private static Map<String,String> longShort =new HashMap<>();
    private static Map<String,String> shortLong =new HashMap<>();
     // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if(longShort.containsKey(longUrl))
            return longShort.get(longUrl);
        String shortUrl;
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        do{
            StringBuilder builder =new StringBuilder();
            for (int i=0;i<6;i++){
                int n =(int)(Math.random()*chars.length());
                builder.append(chars.charAt(n));
            }
            shortUrl =builder.toString();
        }while (shortLong.containsKey(shortUrl));
        shortLong.put(shortUrl,longUrl);
        longShort.put(longUrl,shortUrl);
        return shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return shortLong.get(shortUrl);
    }
}
