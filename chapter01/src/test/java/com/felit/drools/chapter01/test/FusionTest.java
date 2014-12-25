package com.felit.drools.chapter01.test;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.ResourceType;
import org.drools.conf.EventProcessingOption;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.KnowledgeSessionConfiguration;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.conf.ClockTypeOption;
import org.drools.time.SessionPseudoClock;

/**
 */
public class FusionTest extends TestBasic {
    public void testFusion() {
        KnowledgeBaseConfiguration configuration = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
        configuration.setOption(EventProcessingOption.STREAM);
        KnowledgeBase base = KnowledgeBaseFactory.newKnowledgeBase(configuration);
        KnowledgeBuilder builder = this.getKnowledgeBuilder();
        builder.add(new ClassPathResource("chapter01-rule-attributes.drl"), ResourceType.DSL);
        base.addKnowledgePackages(builder.getKnowledgePackages());
        KnowledgeSessionConfiguration sessionConfiguration = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
        sessionConfiguration.setOption(ClockTypeOption.get("pseudo"));
        StatefulKnowledgeSession ksession = base.newStatefulKnowledgeSession(sessionConfiguration, null);
        SessionPseudoClock clock = ksession.getSessionClock();

    }
}
