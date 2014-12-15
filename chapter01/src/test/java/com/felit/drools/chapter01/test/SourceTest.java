package com.felit.drools.chapter01.test;

import com.felit.drools.chapter01.model.Source;
import org.drools.KnowledgeBase;
import org.drools.agent.KnowledgeAgent;
import org.drools.agent.KnowledgeAgentFactory;
import org.drools.builder.*;
import org.drools.builder.conf.KBuilderSeverityOption;
import org.drools.command.Command;
import org.drools.command.CommandFactory;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.Agenda;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class SourceTest {

    protected KnowledgeBase knowledgeBase;

    @Test
    public void testCommand() {
//        List<Command> cmds = new ArrayList<Command>();
//        cmds.add(CommandFactory.newInsert(null));
//        this.knowledgeBase.newStatefulKnowledgeSession().execute(CommandFactory.newBatchExecution(cmds));
        StatefulKnowledgeSession ksession = this.knowledgeBase.newStatefulKnowledgeSession();
        Source source = new Source();
        source.setName("source name");
        ksession.insert(source);
        Agenda agenda = ksession.getAgenda();
        ksession.fireAllRules();
//        Assert.assertEquals(ksession.getObjects().size(),0);
        ksession.dispose();

    }

    @BeforeTest
    public void init() {
        KnowledgeBuilderConfiguration bConfiguration = KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration();
        bConfiguration.setOption(KBuilderSeverityOption.get("drools.kbuilder.severity.duplicateRule", ResultSeverity.ERROR));
        bConfiguration.setOption(KBuilderSeverityOption.get("drools.kbuilder.severity.duplicateFunction",ResultSeverity.ERROR));

        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(bConfiguration);
        knowledgeBuilder.add(ResourceFactory.newClassPathResource("chapter01-source.drl"), ResourceType.DRL);
        KnowledgeAgent agent = KnowledgeAgentFactory.newKnowledgeAgent("my agent");
//        knowledgeBuilder.add(ResourceType.PKG);
        this.knowledgeBase = knowledgeBuilder.newKnowledgeBase();
    }
}
