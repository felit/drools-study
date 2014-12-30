package com.felit.drools.chapter07;

import com.felit.drools.chapter07.model.Person;
import org.drools.builder.ResourceType;
import org.drools.runtime.StatefulKnowledgeSession;
import org.testng.annotations.Test;

/**
 */
public class IntermediateCatchEventConditionTest extends BasicTest{
    @Test
    public void test() {
        StatefulKnowledgeSession ksession = this.newSession();
        ksession.startProcess("com.felit.drools.chapter07.IntermediateCatchEventConditionTest");
        ksession.insert(new Person("Jack"));
    }
    @Override
    protected void addResource() {
        this.addClasspathResource("intermediateCatchEventCondition-test.bpmn", ResourceType.BPMN2);
    }
}
