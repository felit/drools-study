package com.felit.drools.chapter01.action;

import org.drools.runtime.rule.RuleContext;

/**
 */
public class ActionDemo {
    public static String testAction(RuleContext kcontext) {
        System.out.println(kcontext);
        return "return kcontext:" + kcontext.toString();
    }
}
