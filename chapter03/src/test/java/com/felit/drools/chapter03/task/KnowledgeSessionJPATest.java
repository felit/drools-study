package com.felit.drools.chapter03.task;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.persistence.info.SessionInfo;
import org.drools.runtime.StatefulKnowledgeSession;
import org.testng.annotations.Test;

import java.util.List;

/**
 *
 */
public class KnowledgeSessionJPATest extends BasicTest {
    @Test
    public void test() {
        List<SessionInfo> ksessions = this.emf.createEntityManager().createQuery("From SessionInfo").getResultList();
        for (SessionInfo sessionInfo : ksessions) {
            StatefulKnowledgeSession ksession = this.loadSession(sessionInfo.getId());
                System.out.println(ksession.getId());
            for (Object object : ksession.getObjects()) {
                System.out.println(ksession.getId());
                System.out.println(object);

            }
        }
    }


    @Test
    public void testKsession() {
        StatefulKnowledgeSession ksession = this.newSession();
        ksession.insert("test ksession");
        System.out.println("ksession.getId():" + ksession.getId());
    }


    @Override
    protected void createKnowledgeBase() {
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(ResourceFactory.newClassPathResource("task-client-server.bpmn"), ResourceType.BPMN2);
        KnowledgeBase kbase = builder.newKnowledgeBase();
        knowledgeBase = kbase;
    }
}
