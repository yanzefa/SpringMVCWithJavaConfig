package com.github.izhangzhihao.SpringMVCSeedProject.Test.TestUtils;

import com.github.izhangzhihao.SpringMVCSeedProject.Annotation.AuthorityType;
import com.github.izhangzhihao.SpringMVCSeedProject.Config.JPAConfig;
import com.github.izhangzhihao.SpringMVCSeedProject.Config.RedisConfig;
import com.github.izhangzhihao.SpringMVCSeedProject.Config.RootConfig;
import com.github.izhangzhihao.SpringMVCSeedProject.Model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static com.github.izhangzhihao.SpringMVCSeedProject.Utils.StringUtils.getRandomUUID;

@ActiveProfiles("development")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        JPAConfig.class, RedisConfig.class, RootConfig.class
})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class
})
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {
    protected MockMvc mockMvc;

    protected InternalResourceViewResolver viewResolver;

    public BaseTest() {
        viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
    }

    @Test
    public void NullTest() {

    }

    public static User getRandomUser() {
        return new User(getRandomUUID(), getRandomUUID(), AuthorityType.Admin);
    }
}
