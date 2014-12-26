package com.felit.drools.chapter03.task.element;

import com.felit.drools.chapter03.task.BasicTest;
import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.jbpm.process.audit.JPAWorkingMemoryDbLogger;
import org.testng.annotations.Test;

/**
 */
public class DivergingGatewayTest extends BasicTest{

    @Test
    public void test() {
        StatefulKnowledgeSession ksession = this.newSession();
        JPAWorkingMemoryDbLogger logger = new JPAWorkingMemoryDbLogger(ksession);
        ksession.startProcess("com.felit.drools.chapter03.task.element.divergingGateway");
    }
    @Override
    protected void createKnowledgeBase() {
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(ResourceFactory.newClassPathResource("element/diverging-gateway.bpmn"), ResourceType.BPMN2);//与顺序有关
        KnowledgeBase kbase = builder.newKnowledgeBase();
        this.knowledgeBase = kbase;
    }
}
