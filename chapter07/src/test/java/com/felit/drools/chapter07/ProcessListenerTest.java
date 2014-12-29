package com.felit.drools.chapter07;

import com.felit.drools.chapter07.listener.DemoProcessListener;
import org.drools.builder.ResourceType;
import org.drools.runtime.StatefulKnowledgeSession;
import org.testng.annotations.Test;

/**
 */
public class ProcessListenerTest extends BasicTest {
    @Test
    public void test() {
        StatefulKnowledgeSession ksession = this.newSession();
        ksession.addEventListener(new DemoProcessListener());
        ksession.startProcess("com.felit.drools.chapter07.ProcessListenerTest");
    }


    @Override
    protected void addResource() {
        this.addClasspathResource("process-test.bpmn", ResourceType.BPMN2);
    }
}
