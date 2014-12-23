package com.felit.drools.chapter03.task;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.persistence.info.SessionInfo;
import org.drools.persistence.info.WorkItemInfo;
import org.drools.runtime.StatefulKnowledgeSession;

import java.util.Collections;
import java.util.List;

/**
 *
 */
public class KnowledgeSessionJPATest extends BasicTest {
    public void test() {
        List<SessionInfo> ksessions = this.emf.createEntityManager().createQuery("From SessionInfo").getResultList();
        for (SessionInfo sessionInfo : ksessions) {
            StatefulKnowledgeSession ksession = this.loadSession(sessionInfo.getId());
            /*

            List<WorkItemInfo> workItemInfos = this.emf.createEntityManager().createQuery("From WorkItemInfo ").getResultList();
            for (WorkItemInfo workItemInfo : workItemInfos) {
                ksession.getWorkItemManager().completeWorkItem(workItemInfo.getId(), Collections.<String, Object>emptyMap());
            }*/
        }

    }

    @Override
    protected void createKnowledgeBase() {
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(ResourceFactory.newClassPathResource("task-client-server.bpmn"), ResourceType.BPMN2);
        KnowledgeBase kbase = builder.newKnowledgeBase();
        knowledgeBase = kbase;
    }
}
