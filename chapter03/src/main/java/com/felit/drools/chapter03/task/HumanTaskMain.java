package com.felit.drools.chapter03.task;

import bitronix.tm.TransactionManagerServices;
import com.felit.drools.chapter03.Constants;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.SystemEventListenerFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.persistence.jpa.JPAKnowledgeService;
import org.drools.runtime.Environment;
import org.drools.runtime.EnvironmentName;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.ProcessInstance;
import org.jbpm.process.audit.JPAWorkingMemoryDbLogger;
import org.jbpm.process.audit.WorkingMemoryDbLogger;
import org.jbpm.process.workitem.wsht.LocalHTWorkItemHandler;
import org.jbpm.task.service.TaskService;
import org.jbpm.task.service.local.LocalTaskService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;

/**
 */
public class HumanTaskMain {
    private static KnowledgeBase knowledgeBase;

    public static void main(String args[]) {
        StatefulKnowledgeSession knowledgeSession = getSession();
        TaskService ts = new TaskService(Persistence.createEntityManagerFactory(Constants.JPA_PERSISTENCE_UNIT), SystemEventListenerFactory.getSystemEventListener());
        LocalTaskService taskService = new LocalTaskService(ts);
        LocalHTWorkItemHandler taskHandler = new LocalHTWorkItemHandler(taskService, knowledgeSession);
        knowledgeSession.getWorkItemManager().registerWorkItemHandler("Human Task", taskHandler);
        ProcessInstance instance = knowledgeSession.createProcessInstance("com.felit.drools.chapter03.humanTask",null);
        JPAWorkingMemoryDbLogger logger = new JPAWorkingMemoryDbLogger(knowledgeSession);
        knowledgeSession.startProcessInstance(instance.getId());
        knowledgeSession.dispose();
        logger.dispose();

    }

    private static StatefulKnowledgeSession getSession(){
        init();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.jbpm.persistence.jpa");
        TransactionManager tm = TransactionManagerServices.getTransactionManager();

        Environment env = KnowledgeBaseFactory.newEnvironment();
        env.set(EnvironmentName.ENTITY_MANAGER_FACTORY,emf);
        env.set(EnvironmentName.TRANSACTION_MANAGER,tm);
        return JPAKnowledgeService.newStatefulKnowledgeSession(knowledgeBase, null, env);
    }

    private static void init() {
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(ResourceFactory.newClassPathResource("task-client-server.bpmn"), ResourceType.BPMN2);
        KnowledgeBase kbase = builder.newKnowledgeBase();
        knowledgeBase = kbase;
    }
}
