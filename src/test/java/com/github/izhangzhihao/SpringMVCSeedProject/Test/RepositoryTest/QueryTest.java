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
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;

@SuppressWarnings({"unchecked", "SpringJavaAutowiredMembersInspection"})
public class QueryTest extends BaseTest {

    @Autowired
    private BaseRepository<User> userRepository;

    @Autowired
    private BaseRepository<Teacher> teacherRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void getAllByQueryTest() {
        Query query = new Query(entityManager);
        query.from(User.class)
                .whereEqual("authorityType", AuthorityType.Admin)
                .whereLike("passWord", "BaseRepository")
                .createTypedQuery()
                .getResultList()
                .forEach(System.out::println);
    }

    @Test
    public void getListByPageAndQueryTest() throws Exception {
        Query query = new Query(entityManager);
        /*TypedQuery typedQuery = query.from(User.class)
                .whereEqual("authorityType", AuthorityType.School_Level_Admin)
				.createTypedQuery();*/
        query.from(User.class)
                .whereEqual("authorityType", AuthorityType.School_Level_Admin);
        PageResults<User> listByPageAndQuery = userRepository.getListByPageAndQuery(2, 3, query);
        System.out.println(listByPageAndQuery);
        List<User> results = listByPageAndQuery.getResults();
        if (!results.isEmpty()) {
            results.forEach(System.out::println);
        }
        assertNotNull(results);
    }


    @Test
    public void getListByPageAndQueryTest2() throws Exception {
        Query query = new Query(entityManager);

		/*TypedQuery typedQuery = query.from(User.class)
				.whereLike("passWord", "BaseRepository")
				.createTypedQuery();*/
        query.from(User.class)
                .whereEqual("authorityType", AuthorityType.School_Level_Admin);

        PageResults<User> listByPageAndQuery = userRepository.getListByPageAndQuery(2, 3, query);
        List<User> results = listByPageAndQuery.getResults();
        System.out.println(listByPageAndQuery);
        if (!results.isEmpty()) {
            results.forEach(System.out::println);
        }
        assertNotNull(results);
    }

    @Test
    public void getCountByQueryTest() {
        Query query = new Query(entityManager);
        /*TypedQuery typedQuery = query.from(User.class)
                .whereEqual("authorityType", AuthorityType.Admin)
				.createTypedQuery();*/
        query.from(User.class)
                .whereEqual("authorityType", AuthorityType.Admin);
        int countByQuery = userRepository.getCountByQuery(query);
        System.out.println(countByQuery);
    }

    @Test
    public void getStatisticsByQueryTest() {
        Query query = new Query(entityManager);
        query.from(User.class)
                .whereNotEqual("authorityType", AuthorityType.Admin)
                .whereIsNotNull("userName")
                .groupBy("passWord")
                .createTypedQuery()
                .getResultList()
                .forEach(System.out::println);

    }

    @Test
    public void getStatisticsByQueryTestSuccess() {
        Query query = new Query(entityManager);
        query.from(User.class)
                .whereNotEqual("authorityType", AuthorityType.Admin)
                .whereEqual("passWord", "BaseRepository")
                .whereIsNotNull("userName")
                .groupBy("passWord")
                .createTypedQuery()
                .getResultList()
                .forEach(System.out::println);

    }

    @Test
    public void getStatisticsByQueryTest2() {
        Query query = new Query(entityManager);
        ParameterExpression parameter = query.createParameter(String.class);
        query.from(Teacher.class)
                .selectMax("id")
                .whereNotEqual("name", parameter)
                .whereIsNotNull("name")
                .groupBy("passWord")
                .createTypedQuery()
                .setParameter(parameter, "name")
                .getResultList()
                .forEach(System.out::println);
    }

    @Test
    public void getStatisticsByQueryTest3() {
        Query query = new Query(entityManager);
        query.from(Teacher.class)
                .selectMax("id")
                .whereNotEqual("name", "name")
                .whereIsNotNull("name")
                .groupBy("passWord")
                .createTypedQuery()
                .getResultList()
                .forEach(System.out::println);
    }

    @Test
    public void getStatisticsByQueryTest4() {
        Query query = new Query(entityManager);
        query.from(User.class)
                .select()
                .whereValueIn("authorityType", asList(AuthorityType.Teacher, AuthorityType.Expert, AuthorityType.College_Level_Admin))
                .createTypedQuery()
                .getResultList()
                .forEach(System.out::println);
    }

    @Test
    public void getStatisticsByQueryTest5() {
        Query query = new Query(entityManager);
        query.from(User.class)
                .select()
                .whereValueNotIn("authorityType", asList(AuthorityType.Teacher, AuthorityType.Expert, AuthorityType.College_Level_Admin))
                .whereIsNotNull("passWord")
                .whereNotEqual("passWord", "BaseRepository")
                .whereNotEqual("userName", "test")
                .createTypedQuery()
                .getResultList()
                .forEach(System.out::println);
    }

    @Test
    public void getStatisticsByQueryTest6() {
        Query query = new Query(entityManager);
        query.from(User.class)
                .selectCount()
                .whereValueNotIn("authorityType", asList(AuthorityType.Teacher, AuthorityType.Expert, AuthorityType.College_Level_Admin))
                .whereIsNotNull("passWord")
                .whereNotEqual("passWord", "BaseRepository")
                .whereNotEqual("userName", "test")
                .createTypedQuery()
                .getResultList()
                .forEach(System.out::println);
    }
}
