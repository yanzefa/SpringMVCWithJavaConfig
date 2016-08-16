package com.github.izhangzhihao.SpringMVCSeedProject.Test.ServiceTest;

import com.github.izhangzhihao.SpringMVCSeedProject.Model.User;
import com.github.izhangzhihao.SpringMVCSeedProject.Service.UserService;
import com.github.izhangzhihao.SpringMVCSeedProject.Test.TestUtils.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.github.izhangzhihao.SpringMVCSeedProject.Service.UserService.makeSHA256PasswordWithSalt;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class UserServiceTest extends BaseTest {
    @Autowired
    private UserService service;

    /**
     * 对makeSHA256PasswordWithSalt的单元测试
     */
    @Test
    public void makeSHA256PasswordWithSaltTest() {
        User user = new User("admin", "admin");
        User SHAuser = makeSHA256PasswordWithSalt(user);
        assertEquals(SHAuser.getPassWord(), "9cf3e758a497c6274bd066d0b2168432f8a34aad95f63a65677a9a56acec94a7");
    }

    /**
     * 对makeSHA256PasswordWithSalt的单元测试
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = Exception.class)
    public void makeSHA256PasswordWithSaltNullTest() {
        makeSHA256PasswordWithSalt(null);
    }

    /**
     * 对save的单元测试
     */
    @Test
    public void saveNotExistTest() throws Exception {
        service.save(getRandomUser());
    }

    /**
     * 对save的单元测试
     */
    @Test
    public void saveExistTest() throws Exception {
        User randomUser = getRandomUser();
        service.save(randomUser);
        service.save(randomUser);
    }

    /**
     * 对save的单元测试
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = Exception.class)
    public void saveNullTest() throws Exception {
        service.save(null);
    }

    /**
     * 对saveAll的单元测试
     */
    @Test
    public void saveAllNotExistTest() throws Exception {
        List<User> userList = asList(getRandomUser(), getRandomUser(), getRandomUser());
        service.saveAll(userList);
    }

    /**
     * 对saveAll的单元测试
     */
    @Test
    public void saveAllExistTest() throws Exception {
        List<User> userList = asList(getRandomUser(), getRandomUser(), getRandomUser());
        service.saveAll(userList);
        service.saveAll(userList);
    }

    /**
     * 对saveAll的单元测试
     */
    @Test
    public void saveAllNullListTest() throws Exception {
        List<User> userList = Collections.emptyList();
        service.saveAll(userList);
    }

    /**
     * 对saveAll的单元测试
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = Exception.class)
    public void saveAllNullTest() throws Exception {
        service.saveAll(null);
    }

    /**
     * 对updatePassWord的单元测试
     */
    @Test
    public void updatePassWordExistTest() throws Exception {
        User randomUser = getRandomUser();
        service.save(randomUser);
        randomUser.setPassWord("passWord");
        service.updatePassWord(randomUser);
    }

    /**
     * 对updatePassWord的单元测试
     */
    @Test
    public void updatePassWordNotExistTest() throws Exception {
        User randomUser = getRandomUser();
        randomUser.setPassWord("passWord");
        service.updatePassWord(randomUser);
    }

    /**
     * 对updatePassWord的单元测试
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = Exception.class)
    public void updatePassWordNullTest() throws Exception {
        service.updatePassWord(null);
    }

    /**
     * 对updateAllPassWord的单元测试
     */
    @Test
    public void updateAllPassWordNotExistTest() throws Exception {
        List<User> userList = asList(getRandomUser(), getRandomUser(), getRandomUser());
        userList.forEach(item -> item.setPassWord("passWord"));
        service.updateAllPassWord(userList);
    }

    /**
     * 对updateAllPassWord的单元测试
     */
    @Test
    public void updateAllPassWordExistTest() throws Exception {
        List<User> userList = asList(getRandomUser(), getRandomUser(), getRandomUser());
        service.saveAll(userList);
        userList.forEach(item -> item.setPassWord("passWord"));
        service.updateAllPassWord(userList);
    }

    /**
     * 对updateAllPassWord的单元测试
     */
    @Test
    public void updateAllPassWordNullListTest() throws Exception {
        service.updateAllPassWord(Collections.emptyList());
    }

    /**
     * 对updateAllPassWord的单元测试
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = Exception.class)
    public void updateAllPassWordNullTest() throws Exception {
        service.updateAllPassWord(null);
    }

    /**
     * 对saveOrUpdate的单元测试
     */
    @Test
    public void saveOrUpdate_SaveTest() throws Exception {
        User user = getRandomUser();
        service.saveOrUpdate(user);
    }

    /**
     * 对saveOrUpdate的单元测试
     */
    @Test
    public void saveOrUpdate_UpdateTest() throws Exception {
        User user = getRandomUser();
        service.save(user);
        user.setPassWord("changed!");
        service.saveOrUpdate(user);
    }

    /**
     * 对saveOrUpdate的单元测试
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = Exception.class)
    public void saveOrUpdate_NullTest() throws Exception {
        service.saveOrUpdate(null);
    }

    /**
     * 对saveOrUpdateAll的单元测试
     */
    @Test
    public void saveOrUpdateAll_SaveTest() throws Exception {
        List<User> userList = asList(getRandomUser()
                , getRandomUser()
                , getRandomUser()
        );
        service.saveOrUpdateAll(userList);
    }

    /**
     * 对saveOrUpdateAll的单元测试
     */
    @Test
    public void saveOrUpdateAll_UpdateTest() throws Exception {
        List<User> userList = asList(getRandomUser()
                , getRandomUser()
                , getRandomUser()
        );
        service.saveAll(userList);
        service.saveOrUpdateAll(userList);
    }

    /**
     * 对saveOrUpdateAll的单元测试
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = Exception.class)
    public void saveOrUpdateAll_NullTest() throws Exception {
        service.saveOrUpdateAll(null);
    }

    /**
     * 对saveOrUpdateAll的单元测试
     */
    @Test
    public void saveOrUpdateAll_NullListTest() throws Exception {
        service.saveOrUpdateAll(new ArrayList<>());
    }
}
