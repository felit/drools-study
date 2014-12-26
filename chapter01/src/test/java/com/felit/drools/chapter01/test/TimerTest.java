package com.felit.drools.chapter01.test;

import com.felit.drools.chapter01.model.EntityStatus;
import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;
import org.testng.annotations.Test;

/**
 */
public class TimerTest extends TestBasic {
    @Test
    public void testTimer() throws InterruptedException {
        this.knowledgeBase = this.getKnowledgeBase();
        StatefulKnowledgeSession ksession = this.knowledgeBase.newStatefulKnowledgeSession();
        EntityStatus entityStatus = new EntityStatus();
        entityStatus.setTitle("entity status timer");
        entityStatus.setStatus(EntityStatus.Status.AVAILABLE);
        entityStatus.setCount(0);

        ksession.insert(entityStatus);
        ksession.fireUntilHalt();
        ksession.dispose();
        System.out.println("ksession");
    }

    public KnowledgeBase getKnowledgeBase() {
        KnowledgeBuilder knowledgeBuilder = this.getKnowledgeBuilder();
        knowledgeBuilder.add(new ClassPathResource("chapter01-timer.drl"), ResourceType.DRL);
        return knowledgeBuilder.newKnowledgeBase();
    }
}
