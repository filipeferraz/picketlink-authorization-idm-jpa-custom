package org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.model;

import org.picketlink.idm.model.AbstractAttributedType;
import org.picketlink.idm.model.Account;
import org.picketlink.idm.model.Relationship;
import org.picketlink.idm.model.annotation.InheritsPrivileges;
import org.picketlink.idm.query.RelationshipQueryParameter;

public class AuthorizationRelationship extends AbstractAttributedType implements Relationship {

    private static final long serialVersionUID = 1466310876565717402L;

    public static final RelationshipQueryParameter ACCOUNT = new RelationshipQueryParameter() {
        @Override
        public String getName() {
            return "account";
        }
    };

    public static final RelationshipQueryParameter CUSTOM_DEPARTMENT = new RelationshipQueryParameter() {
        @Override
        public String getName() {
            return "customDepartment";
        }
    };

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

    private Account account;
    private CustomDepartment customDepartment;
    private CustomApplication customApplication;
    private CustomRole customRole;

    public AuthorizationRelationship() {}

    public AuthorizationRelationship(Account account, CustomDepartment customDepartment, CustomApplication customApplication, CustomRole customRole) {
        this.account = account;
        this.customDepartment = customDepartment;
        this.customApplication = customApplication;
        this.customRole = customRole;
    }

    @InheritsPrivileges("customDepartment, customApplication")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public CustomDepartment getCustomDepartment() {
        return customDepartment;
    }

    public void setCustomDepartment(CustomDepartment customDepartment) {
        this.customDepartment = customDepartment;
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

}