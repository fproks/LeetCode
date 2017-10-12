package LeetCode.struct;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author : linhos
 * @Time : Created in 下午1:54 17-10-12
 * @Site :
 * @PACKAGE : LeetCode.struct
 * @File : MapSumTest.java
 */
public class MapSumTest {
    @Test
    public void sum() throws Exception {
        MapSum sum =new MapSum();
        sum.insert("a",3);
        System.out.println(sum.sum("ap"));
        sum.insert("b",2);
        int res=sum.sum("a");
        Assert.assertEquals(3,res);
    }

}