package com.felit.drools.chapter03.task;

import org.drools.SystemEventListenerFactory;
import org.jbpm.task.Group;
import org.jbpm.task.User;
import org.jbpm.task.UserInfo;
import org.jbpm.task.admin.TasksAdmin;
import org.jbpm.task.identity.UserGroupCallbackManager;
import org.jbpm.task.service.DefaultUserInfo;
import org.jbpm.task.service.TaskService;
import org.jbpm.task.service.TaskServiceSession;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 */
public class TaskServiceMain {
    public static final String JBPM_USERGROUP_CALLBACK = "jbpm.usergroup.callback";
    public static void main(String args[]) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.jbpm.persistence.jpa");
        TaskService taskService = new TaskService(emf, SystemEventListenerFactory.getSystemEventListener());
        TaskServiceSession session = taskService.createSession();
        User user = new User();
        user.setId("jbpm-test2");
        session.addUser(user);
        addUserAndGroups(session);
        session.dispose();
    }

    private static void addUserAndGroups(TaskServiceSession serviceSession) {
        User user = new User();
        user.setId("Administrator");
        serviceSession.addUser(user);
        Group group = new Group();
        group.setId("admin");
        serviceSession.addGroup(group);

    }
}
