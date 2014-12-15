package com.felit.drools.chapter01.test;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderConfiguration;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResultSeverity;
import org.drools.builder.conf.KBuilderSeverityOption;

/**
 */
public abstract class TestBasic {
    protected KnowledgeBase knowledgeBase;
    protected KnowledgeBuilder getKnowledgeBuilder() {
        KnowledgeBuilderConfiguration bConfiguration = KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration();
        bConfiguration.setOption(KBuilderSeverityOption.get("drools.kbuilder.severity.duplicateRule", ResultSeverity.ERROR));
        bConfiguration.setOption(KBuilderSeverityOption.get("drools.kbuilder.severity.duplicateFunction",ResultSeverity.ERROR));

        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(bConfiguration);
        return knowledgeBuilder;
    }
}
