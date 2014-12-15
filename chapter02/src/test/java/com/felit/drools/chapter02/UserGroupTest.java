package com.felit.drools.chapter02;

import org.drools.SystemEventListenerFactory;
import org.jbpm.task.service.TaskService;
import org.jbpm.task.service.TaskServiceSession;
import org.testng.annotations.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 */
public class UserGroupTest {
    @Test
    public void testTaskSession() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.jbpm.persistence.jpa");
        TaskService taskService = new TaskService(emf, SystemEventListenerFactory.getSystemEventListener());
        TaskServiceSession taskSession = taskService.createSession();
//        taskSession.getTaskPersistenceManager().userExists();
    }
}
