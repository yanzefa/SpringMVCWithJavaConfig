package com.github.izhangzhihao.SpringMVCSeedProject.Test.RepositoryTest;


import com.github.izhangzhihao.SpringMVCSeedProject.Annotation.AuthorityType;
import com.github.izhangzhihao.SpringMVCSeedProject.Repository.BaseRepository;
import com.github.izhangzhihao.SpringMVCSeedProject.Repository.Query;
import com.github.izhangzhihao.SpringMVCSeedProject.Model.Teacher;
import com.github.izhangzhihao.SpringMVCSeedProject.Model.User;
import com.github.izhangzhihao.SpringMVCSeedProject.Test.TestUtils.BaseTest;
import com.github.izhangzhihao.SpringMVCSeedProject.Utils.PageResults;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.ParameterExpression;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.github.izhangzhihao.SpringMVCSeedProject.Utils.StringUtils.getRandomUUID;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;


@SuppressWarnings({"unchecked", "SpringJavaAutowiredMembersInspection"})
public class BaseRepositoryTest extends BaseTest {

    @Autowired
    private BaseRepository<User> userRepository;

    @Autowired
    private BaseRepository<Teacher> teacherRepository;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 对contains的单元测试
     */
    @Test
    public void containsExistTest() {
        User user = getRandomUser();
        userRepository.save(user);
        assertTrue(userRepository.contains(user));
    }

    /**
     * 对contains的单元测试
     */
    @Test
    public void containsNotExistTest() {
        User user = getRandomUser();
        assertFalse(userRepository.contains(user));
    }

    /**
     * 对contains的单元测试 莫名其妙，用gradle能跑过的话idea内置的测试跑不过，idea内置测试跑得过的话gradle跑不过
     */
    /*@SuppressWarnings("ConstantConditions")
    @Test
    public void containsNullTest() {
        assertFalse(userRepository.contains(null));
    }*/

    /**
     * 对detach的单元测试
     */
    @Test
    public void detachExistTest() {
        User user = getRandomUser();
        userRepository.save(user);
        userRepository.detach(user);
        boolean contains = userRepository.contains(user);
        assertFalse(contains);
    }

    /**
     * 对detach的单元测试
     */
    @Test
    public void detachNotExistTest() {
        User user = getRandomUser();
        userRepository.detach(user);
    }

    /**
     * 对detach的单元测试
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = Exception.class)
    public void detachNullTest() {
        userRepository.detach(null);
    }

    /**
     * 对save的单元测试
     */
    @Test
    public void saveNewTest() {
        userRepository.save(getRandomUser());
    }

    /**
     * 对save的单元测试
     */
    @Test
    public void saveExistTest() {
        final User user = getRandomUser();
        userRepository.save(user);
        userRepository.save(user);
    }

    /**
     * 对save的单元测试
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = Exception.class)
    public void saveNullTest() {
        userRepository.save(null);
    }

    /**
     * 对saveAll的单元测试
     */
    @Test
    public void saveAllNewTest() {
        List<User> userList = asList(getRandomUser()
                , getRandomUser()
                , getRandomUser()
        );
        userRepository.saveAll(userList);
    }

    /**
     * 对saveAll的单元测试
     */
    @Test
    public void saveAllExistTest() {
        List<User> userList = asList(getRandomUser()
                , getRandomUser()
                , getRandomUser()
        );
        userRepository.saveAll(userList);
        userRepository.saveAll(userList);
    }

    /**
     * 对saveAll的单元测试
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = Exception.class)
    public void saveAllNullTest() {
        userRepository.saveAll(null);
    }

    /**
     * 对delete的单元测试
     */
    @Test
    public void deleteExistTest() {
        User user = getRandomUser();
        userRepository.save(user);
        userRepository.delete(user);
    }

    /**
     * 对delete的单元测试
     */
    @Test
    public void deleteNotExistTest() {
        userRepository.delete(getRandomUser());
    }

    /**
     * 对delete的单元测试
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = Exception.class)
    public void deleteNullTest() {
        userRepository.delete(null);
    }

    /**
     * 对delete的单元测试
     */
    @Test
    public void deleteAllExistTest() {
        List<User> userList = asList(getRandomUser()
                , getRandomUser()
                , getRandomUser()
        );
        userRepository.saveAll(userList);
        userRepository.deleteAll(userList);
    }

    /**
     * 对delete的单元测试
     */
    @Test
    public void deleteAllNotExistTest() {
        List<User> userList = asList(getRandomUser()
                , getRandomUser()
                , getRandomUser()
        );
        userRepository.deleteAll(userList);
    }

