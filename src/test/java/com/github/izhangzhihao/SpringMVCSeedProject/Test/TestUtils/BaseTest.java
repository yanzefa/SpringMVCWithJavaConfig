package com.github.izhangzhihao.SpringMVCSeedProject.Test.TestUtils;

import com.github.izhangzhihao.SpringMVCSeedProject.Annotation.AuthorityType;
import com.github.izhangzhihao.SpringMVCSeedProject.Config.JPAConfig;
import com.github.izhangzhihao.SpringMVCSeedProject.Config.RedisConfig;
import com.github.izhangzhihao.SpringMVCSeedProject.Config.RootConfig;
import com.github.izhangzhihao.SpringMVCSeedProject.Config.WebConfig;
import com.github.izhangzhihao.SpringMVCSeedProject.Model.User;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.github.izhangzhihao.SpringMVCSeedProject.Utils.StringUtils.getRandomUUID;

@ActiveProfiles("development")
@RunWith(SpringJUnit4ClassRunner.class)
/*@WebAppConfiguration
@ContextConfiguration(classes = {
        JPAConfig.class, RedisConfig.class, RootConfig.class
})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class
})*/
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest(classes = {JPAConfig.class, RedisConfig.class, RootConfig.class, WebConfig.class}
        , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    protected DefaultWebSecurityManager securityManager;

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mockMvc;

    protected MockHttpSession mockHttpSession;

    @Autowired
    protected TestRestTemplate restTemplate;

    @LocalServerPort
    protected int port;


    /*protected InternalResourceViewResolver viewResolver;

    public BaseTest() {
        viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
    }*/

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        mockHttpSession = new MockHttpSession();
    }

    @Test
    public void NullTest() {

    }

    public static User getRandomUser() {
        return new User(getRandomUUID(), getRandomUUID(), AuthorityType.Admin);
    }
}
