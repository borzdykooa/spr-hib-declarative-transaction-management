package com.borzdykooa.dao;

import com.borzdykooa.config.ApplicationConfiguration;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/*
Класс для тестирования методов TrainerDao
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@Transactional
public class TrainerDaoTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private TestDataImporter testDataImporter;

    @Autowired
    private TrainerDao trainerDao;

    @Before
    public void initDb() {
        testDataImporter.deleteTestData();
        testDataImporter.importTestData();
    }

    @Test
    public void testFind() {
        Session session = sessionFactory.getCurrentSession();
        Trainer trainer = session.createQuery("select t from Trainer t ", Trainer.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(trainer);
        String name = trainer.getName();

        Trainer theSameTrainer = trainerDao.find(trainer.getId());
        String theSameName = theSameTrainer.getName();
        assertEquals(name, theSameName);
    }

    @Test
    public void testSave() {
        Trainer nikolaiNikolaev = new Trainer("Nikolai Nikolaev", "C", 12);
        Long id = trainerDao.save(nikolaiNikolaev);
        assertNotNull("Entity is not saved", id);
    }
}
