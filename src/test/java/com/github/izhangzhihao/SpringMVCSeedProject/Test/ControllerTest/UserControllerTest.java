package com.github.izhangzhihao.SpringMVCSeedProject.Test.ControllerTest;

import com.github.izhangzhihao.SpringMVCSeedProject.Test.TestUtils.BaseTest;
import org.junit.Test;

import static com.github.izhangzhihao.SpringMVCSeedProject.Utils.StringUtils.getRandomUUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserControllerTest extends BaseTest {
    /*@Autowired
    private UserController userController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .setViewResolvers(viewResolver)
                .build();
    }*/

    @Test
    public void getAllUsersTest() throws Exception {
        mockMvc.perform(get("/User/UserList"))
                .andDo(print())
                //.andExpect(status().is(200))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserExistTest() throws Exception {
        //获取单个对象并自动将json转化为对应对象，可惜不能测试http status
        //User admin = restTemplate.getForObject("/User/getUser/admin", User.class);
        //assertNotNull(admin);

        mockMvc.perform(get("/User/User/admin"))
                .andDo(print())
                //.andExpect(status().is(200))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserNotExistTest() throws Exception {
        mockMvc.perform(get("/User/User/" + getRandomUUID()))
                .andDo(print())
                //.andExpect(status().is(404))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createUserExistTest() throws Exception {
        mockMvc.perform(
                post("/User/User/userName/" + "admin" + "/passWord/" + getRandomUUID())
        )
                .andDo(print())
                //.andExpect(status().is(409))
                .andExpect(status().isConflict());
    }

    @Test
    public void createUserNotExistTest() throws Exception {
        mockMvc.perform(
                post("/User/User/userName/" + getRandomUUID() + "/passWord/" + getRandomUUID())
        )
                .andDo(print())
                //.andExpect(status().is(201))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateUserExistTest() throws Exception {
        String userName = getRandomUUID();
        mockMvc.perform(
                post("/User/User/userName/" + userName + "/passWord/" + getRandomUUID())
        );

        mockMvc.perform(
                put("/User/User/userName/" + userName + "/passWord/" + getRandomUUID())
        )
                .andDo(print())
                //.andExpect(status().is(200))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUserNotExistTest() throws Exception {
        mockMvc.perform(
                put("/User/User/userName/" + getRandomUUID() + "/passWord/" + getRandomUUID())
        )
                .andDo(print())
                //.andExpect(status().is(404))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteUserExistTest() throws Exception {
        String userName = getRandomUUID();
        mockMvc.perform(
                post("/User/User/userName/" + userName + "/passWord/" + getRandomUUID())
        );

        mockMvc.perform(
                delete("/User/User/" + userName)
        )
                .andDo(print())
                //.andExpect(status().is(200))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserNotExistTest() throws Exception {
        mockMvc.perform(
                delete("/User/User/" + getRandomUUID())
        )
                .andDo(print())
                //.andExpect(status().is(404))
                .andExpect(status().isNotFound());
    }

}
