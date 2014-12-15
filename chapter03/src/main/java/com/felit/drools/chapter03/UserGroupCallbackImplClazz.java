package com.felit.drools.chapter03;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.task.identity.UserGroupCallback;

import java.util.Arrays;
import java.util.List;

/**
 * System.setProperty(JBPM_USERGROUP_CALLBACK, "com.felit.drools.chapter03.UserGroupCallbackImplClazz");
 * UserGroupCallbackManager.getInstance().getCallback().existsGroup("200");
 */
public class UserGroupCallbackImplClazz implements UserGroupCallback {
    private Log log = LogFactory.getLog(UserGroupCallbackImplClazz.class);

    @Override
    public boolean existsUser(String userId) {
        log.info("userId:" + userId);
        System.out.println("userId:" + userId);
        return true;
    }

    @Override
    public boolean existsGroup(String groupId) {
        log.info("groupId:" + groupId);
        System.out.println("groupId:" + groupId);
        return true;
    }

    @Override
    public List<String> getGroupsForUser(String userId, List<String> groupIds, List<String> allExistingGroupIds) {
        log.info("userId:" + userId + ",groupIds:" + groupIds + ",allExistingGroupIds: " + allExistingGroupIds);
        return Arrays.asList(new String[]{"1", "2"});
    }
}
