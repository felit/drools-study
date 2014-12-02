package com.felit.drools.chapter03;

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

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;

/**
 */
public class PersistenceMain {
    private static KnowledgeBase kbase;

    public static void main(String args[]) {
        setup();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.jbpm.persistence.jpa");
        TransactionManager tm = TransactionManagerServices.getTransactionManager();

        Environment env = KnowledgeBaseFactory.newEnvironment();
        env.set(EnvironmentName.ENTITY_MANAGER_FACTORY,emf);
        env.set(EnvironmentName.TRANSACTION_MANAGER,tm);
        kbase.newStatefulKnowledgeSession();
        StatefulKnowledgeSession ksession = JPAKnowledgeService.newStatefulKnowledgeSession(kbase,null,env);
        ksession.startProcess("com.felit.drools.chapter03.SimplePersistenceProcess");
        ksession.fireAllRules();
        ksession.dispose();
    }

    protected static void setup() {
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(ResourceFactory.newClassPathResource("SimplePersistenceProcess.bpmn2"), ResourceType.BPMN2);
        KnowledgeBase knowledgeBase = builder.newKnowledgeBase();
        kbase = knowledgeBase;
    }
}
