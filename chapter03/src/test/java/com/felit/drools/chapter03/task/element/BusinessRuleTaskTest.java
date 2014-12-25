package com.felit.drools.chapter03.task.element;

import com.felit.drools.chapter03.task.BasicTest;
import com.felit.drools.chapter03.task.element.model.RuleDomain;
import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.testng.annotations.Test;

/**
 */
public class BusinessRuleTaskTest extends BasicTest {
    protected final String BUSINESS_PROESS_ID = "com.felit.drools.chapter03.task.element.businessRuleTask";

    @Test
    public void test() {
        StatefulKnowledgeSession ksession = this.newSession();
        ksession.insert(new RuleDomain("test0001"));
        ksession.startProcess(BUSINESS_PROESS_ID);
        ksession.fireAllRules();
        System.out.println("ksession.getObjects().size():" + ksession.getObjects().size());
    }

    @Override
    protected void createKnowledgeBase() {
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(ResourceFactory.newClassPathResource("element/businessRuleTask.drl"), ResourceType.DRL);//与顺序有关
        builder.add(ResourceFactory.newClassPathResource("element/businessRuleTask.bpmn"), ResourceType.BPMN2);
        KnowledgeBase kbase = builder.newKnowledgeBase();
        this.knowledgeBase = kbase;
    }
}
