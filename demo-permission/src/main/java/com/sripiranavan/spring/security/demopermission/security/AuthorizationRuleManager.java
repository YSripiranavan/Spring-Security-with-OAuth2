package com.sripiranavan.spring.security.demopermission.security;

import com.sripiranavan.spring.security.demopermission.model.Document;

import java.util.List;

public interface AuthorizationRuleManager {
    public boolean applySecurityPermissions(List<Document> returnedList, String auth);
}
