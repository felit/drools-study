package com.felit.drools.chapter01.test;

import org.drools.WorkingMemory;
import org.drools.reteoo.ReteooRuleBase;
import org.drools.reteoo.RuleTerminalNode;
import org.drools.rule.Rule;
import org.testng.annotations.Test;

import java.util.Calendar;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 *
 */
public class RuleTest {

    @Test
    public void testDateEffective() {
        WorkingMemory wm = new ReteooRuleBase("x").newStatefulSession();
        final Rule rule = new Rule("myrule");
        assertTrue(rule.isEffective(null, new RuleTerminalNode(), wm));

        final Calendar earlier = Calendar.getInstance();
        earlier.setTimeInMillis(10);
        rule.setDateEffective(earlier);
        // This uses the dateEffective, dateExpires and enabled flag to decide this.
        assertTrue(rule.isEffective(null,new RuleTerminalNode(),wm));

        final Calendar later = Calendar.getInstance();
        later.setTimeInMillis(later.getTimeInMillis() + 1000000000);

        assertTrue(later.after(Calendar.getInstance()));

        rule.setDateEffective(later);
        assertFalse(rule.isEffective(null, new RuleTerminalNode(), wm));
    }
}
