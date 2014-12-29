package com.felit.drools.chapter07;

import org.drools.builder.ResourceType;
import org.drools.runtime.StatefulKnowledgeSession;
import org.testng.annotations.Test;

/**
 */
public class SignalStartTest extends BasicTest {
    @Test
    public void test() {
        StatefulKnowledgeSession ksession = this.newSession();
        // All process instances that are listening to this type  of(external) event will be notified.
        ksession.signalEvent("my-signal", "hello");
//        ksession.signalEvent("my-signal", "hello");

    }

    @Override
    protected void addResource() {
        this.addClasspathResource("signal-start.bpmn", ResourceType.BPMN2);
    }
}
