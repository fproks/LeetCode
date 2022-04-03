package LeetCode.Hutool;

import com.xiaoleilu.hutool.convert.Convert;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.io.FileUtil;
import com.xiaoleilu.hutool.io.IoUtil;
import com.xiaoleilu.hutool.util.CharsetUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

/**
 * @user: linhos
 * @Time: Create in 15:51 2017/10/9
 */

public class ConvertTest {
    @Test
    public void ConvertTests() throws Exception {
        int a = 1;
        int[] b = {1, 2, 3, 4, 5};
        Assert.assertEquals("1", Convert.toStr(a));
        Assert.assertEquals("[1, 2, 3, 4, 5]", Convert.toStr(b));
        String a1 = "123456";
        String hex = Convert.toHex(a1, CharsetUtil.CHARSET_UTF_8);

        Assert.assertEquals("313233343536", hex);

        double a2 = 1234577.66;

        String digiiUppercase = Convert.digitToChinese(a2);
        System.out.println(digiiUppercase);
    }

    @Test
    public void DateTest() throws Exception {
        String today = DateUtil.today();
        System.out.println(today);

        System.out.println(DateUtil.ageOfNow("1991-10-29"));
        System.out.println(DateUtil.isLeapYear(2017));
    }

    @Test
    public void IOTest() throws Exception {
        BufferedInputStream in = FileUtil.getInputStream("d:/123.txt");
        BufferedOutputStream out = FileUtil.getOutputStream("d:/234.txt");
        long copySize = IoUtil.copy(in, out, IoUtil.DEFAULT_BUFFER_SIZE);
    }
}
