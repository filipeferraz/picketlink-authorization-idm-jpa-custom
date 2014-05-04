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

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.model.*;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.credential.Password;

/**
 * This startup bean creates a basic application structure configuration to be used as example
 * of implementing a custom authentication schema.
 * 
 * @author Filipe Ferraz
 */
@Singleton
@Startup
public class IDMInitializer {

    @Inject
    private PartitionManager partitionManager;

    @PostConstruct
    public void create() {

        IdentityManager identityManager = this.partitionManager.createIdentityManager();

        // Create user john
        CustomUser john = new CustomUser("john");
        john.setName("John Smith");
        john.setEmail("john@acme.com");

        identityManager.add(john);
        identityManager.updateCredential(john, new Password("demo"));

        // Create user mary
        CustomUser mary = new CustomUser("mary");
        mary.setName("Mary Jones");
        mary.setEmail("mary@acme.com");

        identityManager.add(mary);
        identityManager.updateCredential(mary, new Password("demo"));

        // Create user jane
        CustomUser jane = new CustomUser("jane");
        jane.setName("Jane Doe");
        jane.setEmail("jane@acme.com");

        identityManager.add(jane);
        identityManager.updateCredential(jane, new Password("demo"));

        // Create departments

        CustomDepartment newYorkDep = new CustomDepartment("New York Department");
        identityManager.add(newYorkDep);

        CustomDepartment miamiDepartment = new CustomDepartment("Miami Department");
        identityManager.add(miamiDepartment);

        CustomDepartment orlandoDepartment = new CustomDepartment("Orlando Department");
        identityManager.add(orlandoDepartment);

        // Create application

        CustomApplication application = new CustomApplication(ResourceProtectionFilter.APPLICATION_NAME, "/picketlink-authorization-idm-jpa-custom");
        identityManager.add(application);

        // Create roles

        CustomRole usuario = new CustomRole("user");
        identityManager.add(usuario);

        CustomRole admin = new CustomRole("admin");
        identityManager.add(admin);

        // Create resources

        CustomResource acme = new CustomResource("acme");
        identityManager.add(acme);

        CustomResource umbrella = new CustomResource("umbrella");
        identityManager.add(umbrella);

        CustomResource wayne = new CustomResource("wayne");
        identityManager.add(wayne);

        RelationshipManager relationshipManager = this.partitionManager.createRelationshipManager();

        // Add authorization relationship

        relationshipManager.add(new AuthorizationRelationship(john, newYorkDep, application, admin));
        relationshipManager.add(new AuthorizationRelationship(john, miamiDepartment, application, admin));
        relationshipManager.add(new AuthorizationRelationship(john, orlandoDepartment, application, admin));

        relationshipManager.add(new AuthorizationRelationship(mary, miamiDepartment, application, usuario));

        relationshipManager.add(new AuthorizationRelationship(jane, orlandoDepartment, application, usuario));

        // Add permission relationship

        relationshipManager.add(new PermissionRelationship(application, admin, acme));

        relationshipManager.add(new PermissionRelationship(application, usuario, umbrella));

        relationshipManager.add(new PermissionRelationship(application, usuario, wayne));

    }
}