    /**
     * 对delete的单元测试
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = Exception.class)
    public void deleteAllNullTest() {
        userRepository.deleteAll(null);
    }

    /**
     * 对deleteById的单元测试
     */
    public void deleteByExistIdTest() {
        User user = getRandomUser();
        userRepository.save(user);
        userRepository.deleteById(User.class, user.getUserName());
    }

    /**
     * 对deleteById的单元测试
     */
    public void deleteByNotExistIdTest() {
        userRepository.deleteById(User.class, getRandomUUID());
    }

    /**
     * 对deleteById的单元测试
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = Exception.class)
    public void deleteByNullIdTest() {
        userRepository.deleteById(User.class, null);
    }

    /**
     * 对saveOrUpdate的单元测试
     */
    @Test
    public void saveOrUpdate_SaveTest() {
        User user = getRandomUser();
        userRepository.saveOrUpdate(user);
    }

    /**
     * 对saveOrUpdate的单元测试
     */
    @Test
    public void saveOrUpdate_UpdateTest() {
        User user = getRandomUser();
        userRepository.save(user);
        user.setPassWord("changed!");
        userRepository.saveOrUpdate(user);
    }

    /**
     * 对saveOrUpdate的单元测试
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = Exception.class)
    public void saveOrUpdate_NullTest() {
        userRepository.saveOrUpdate(null);
    }

    /**
     * 对saveOrUpdateAll的单元测试
     */
    @Test
    public void saveOrUpdateAll_SaveTest() {
        List<User> userList = asList(getRandomUser()
                , getRandomUser()
                , getRandomUser()
        );
        userRepository.saveOrUpdateAll(userList);
    }

    /**
     * 对saveOrUpdateAll的单元测试
     */
    @Test
    public void saveOrUpdateAll_UpdateTest() {
        List<User> userList = asList(getRandomUser()
                , getRandomUser()
                , getRandomUser()
        );
        userRepository.saveAll(userList);
        userRepository.saveOrUpdateAll(userList);
    }

    /**
     * 对saveOrUpdateAll的单元测试
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = Exception.class)
    public void saveOrUpdateAll_NullTest() {
        userRepository.saveOrUpdateAll(null);
    }

    /**
     * 对saveOrUpdateAll的单元测试
     */
    @Test
    public void saveOrUpdateAll_NullListTest() {
        userRepository.saveOrUpdateAll(new ArrayList<>());
    }

    /**
     * 对getById的单元测试
     */
    @Test
    public void getByExistIntegerIdTest() {
        Teacher teacher = new Teacher("name", "password");
        teacherRepository.save(teacher);
        assertEquals(teacher.getId(), teacherRepository.getById(Teacher.class, teacher.getId()).getId());
    }

    /**
     * 对getById的单元测试
     */
    @Test
    public void getByNotExistIntegerIdTest() {
        Teacher byId = teacherRepository.getById(Teacher.class, new Random().nextInt());
        assertNull(byId);
    }

    /**
     * 对getById的单元测试
     */
    @Test
    public void getByExistStringIdTest() {
        User admin = getRandomUser();
        userRepository.save(admin);
        User byId = userRepository.getById(User.class, admin.getUserName());
        assertEquals(byId, admin);
    }

    /**
     * 对getById的单元测试
     */
    @Test
    public void getByNotExistStringIdTest() {
        User byId = userRepository.getById(User.class, getRandomUUID());
        assertNull(byId);
    }

    /**
     * 对getAll的单元测试
     */
    @Test
    public void getAllTest() {
        List<User> userList = userRepository.getAll(User.class);
        System.out.println(userList);
        if (userList.size() > 0) {
            userList.forEach(System.out::println);
        }
        assertNotNull(userList);
    }

    /**
     * 对getCount的单元测试
     */
    @Test
    public void getCountTest() {
        int count = userRepository.getCount(User.class);
        System.out.println(count);
    }

    /**
     * 对getListByPage的单元测试
     */
    @Test
    public void getListByPageTest() {
        PageResults<User> userPageResults = userRepository.getListByPage(User.class, -7, 2);
        List<User> listByPage = userPageResults.getResults();
        if (listByPage.size() > 0) {
            listByPage.forEach(System.out::println);
        }
        System.out.println(userPageResults);
        assertNotNull(listByPage);
    }

    /**
     * 对getListByPageAndQuery的单元测试
     */
    @Test
    public void getListByPageAndQueryTest() throws Exception {
        Query query = new Query(entityManager);
        query.from(User.class)
                .whereEqual("userName", "admin");
        PageResults<User> listByPageAndQuery = userRepository.getListByPageAndQuery(1, 3, query);
        System.out.println(listByPageAndQuery);
        List<User> results = listByPageAndQuery.getResults();
        if (!results.isEmpty()) {
            results.forEach(System.out::println);
        }
        assertNotNull(results);
    }

