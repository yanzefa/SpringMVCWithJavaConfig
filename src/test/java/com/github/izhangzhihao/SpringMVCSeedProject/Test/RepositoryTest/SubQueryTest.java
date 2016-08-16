package com.github.izhangzhihao.SpringMVCSeedProject.Test.RepositoryTest;


import com.github.izhangzhihao.SpringMVCSeedProject.Model.BankCard;
import com.github.izhangzhihao.SpringMVCSeedProject.Model.Teacher;
import com.github.izhangzhihao.SpringMVCSeedProject.Model.User;
import com.github.izhangzhihao.SpringMVCSeedProject.Repository.BaseRepository;
import com.github.izhangzhihao.SpringMVCSeedProject.Repository.Query;
import com.github.izhangzhihao.SpringMVCSeedProject.Test.TestUtils.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

@SuppressWarnings({"unchecked", "SpringJavaAutowiredMembersInspection"})
public class SubQueryTest extends BaseTest {
    @Autowired
    private BaseRepository<User> userRepository;

    @Autowired
    private BaseRepository<Teacher> teacherRepository;

    @Autowired
    private BaseRepository<BankCard> bankCardRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void NorMalSubQuery() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        Subquery<User> subQuery = criteriaQuery.subquery(User.class);
        Root<User> subRoot = subQuery.from(User.class);

        subQuery.where(criteriaBuilder.equal(subRoot.get("userName"), "admin")).select(subRoot.get("userName"));//
        //criteriaQuery.where(criteriaBuilder.exists(subquery));
        criteriaQuery.where(criteriaBuilder.equal(root.get("passWord"), subQuery));
        //CriteriaQuery<User> passWord = criteriaQuery.select(root.get("passWord")).distinct(true);

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        query.getResultList().forEach(System.out::println);
    }

    @Test
    public void SubQueryTest() {
        Query query = new Query(User.class, entityManager);
        Query subQuery = query.subQuery(User.class);
        subQuery.whereEqual("userName", "admin");
        User singleResult = (User) subQuery.createTypedQuery().getSingleResult();
        query.whereEqual("passWord", singleResult.getUserName()).createTypedQuery().getResultList().forEach(System.out::println);
    }


}
