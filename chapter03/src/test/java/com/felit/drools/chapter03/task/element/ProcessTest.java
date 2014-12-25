package com.felit.drools.chapter03.task.element;

import com.felit.drools.chapter03.task.BasicTest;
import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.ProcessInstance;
import org.testng.annotations.Test;

import java.util.Collections;

/**
 */
public class ProcessTest extends BasicTest {
    @Test
    public void testProcessElement() {
        StatefulKnowledgeSession ksession = this.newSession();
        ProcessInstance processInstance = ksession.createProcessInstance("com.felit.drools.chapter03.task.element.ProcessTest", null);
        for (String eventType : processInstance.getEventTypes()) {
            System.out.println(eventType);
        }
        ksession.startProcess("com.felit.drools.chapter03.task.element.ProcessTest", Collections.<String,Object>singletonMap("params","test params"));
        ksession.dispose();
    }
    @Override
    protected void createKnowledgeBase() {
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(ResourceFactory.newClassPathResource("process.bpmn"), ResourceType.BPMN2);
        KnowledgeBase kbase = builder.newKnowledgeBase();
        this.knowledgeBase = kbase;
    }
}
