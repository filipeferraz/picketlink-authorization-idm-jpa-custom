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

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.model.CustomUser;
import org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.typeentity.*;
import org.picketlink.idm.config.IdentityConfiguration;
import org.picketlink.idm.config.IdentityConfigurationBuilder;
import org.picketlink.idm.credential.handler.PasswordCredentialHandler;
import org.picketlink.idm.jpa.model.sample.simple.AttributeTypeEntity;
import org.picketlink.idm.jpa.model.sample.simple.IdentityTypeEntity;
import org.picketlink.idm.jpa.model.sample.simple.PartitionTypeEntity;
import org.picketlink.idm.jpa.model.sample.simple.PasswordCredentialTypeEntity;
import org.picketlink.idm.jpa.model.sample.simple.RelationshipIdentityTypeEntity;
import org.picketlink.idm.jpa.model.sample.simple.RelationshipTypeEntity;
import org.picketlink.idm.model.Relationship;
//import org.picketlink.internal.EEJPAContextInitializer;

/**
 * This bean produces the configuration for PicketLink IDM
 * 
 * 
 * @author Filipe Ferraz
 *
 */
@ApplicationScoped
public class IDMConfiguration {

//    @Inject
//    private EEJPAContextInitializer contextInitializer;

    private IdentityConfiguration identityConfig = null;

    @Produces IdentityConfiguration createConfig() {
        if (identityConfig == null) {
            initConfig();
        }
        return identityConfig;
    }

    /**
     * This method uses the IdentityConfigurationBuilder to create an IdentityConfiguration, which 
     * defines how PicketLink stores identity-related data.  In this particular example, a 
     * JPAIdentityStore is configured to allow the identity data to be stored in a relational database
     * using JPA based on Custom Entities implementation.
     */
    private void initConfig() {
        IdentityConfigurationBuilder builder = new IdentityConfigurationBuilder();

        builder
            .named("default")
                .stores()
                    .jpa()
                        .mappedEntity(
                                CustomUserTypeEntity.class,
                                CustomRoleTypeEntity.class,
                                CustomDepartmentTypeEntity.class,
                                CustomApplicationTypeEntity.class,
                                CustomResourceTypeEntity.class,
                                IdentityTypeEntity.class,
                                RelationshipTypeEntity.class,
                                RelationshipIdentityTypeEntity.class,
                                PartitionTypeEntity.class,
                                PasswordCredentialTypeEntity.class,
                                AttributeTypeEntity.class)
                        .supportGlobalRelationship(Relationship.class)
//                        .addContextInitializer(this.contextInitializer)
                        // Specify that this identity store configuration supports all features
//                        .setCredentialHandlerProperty(PasswordCredentialHandler.SUPPORTED_ACCOUNT_TYPES_PROPERTY, CustomUser.class)
//                        .setCredentialHandlerProperty(PasswordCredentialHandler.LOGIN_NAME_PROPERTY, "userName")
                        .supportAllFeatures();

        identityConfig = builder.build();
    }
}
