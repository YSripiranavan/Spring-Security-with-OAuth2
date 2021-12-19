package com.sripiranavan.spring.security.demopermission.service;

import com.sripiranavan.spring.security.demopermission.model.Document;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Service
public class DocumentService {

//    @PostAuthorize("hasPermission(returnObject,'read')")
//    @PostAuthorize("@documentMethodAuthorizationManager.applySecurityPermissions(returnObject,'read')")
//    @Secured("ROLE_MANAGER")
    @RolesAllowed("ROLE_MANAGER")
    public List<Document> findAllDocuments(String username){
        var doc = new Document();
        doc.setUser("piranavan");
        doc.setText("Hello Pianavan");
        return List.of(doc);
    }
}
