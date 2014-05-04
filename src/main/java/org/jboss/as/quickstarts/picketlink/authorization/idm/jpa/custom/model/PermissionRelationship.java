package org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.model;

import org.picketlink.idm.model.AbstractAttributedType;
import org.picketlink.idm.model.Relationship;
import org.picketlink.idm.query.RelationshipQueryParameter;

public class PermissionRelationship extends AbstractAttributedType implements Relationship {

    private static final long serialVersionUID = 3544714080801837357L;

    public static final RelationshipQueryParameter CUSTOM_APPLICATION = new RelationshipQueryParameter() {
        @Override
        public String getName() {
            return "customApplication";
        }
    };

    public static final RelationshipQueryParameter CUSTOM_ROLE = new RelationshipQueryParameter() {
        @Override
        public String getName() {
            return "customRole";
        }
    };

    public static final RelationshipQueryParameter CUSTOM_RESOURCE = new RelationshipQueryParameter() {
        @Override
        public String getName() {
            return "customResource";
        }
    };

    private CustomApplication customApplication;
    private CustomRole customRole;
    private CustomResource customResource;

    public PermissionRelationship() {

    }

    public PermissionRelationship(CustomApplication customApplication, CustomRole customRole, CustomResource customResource) {
        this.customApplication = customApplication;
        this.customRole = customRole;
        this.customResource = customResource;
    }

    public CustomApplication getCustomApplication() {
        return customApplication;
    }

    public void setCustomApplication(CustomApplication customApplication) {
        this.customApplication = customApplication;
    }

    public CustomRole getCustomRole() {
        return customRole;
    }

    public void setCustomRole(CustomRole customRole) {
        this.customRole = customRole;
    }

    public CustomResource getCustomResource() {
        return customResource;
    }

    public void setCustomResource(CustomResource customResource) {
        this.customResource = customResource;
    }
}