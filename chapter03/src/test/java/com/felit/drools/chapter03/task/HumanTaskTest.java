package com.felit.drools.chapter03.task;

import bitronix.tm.TransactionManagerServices;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;
import java.util.Collections;

/**
 */
public class HumanTaskTest {
    private KnowledgeBase knowledgeBase;

    @Test
    public void testProcess() {
        int sessionId = 31;
        int processId = 31;
        StatefulKnowledgeSession ksession = this.loadSession(sessionId);
        ProcessInstance processInstance = ksession.getProcessInstance(processId);
        JPAWorkingMemoryDbLogger logger = new JPAWorkingMemoryDbLogger(ksession);
//        ksession.fireAllRules();
        ksession.dispose();
        logger.dispose();
    }

    @Test
    public void testProcessInstance() {
        StatefulKnowledgeSession knowledgeSession = this.loadSession(44);
        for (int i=32;i<=32;i++ ) {
            knowledgeSession.getWorkItemManager().completeWorkItem(i, Collections.<String, Object>emptyMap());
        }
    }


    private StatefulKnowledgeSession newSession() {
        init();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.jbpm.persistence.jpa");
        TransactionManager tm = TransactionManagerServices.getTransactionManager();

        Environment env = KnowledgeBaseFactory.newEnvironment();
        env.set(EnvironmentName.ENTITY_MANAGER_FACTORY, emf);
        env.set(EnvironmentName.TRANSACTION_MANAGER, tm);
        return JPAKnowledgeService.newStatefulKnowledgeSession(knowledgeBase, null, env);
    }

    private StatefulKnowledgeSession loadSession(int sessionId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.jbpm.persistence.jpa");
        TransactionManager tm = TransactionManagerServices.getTransactionManager();

        Environment env = KnowledgeBaseFactory.newEnvironment();
        env.set(EnvironmentName.ENTITY_MANAGER_FACTORY, emf);
        env.set(EnvironmentName.TRANSACTION_MANAGER, tm);
        return JPAKnowledgeService.loadStatefulKnowledgeSession(sessionId, this.knowledgeBase, null, env);
    }

    @BeforeTest
    public void init() {
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(ResourceFactory.newClassPathResource("task-client-server.bpmn"), ResourceType.BPMN2);
        KnowledgeBase kbase = builder.newKnowledgeBase();
        knowledgeBase = kbase;
    }
}
