package com.felit.drools.chapter03.task;

import bitronix.tm.TransactionManagerServices;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.persistence.jpa.JPAKnowledgeService;
import org.drools.runtime.Environment;
import org.drools.runtime.EnvironmentName;
import org.drools.runtime.StatefulKnowledgeSession;
import org.testng.annotations.BeforeTest;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;

/**
 *
 */
public abstract class BasicTest {
    protected KnowledgeBase knowledgeBase;
    protected EntityManagerFactory emf;

    @BeforeTest
    protected void init() {
        this.createKnowledgeBase();
        this.getEntityManagerFactory();
    }
    protected StatefulKnowledgeSession newSession() {


        TransactionManager tm = TransactionManagerServices.getTransactionManager();

        Environment env = KnowledgeBaseFactory.newEnvironment();
        env.set(EnvironmentName.ENTITY_MANAGER_FACTORY, this.getEntityManagerFactory());
        env.set(EnvironmentName.TRANSACTION_MANAGER, tm);

        return JPAKnowledgeService.newStatefulKnowledgeSession(knowledgeBase, null, env);
    }

    protected StatefulKnowledgeSession loadSession(int sessionId) {
        TransactionManager tm = TransactionManagerServices.getTransactionManager();
        Environment env = KnowledgeBaseFactory.newEnvironment();
        env.set(EnvironmentName.ENTITY_MANAGER_FACTORY, this.getEntityManagerFactory());
        env.set(EnvironmentName.TRANSACTION_MANAGER, tm);
        return JPAKnowledgeService.loadStatefulKnowledgeSession(sessionId, this.knowledgeBase, null, env);
    }

    protected abstract void createKnowledgeBase();
    protected  EntityManagerFactory getEntityManagerFactory() {
        if (this.emf == null) {
            this.emf = Persistence.createEntityManagerFactory("org.jbpm.persistence.jpa");
        }
        return this.emf;
    }
}
