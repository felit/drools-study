package com.felit.chapter01.test;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderConfiguration;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.type.FactType;
import org.drools.io.ResourceFactory;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.Environment;
import org.drools.runtime.KnowledgeSessionConfiguration;
import org.drools.runtime.StatefulKnowledgeSession;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Date;

/**
 *
 */
@Test
public class FactTest {
    protected KnowledgeBase knowledgeBase;

    @BeforeTest
    public void setup() {
        this.init();
    }

    @Test
    public void testFact() throws IllegalAccessException, InstantiationException {
        // set configuration for knowledgeSession
        KnowledgeSessionConfiguration sessionConfiguration = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
//        sessionConfiguration.setProperty();
        Environment env = KnowledgeBaseFactory.newEnvironment();
//        env.set();

        // create knowledgeSession
        StatefulKnowledgeSession ksession = this.knowledgeBase.newStatefulKnowledgeSession(sessionConfiguration, env);

        // get the class of Fact
        FactType factType = this.knowledgeBase.getFactType("com.felit.chapter01", "Requirement");
        Assert.assertNotNull(factType);

        // instance the Fact
        Object requirement = factType.newInstance();
        factType.set(requirement, "title", "the title of requirement");
        factType.set(requirement, "content", "the requirement content");
        factType.set(requirement, "handled_at", new Date());
        factType.set(requirement, "created_at", new Date());
        factType.set(requirement, "last_updated", new Date());
        // add the fact to knowledgeSession
        ksession.insert(requirement);

        int rulesNum = ksession.fireAllRules();

        System.out.println(rulesNum);
        Assert.assertEquals(ksession.getObjects().size(), 0);

    }

    @AfterTest
    public void destroy() {

    }

    protected void init() {
        // configure knowledgeBuilder
        KnowledgeBuilderConfiguration builderConfiguration = KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration();
//        builderConfiguration.setProperty();

        // create KnowledgeBuilder
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder(builderConfiguration);
        builder.add(new ClassPathResource("chapter01-rules.drl", getClass()), ResourceType.DRL);

        // create KnowledgeBase
        KnowledgeBase knowledgeBase = builder.newKnowledgeBase();
        this.knowledgeBase = knowledgeBase;
    }
}
