package com.felit.drools.chapter07;

import bitronix.tm.TransactionManagerServices;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
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
    private KnowledgeBuilder kbuilder;

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

    protected void addClasspathResource(String uri, ResourceType resourceType) {
        this.kbuilder.add(ResourceFactory.newClassPathResource(uri), resourceType);//与顺序有关
    }

    protected abstract void addResource();

    protected void createKnowledgeBase() {
        this.kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        this.addResource();
        if (this.kbuilder.getErrors().size() > 0) {
            for (KnowledgeBuilderError error : this.kbuilder.getErrors()) {
                System.out.println(error.getResource() + ":" + (error.getLines().length > 0 ? error.getLines()[0] : "null") + "->" + error.getMessage());
            }
        }
        KnowledgeBase kbase = this.kbuilder.newKnowledgeBase();
        this.knowledgeBase = kbase;
    }

    ;

    protected EntityManagerFactory getEntityManagerFactory() {
        if (this.emf == null) {
            this.emf = Persistence.createEntityManagerFactory("org.jbpm.persistence.jpa");
        }
        return this.emf;
    }
}
