package com.felit.drools.chapter01.test;

import com.felit.drools.chapter01.model.Source;
import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.WorkingMemoryEntryPoint;
import org.testng.annotations.Test;

/**
 */
public class ConflictionTest extends TestBasic {
    @Test
    public void testConfliction() {
        StatefulKnowledgeSession ksession = this.getKnowledgeBase().newStatefulKnowledgeSession();
        Source source = new Source();
        source.setName("source name");
        ksession.insert(source);
        ksession.fireAllRules();
    }

    public KnowledgeBase getKnowledgeBase() {
        KnowledgeBuilder knowledgeBuilder = this.getKnowledgeBuilder();
        knowledgeBuilder.add(new ClassPathResource("chapter01-source-conflict.drl"), ResourceType.DRL);
        return knowledgeBuilder.newKnowledgeBase();
    }
}
