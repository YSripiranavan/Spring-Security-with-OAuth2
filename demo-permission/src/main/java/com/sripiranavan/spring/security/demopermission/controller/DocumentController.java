package com.sripiranavan.spring.security.demopermission.controller;

import com.sripiranavan.spring.security.demopermission.model.Document;
import com.sripiranavan.spring.security.demopermission.service.DocumentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/documents/{username}")
    public List<Document> findAllDocuments(@PathVariable String username){
        return documentService.findAllDocuments(username);
    }
}
