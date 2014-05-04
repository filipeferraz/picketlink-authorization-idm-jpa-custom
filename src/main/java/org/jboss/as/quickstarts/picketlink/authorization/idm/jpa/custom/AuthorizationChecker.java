/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom;

import org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.model.*;
import org.picketlink.Identity;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.query.IdentityQuery;
import org.picketlink.idm.query.RelationshipQuery;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import java.util.*;

/**
 * This is a utility bean that may be used by the view layer to determine whether the
 * current user has specific privileges based on custom entities implementation.
 * 
 * @author Filipe Ferraz
 */
@Named 
public class AuthorizationChecker {
    
    @Inject
    private Identity identity;
    
    @Inject 
    private IdentityManager identityManager;

    @Inject
    private RelationshipManager relationshipManager;

    @Inject
    private AuthorizationData authorizationData;

    @Inject
    private FacesContext facesContext;

    public void assignDepartment(String departmentName) {
        for (CustomDepartment department : getDepartments()) {
            if (department.getName().equals(departmentName)) {
                authorizationData.setDepartment(department);
                facesContext.addMessage(
                        null,
                        new FacesMessage("Department "+department.getName()+" assigned."));
            }
        }
    }

    public List<CustomDepartment> getDepartments() {
        RelationshipQuery<AuthorizationRelationship> query = relationshipManager.createRelationshipQuery(AuthorizationRelationship.class);

        query.setParameter(AuthorizationRelationship.ACCOUNT, identity.getAccount());
        query.setParameter(AuthorizationRelationship.CUSTOM_APPLICATION, getCurrentApplication());

        List<AuthorizationRelationship> result = query.getResultList();

        Set<CustomDepartment> departments = new HashSet<CustomDepartment>();
        for (AuthorizationRelationship relationship : result) {
            departments.add(relationship.getCustomDepartment());
        }
        return new ArrayList<CustomDepartment>(departments);
    }

    public List<CustomResource> getResources(CustomRole customRole) {
        RelationshipQuery<PermissionRelationship> query = relationshipManager.createRelationshipQuery(PermissionRelationship.class);

        query.setParameter(PermissionRelationship.CUSTOM_APPLICATION, getCurrentApplication());
        query.setParameter(PermissionRelationship.CUSTOM_ROLE, customRole);

        List<PermissionRelationship> result = query.getResultList();

        Set<CustomResource> customResources = new HashSet<CustomResource>();

        for (PermissionRelationship relationship : result) {
            customResources.add(relationship.getCustomResource());
        }

        return new ArrayList<CustomResource>(customResources);
    }

    public List<CustomRole> getRoles(CustomDepartment department) {
        RelationshipQuery<AuthorizationRelationship> query = relationshipManager.createRelationshipQuery(AuthorizationRelationship.class);

        query.setParameter(AuthorizationRelationship.ACCOUNT, identity.getAccount());
        query.setParameter(AuthorizationRelationship.CUSTOM_DEPARTMENT, department);

        List<AuthorizationRelationship> result = query.getResultList();

        Set<CustomRole> roles = new HashSet<CustomRole>();

        for (AuthorizationRelationship relationship : result) {
            roles.add(relationship.getCustomRole());
        }

        return new ArrayList<CustomRole>(roles);
    }

    public String getRoles() {
        RelationshipQuery<AuthorizationRelationship> query = relationshipManager.createRelationshipQuery(AuthorizationRelationship.class);

        query.setParameter(AuthorizationRelationship.ACCOUNT, identity.getAccount());
        query.setParameter(AuthorizationRelationship.CUSTOM_APPLICATION, getCurrentApplication());
        query.setParameter(AuthorizationRelationship.CUSTOM_DEPARTMENT, authorizationData.getDepartment());

        List<AuthorizationRelationship> result = query.getResultList();

        if (result.size() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<result.size()-1; i++) {
            sb.append(result.get(i).getCustomRole().getName()+", ");
        }
        sb.append(result.get(result.size()-1).getCustomRole().getName());

        return sb.toString();
    }

    public CustomApplication getCurrentApplication() {
        IdentityQuery<CustomApplication> query = identityManager.createIdentityQuery(CustomApplication.class);

        query.setParameter(CustomApplication.NAME, ResourceProtectionFilter.APPLICATION_NAME);

        return query.getResultList().get(0);
    }

}
