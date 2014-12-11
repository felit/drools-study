package com.felit.drools.chapter03.task;

import org.drools.SystemEventListenerFactory;
import org.jbpm.task.Task;
import org.jbpm.task.query.TaskSummary;
import org.jbpm.task.service.TaskService;
import org.jbpm.task.service.TaskServiceSession;
import org.jbpm.task.service.local.LocalTaskService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 */
public class TaskClientMain {
    public static void main(String args[]) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.jbpm.persistence.jpa");
        TaskService taskService = new TaskService(emf, SystemEventListenerFactory.getSystemEventListener());
        TaskServiceSession tsession = taskService.createSession();

        List<TaskSummary> tasks = tsession.getTasksAssignedAsPotentialOwner("jbpm-test2", "en-UK");
//        tasks = tsession.getTasksAssignedAsBusinessAdministrator("Administrator", "en-UK");
        LocalTaskService localTaskService = new LocalTaskService(taskService);

        for (TaskSummary taskSummary : tasks) {
            System.out.println(taskSummary);
//            localTaskService.start(taskSummary.getId(), "jbpm-test2");
//            localTaskService.claim(taskSummary.getId(), "jbpm-test2");
            localTaskService.complete(taskSummary.getId(), "jbpm-test2",null);
//            localTaskService.delegate(taskSummary.getId(),"Administrator","jbpm-test2");
        }
    }
}
