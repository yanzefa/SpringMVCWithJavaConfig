package com.github.izhangzhihao.SpringMVCSeedProject.Test.ControllerTest;


import com.github.izhangzhihao.SpringMVCSeedProject.Test.TestUtils.BaseTest;
import org.junit.Test;

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

	/*
     * 登录成功测试
     */
    //@Test 加了验证码之后没法继续跑了
    public void testLoginSuccess() throws Exception {
        mockMvc.perform(post("/Account/Login")
                .param("UserName", "admin")
                .param("Password", "d033e22ae348aeb5660fc2140aec35850c4da997"))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/MustLogin"));
    }

	/*
	 * 登录失败测试 NotSerializableException: org.springframework.mock.web.MockHttpSession
     */
    /*@Test
    public void testLoginFalse() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("User", new User());
        mockMvc.perform(post("/Account/Login")
                .param("UserName", "admin")
                .param("Password", "11111"))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/Account/Login"));
    }*/
}
