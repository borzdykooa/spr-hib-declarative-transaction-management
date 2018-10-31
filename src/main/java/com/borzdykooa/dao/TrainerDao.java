package com.borzdykooa.dao;

import com.borzdykooa.entity.Trainer;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/*
Класс, содержащий метод save и find для таблицы trainer
 */
@Repository
@Transactional
public class TrainerDao {

    private static final Logger log = Logger.getLogger(TrainerDao.class);

    private SessionFactory sessionFactory;

    @Autowired
    public TrainerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Trainer find(Long id) {
        log.info("Method find is called in TrainerDao");
        Trainer trainer = sessionFactory.getCurrentSession().find(Trainer.class, id);
        if (trainer != null) {
            log.info(trainer.toString() + " has been found");
        }
        return trainer;
    }

    public Long save(Trainer trainer) {
        log.info("Method save is called in TrainerDao");
        Serializable id = sessionFactory.getCurrentSession().save(trainer);
        if (id != null) {
            log.info(trainer.toString() + " has been saved successfully!");
        }
        return (Long) id;
    }
}