    /**
     * 对getListByPageAndQuery的单元测试
     */
    @Test
    public void getListByPageAndQueryTest2() throws Exception {
        Query query = new Query(entityManager);

        query.from(User.class)
                .whereIsNotNull("userName");

        PageResults<User> listByPageAndQuery = userRepository.getListByPageAndQuery(2, 5, query);
        List<User> results = listByPageAndQuery.getResults();
        System.out.println(listByPageAndQuery);
        assertNotNull(results);
    }

    /**
     * 对getPageResultsByQuery的单元测试
     */
    @Test
    public void getPageResultsByQueryTest() throws Exception {
        Query query = new Query(entityManager);
        query.from(User.class)
                .whereEqual("userName", "admin");
        PageResults<User> listByPageAndQuery = userRepository.getListByPageAndQuery(1, 5, query);
        List<User> results = listByPageAndQuery.getResults();
        assertNotNull(results);
    }


    /**
     * 对getAllByQuery的单元测试
     */
    @Test
    public void getAllByQueryTest() {
        Query query = new Query(User.class, entityManager);
        ParameterExpression<Enum> parameter1 = query.createParameter(Enum.class);
        ParameterExpression<String> parameter2 = query.createParameter(String.class);
        List resultList = query.whereEqual("authorityType", parameter1)
                .whereLike("passWord", parameter2)
                .createTypedQuery()
                .setParameter(parameter1, AuthorityType.College_Level_Admin)
                .setParameter(parameter2, "BaseRepository")
                .getResultList();
        if (resultList != null) {
            resultList.forEach(System.out::println);
        }
    }


    /**
     * 对getCountByQuery的单元测试
     */
    @Test
    public void getCountByQueryTest() {
        Query query = new Query(entityManager);
        query.from(User.class)
                .whereEqual("authorityType", AuthorityType.College_Level_Admin);
        int countByQuery = userRepository.getCountByQuery(query);
        System.out.println(countByQuery);
    }

    /**
     * 对executeSql的单元测试
     */
    @Test
    public void executeSqlTest() {
        String sql = "insert into Teacher (id,name,password) values (?,?,?)";
        Random random = new Random();
        int i = teacherRepository.executeSql(sql, random.nextInt(), "admin", "admin");//这里数据库名要和大小写一致！！！
        assertEquals(1, i);
    }

    /**
     * 对queryByJpql的单元测试
     */
    @Test
    public void queryByJpqlTest() {
        String jpql = "select o from User o where o.passWord = ?0 ";
        PageResults<Object> results = userRepository.getListByPageAndJpql(2, 5, jpql, "BaseRepository");
        results.getResults().forEach(System.out::println);
    }

    /**
     * 对queryByJpql的单元测试
     */
    @Test
    public void queryByJpqlTest2() {
        String jpql = "select o from User o where 1=1 ";
        PageResults<Object> results = userRepository.getListByPageAndJpql(2, 5, jpql);
        results.getResults().forEach(System.out::println);
    }

    /**
     * 对getCountByJpql的单元测试
     */
    @Test
    public void getCountByJpqlTest() {
        String jpql = "select COUNT(o) from User o where o.userName = ?0 ";
        int result = userRepository.getCountByJpql(jpql, "admin");
        assertEquals(result, 1);
    }

    /**
     * 对getListByPageAndJpql的单元测试
     */
    @Test
    public void getListByPageAndJpqlTest() {
        String jpql = "select o from User o where o.userName = ?0 ";
        PageResults<Object> pageResults = userRepository.getListByPageAndJpql(1, 5, jpql, "admin");
        assertNotNull(pageResults);
    }

    /**
     * 对executeJpql的单元测试
     */
    @Test
    public void executeJpqlTest() {
        String jpql = "update User u set u.passWord=?0 where u.userName = ?1 ";
        int result = userRepository.executeJpql(jpql, "admin", "admin");
        assertEquals(result, 1);
    }

    /**
     * 对refresh的单元测试
     */
    @Test(expected = Exception.class)
    public void refreshExistTest() {
        User randomUser = getRandomUser();
        userRepository.save(randomUser);
        userRepository.refresh(randomUser);
    }

    /**
     * 对refresh的单元测试
     */
    @Test(expected = Exception.class)
    public void refreshNotExistTest() {
        userRepository.refresh(getRandomUser());
    }

    /**
     * 对flush的单元测试
     */
    @Test
    public void flushTest() {
        userRepository.flush();
    }
}
