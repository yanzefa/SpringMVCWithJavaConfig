package com.github.izhangzhihao.SpringMVCSeedProject.Test.ControllerTest;


import com.github.izhangzhihao.SpringMVCSeedProject.Controller.MustLoginController;
import com.github.izhangzhihao.SpringMVCSeedProject.Test.TestUtils.BaseTest;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class MustLoginControllerTest extends BaseTest {

    @Autowired
    private MustLoginController mustLoginController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(mustLoginController)
                .setViewResolvers(viewResolver)
                .build();
    }

   /* @Test
    public void mustLoginPage() throws Exception {
        mockMvc.perform(get("/MustLogin"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(view().name("/MustLogin/MustLogin"));
    }*/
}
