package com.felit.drools.chapter01.test;

import com.felit.drools.chapter01.model.monitor.Fire;
import com.felit.drools.chapter01.model.monitor.Room;
import com.felit.drools.chapter01.model.monitor.Sprinkler;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class StatefulMonitorTest extends TestBasic {

    @Test
    public void testMonitor() {
        KnowledgeBuilder builder = this.getKnowledgeBuilder();
        builder.add(new ClassPathResource("chapter01-stateful-monitor.drl"), ResourceType.DRL);
        this.knowledgeBase = builder.newKnowledgeBase();

        StatefulKnowledgeSession ksession = this.knowledgeBase.newStatefulKnowledgeSession();

        String[] names = new String[]{"kitchen", "bedroom", "office", "livingroom"};
        Map<String, Room> name2room = new HashMap<String, Room>();

        for (String name : names) {
            Room room = new Room(name);
            name2room.put(name, room);
            ksession.insert(room);
            Sprinkler sprinkler = new Sprinkler(room);
            ksession.insert(sprinkler);
        }
        ksession.fireAllRules();
        System.out.println("------------------------------------------");
        Fire fire = new Fire();
        fire.setRoom(name2room.get("office"));
        ksession.insert(fire);
        ksession.fireAllRules();
    }
}
