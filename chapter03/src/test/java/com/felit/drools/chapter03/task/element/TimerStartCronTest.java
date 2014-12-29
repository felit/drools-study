package com.felit.drools.chapter03.task.element;

import com.felit.drools.chapter03.task.BasicTest;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;
import org.jbpm.process.audit.JPAWorkingMemoryDbLogger;
import org.testng.annotations.Test;

/**
 */
public class TimerStartCronTest extends BasicTest {
    @Test
    public void test() throws InterruptedException {
        StatefulKnowledgeSession ksession = this.newSession();
        JPAWorkingMemoryDbLogger logger = new JPAWorkingMemoryDbLogger(ksession);
        ksession.startProcess("com.felit.drools.chapter03.task.element.timerStartCron");
        Thread.sleep(1000);
    }

    @Override
    protected void createKnowledgeBase() {
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(new ClassPathResource("element/timer-start-cron.bpmn"), ResourceType.BPMN2);
        this.knowledgeBase = builder.newKnowledgeBase();
    }
}
