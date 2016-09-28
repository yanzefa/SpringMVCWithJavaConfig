package com.github.izhangzhihao.SpringMVCSeedProject.Service;


import com.github.izhangzhihao.SpringMVCSeedProject.Model.Log;
import com.github.izhangzhihao.SpringMVCSeedProject.Repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class LogService extends BaseService<Log> {

    public Long getExceptionCountByCallerFilename(String callerFilename){
        Query query = new Query(entityManager);
        return (Long) query.from(Log.class)
                .whereEqual("caller_filename", callerFilename)
                .selectCount()
                .createTypedQuery()
                .getSingleResult();
    }

    public Long getExceptionCount(){
        Query query = new Query(entityManager);
        return (Long) query.from(Log.class)
                .selectCount()
                .createTypedQuery()
                .getSingleResult();
    }
}
