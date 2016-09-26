package com.github.izhangzhihao.SpringMVCSeedProject.Test.ControllerTest;


import com.github.izhangzhihao.SpringMVCSeedProject.Test.TestUtils.BaseTest;
import org.apache.shiro.SecurityUtils;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MvcResult;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class AccountControllerTest extends BaseTest {

    /*@Autowired
    private AccountController controller;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setViewResolvers(viewResolver)
                .build();
    }*/

    /**
     * 登陆页面的测试
     */
    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/Account/Login"))
                .andDo(print())
                .andExpect(view().name("Account/Login"))
                .andExpect(forwardedUrl("/WEB-INF/views/Account/Login.jsp"))
                .andExpect(status().isOk());
    }

    /**
     * 登陆成功的测试
     */
    @Test
    public void testLoginSuccess() throws Exception {
        SecurityUtils.setSecurityManager(securityManager);

        //请求验证码，验证码会保存在session里
        HttpSession session = mockMvc
                .perform(get("/Account/validateCode"))
                .andDo(print())
                .andReturn()
                .getRequest()
                .getSession();

        /*mockMvc.perform(get("/Account/Login")
                .session(this.session))
                .andExpect(model().attributeExists("user"));*/

        String validateCode = session.getAttribute("validateCode").toString();
        mockHttpSession.setAttribute("validateCode", validateCode);

        MvcResult result = mockMvc.perform(post("/Account/Login")
                .param("UserName", "admin")
                .param("Password", "d82494f05d6917ba02f7aaa29689ccb444bb73f20380876cb05d1f37537b7892")
                .param("validateCode", validateCode)
                .param("RememberMe", "on")
                .session(mockHttpSession)
        )
                .andDo(print())
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/"))
                //.andExpect(cookie().exists("rememberMe"))
                //.andExpect(cookie().exists("SHRIOSESSIONID"))
                .andReturn();

        //登陆完后清除session中的validateCode
        assertFalse(result.getRequest().getSession().getAttributeNames().hasMoreElements());

    }

    /*
     * 登录失败测试
     */
    @Test
    public void testLoginFail() throws Exception {
        MvcResult result = mockMvc.perform(post("/Account/Login")
                .param("UserName", "admin")
                .param("Password", "11111")
                .param("validateCode", "11111")
                .param("RememberMe", "on")
        )
                .andDo(print())
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/Account/Login"))
                .andReturn();

        //登陆失败后清除session中的validateCode
        assertFalse(result.getRequest().getSession().getAttributeNames().hasMoreElements());
    }

    /**
     * 对验证码的测试
     */
    @Test
    public void testValidateCode() throws Exception {

        MvcResult result = mockMvc
                .perform(get("/Account/validateCode"))
                .andDo(print())
                .andReturn();

        boolean isImage = result
                .getResponse()
                .getContentType().equals("image/jpeg");
        assertTrue(isImage);

        HttpSession session = result.getRequest().getSession();
        String validateCode = session.getAttribute("validateCode").toString();
        assertEquals(validateCode.length(), 4);
    }

    /**
     * 退出登陆的测试
     */
    @Test
    public void testLoginOut() throws Exception {

        SecurityUtils.setSecurityManager(securityManager);

        mockMvc.perform(get("/Account/LogOut"))
                .andDo(print())
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/Account/Login"));
    }
}
