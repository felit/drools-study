package com.felit.drools.chapter07.listener;

import org.drools.event.process.*;

/**
 */
public class DemoProcessListener implements ProcessEventListener {
    @Override
    public void beforeProcessStarted(ProcessStartedEvent event) {
        System.out.println("beforeProcessStarted:" + event);

    }

    @Override
    public void afterProcessStarted(ProcessStartedEvent event) {
        System.out.println("afterProcessStarted:" + event);
    }

    @Override
    public void beforeProcessCompleted(ProcessCompletedEvent event) {
        System.out.println("beforeProcessCompleted:" + event);
    }

    @Override
    public void afterProcessCompleted(ProcessCompletedEvent event) {
        System.out.println("afterProcessCompleted:" + event);
    }

    @Override
    public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {
        System.out.println("beforeNodeTriggered:" + event);
    }

    @Override
    public void afterNodeTriggered(ProcessNodeTriggeredEvent event) {
        System.out.println("afterNodeTriggered:" + event);
    }

    @Override
    public void beforeNodeLeft(ProcessNodeLeftEvent event) {
        System.out.println("beforeNodeLeft:" + event);
    }

    @Override
    public void afterNodeLeft(ProcessNodeLeftEvent event) {
        System.out.println("afterNodeLeft:" + event);
    }

    @Override
    public void beforeVariableChanged(ProcessVariableChangedEvent event) {
        System.out.println("beforeVariableChanged:" + event);
    }

    @Override
    public void afterVariableChanged(ProcessVariableChangedEvent event) {
        System.out.println("afterVariableChanged:" + event);
    }
}
