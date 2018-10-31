package com.borzdykooa.dao;

import com.borzdykooa.entity.Trainee;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/*
Класс, содержащий методы save и findAll для таблицы trainee
 */
@Repository
@Transactional
public class TraineeDao {

    private static final Logger log = Logger.getLogger(TraineeDao.class);

    private SessionFactory sessionFactory;

    @Autowired
    public TraineeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Long save(Trainee trainee) {
        log.info("Method save is called in TraineeDao");
        Serializable id = sessionFactory.getCurrentSession().save(trainee);
        if (id != null) {
            log.info(trainee.toString() + " has been saved successfully!");
        }return (Long) id;
    }

    public List<Trainee> findAll() {
        log.info("Method findAll is called in TraineeDao");
        List<Trainee> list = sessionFactory.getCurrentSession()
                .createQuery("select t from Trainee t", Trainee.class).list();
        if (list.size() > 0) {
            log.info("List of Trainees: " + list.toString());
        } else {
            log.info("List of Trainees is empty");
        }
        return list;
    }
}
