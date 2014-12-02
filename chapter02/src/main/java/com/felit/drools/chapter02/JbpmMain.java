package com.felit.drools.chapter02;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.ProcessInstance;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class JbpmMain {
    public static void main(String args[]) {
        StatefulKnowledgeSession ksession = getSession();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("title", "test simple process");
        ProcessInstance processInstance = ksession.createProcessInstance("com.felit.drools.chapter02.SimpleProcess",params);
        ProcessInstance processInstance1 = ksession.startProcessInstance(processInstance.getId());
        ksession.dispose();
    }

    private static StatefulKnowledgeSession getSession() {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("SimpleProcess.bpmn2"), ResourceType.BPMN2);
        KnowledgeBase knowledgeBase = kbuilder.newKnowledgeBase();
        StatefulKnowledgeSession ksession = knowledgeBase.newStatefulKnowledgeSession();
        return ksession;
    }
}
