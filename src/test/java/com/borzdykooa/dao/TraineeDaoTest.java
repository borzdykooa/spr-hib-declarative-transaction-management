package com.borzdykooa.dao;

import com.borzdykooa.config.ApplicationConfiguration;
import com.borzdykooa.entity.Trainee;
import com.borzdykooa.entity.Trainer;
import com.borzdykooa.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/*
Класс для тестирования методов TraineeDao
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@Transactional
public class TraineeDaoTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private TestDataImporter testDataImporter;

    @Autowired
    private TraineeDao traineeDao;

    @Before
    public void initDb() {
        testDataImporter.deleteTestData();
        testDataImporter.importTestData();
    }

    @Test
    public void testFindAll() {
        List<Trainee> trainees = traineeDao.findAll();
        assertEquals(2, trainees.size());
    }


    @Test
    public void testSave() {
        Session session = sessionFactory.getCurrentSession();
        Trainer trainer = session.createQuery("select t from Trainer t where t.name like 'Andrei Reut'", Trainer.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(trainer);

        Trainee alexandrAlexandrov = new Trainee("Alexandr Alexandrov", trainer);
        Long id = traineeDao.save(alexandrAlexandrov);
        assertNotNull("Entity is not saved", id);
    }
}
