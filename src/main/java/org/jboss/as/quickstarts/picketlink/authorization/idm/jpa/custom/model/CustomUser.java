package org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.model;

import org.picketlink.idm.model.annotation.AttributeProperty;

public class CustomUser extends CustomAgent {

    @AttributeProperty
    private String name;

    @AttributeProperty
    private String email;

    public CustomUser() { }

    public CustomUser(String userName) {
        super(userName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
