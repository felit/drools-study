package com.felit.drools.chapter03.task;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.persistence.info.SessionInfo;
import org.drools.persistence.info.WorkItemInfo;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.ProcessInstance;
import org.jbpm.process.audit.JPAWorkingMemoryDbLogger;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

/**
 */
public class HumanTaskTest extends BasicTest {


    @Test
    public void testProcess() {
        int sessionId = 31;
        int processId = 31;
        StatefulKnowledgeSession ksession = this.loadSession(sessionId);
        ProcessInstance processInstance = ksession.getProcessInstance(processId);
        JPAWorkingMemoryDbLogger logger = new JPAWorkingMemoryDbLogger(ksession);
//        ksession.fireAllRules();
        ksession.dispose();
        logger.dispose();
    }

    @Test
    public void testProcessInstance() {
        List<SessionInfo> ksessions = this.emf.createEntityManager().createQuery("From SessionInfo ").getResultList();
        for (SessionInfo sessionInfo : ksessions) {
            StatefulKnowledgeSession knowledgeSession = this.loadSession(sessionInfo.getId());
            List<WorkItemInfo> workItemInfos = this.emf.createEntityManager().createQuery("From WorkItemInfo ").getResultList();
            for (WorkItemInfo workItemInfo : workItemInfos) {
                knowledgeSession.getWorkItemManager().completeWorkItem(workItemInfo.getId(), Collections.<String, Object>emptyMap());
            }
        }
    }


    @Override
    protected void createKnowledgeBase() {
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(ResourceFactory.newClassPathResource("task-client-server.bpmn"), ResourceType.BPMN2);
        KnowledgeBase kbase = builder.newKnowledgeBase();
        this.knowledgeBase = kbase;
    }
}
