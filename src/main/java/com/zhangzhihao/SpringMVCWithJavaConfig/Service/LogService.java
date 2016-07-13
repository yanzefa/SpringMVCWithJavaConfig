package com.zhangzhihao.SpringMVCWithJavaConfig.Service;


import com.zhangzhihao.SpringMVCWithJavaConfig.Dao.Query;
import com.zhangzhihao.SpringMVCWithJavaConfig.Model.Log;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class LogService extends BaseService<Log> {

    @PersistenceContext
    private EntityManager entityManager;

    public Long getExceptionCountByCaller_filename(String caller_filename) {
        Query query = new Query(entityManager);
        return (Long) query.from(Log.class)
                .whereEqual("caller_filename", caller_filename)
                .selectCount()
                .createTypedQuery()
                .getSingleResult();
    }

    public Long getExceptionCount() {
        Query query = new Query(entityManager);
        return (Long) query.from(Log.class)
                .selectCount()
                .createTypedQuery()
                .getSingleResult();
    }

    /*public List<Log> getListByPage(){

    }*/
}
