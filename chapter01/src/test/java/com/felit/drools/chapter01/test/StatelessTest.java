package com.felit.drools.chapter01.test;


import com.felit.drools.chapter01.model.Applicant;
import com.felit.drools.chapter01.model.Application;
import junit.framework.Assert;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatelessKnowledgeSession;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Date;

/**
 *
 */
public class StatelessTest extends TestBasic{
    @Test
    public void testApplicant() {
        KnowledgeBuilder knowledgeBuilder = this.getKnowledgeBuilder();
        knowledgeBuilder.add(new ClassPathResource("chapter01-applicant.drl"), ResourceType.DRL);
        this.knowledgeBase = knowledgeBuilder.newKnowledgeBase();

        StatelessKnowledgeSession statelessKnowledgeSession = this.knowledgeBase.newStatelessKnowledgeSession();
        Applicant applicant = new Applicant();
        applicant.setAge(16);
        applicant.setName("Mr John Smith");
        applicant.setValid(true);

        Application application = new Application();
        application.setDateApplied(new Date());
        statelessKnowledgeSession.execute(Arrays.asList(applicant, application));
        Assert.assertFalse(application.isValid());

//        CommandFactory
    }
}
