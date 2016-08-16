package com.github.izhangzhihao.SpringMVCSeedProject.Test.UtilsTest;


import org.junit.Test;

import static com.github.izhangzhihao.SpringMVCSeedProject.Utils.SHAUtils.getMD5;
import static com.github.izhangzhihao.SpringMVCSeedProject.Utils.SHAUtils.getSHA_1;
import static com.github.izhangzhihao.SpringMVCSeedProject.Utils.SHAUtils.getSHA_256;
import static org.junit.Assert.assertEquals;

public class SHAUtilsTest {
    @Test
    public void sha1Test() {
        String sha1 = getSHA_1("admin");
        assertEquals("d033e22ae348aeb5660fc2140aec35850c4da997", sha1);
    }

    @Test
    public void sha256Test() {
        String sha256 = getSHA_256("123456123456");
        System.out.println(
                getSHA_256(
                        getSHA_256("admin" + "admin")
                )
        );
        assertEquals("958d51602bbfbd18b2a084ba848a827c29952bfef170c936419b0922994c0589", sha256);
    }

    @Test
    public void MD5Test() {
        String md5 = getMD5("admin");
        assertEquals(md5, "21232f297a57a5a743894a0e4a801fc3");
    }
}
