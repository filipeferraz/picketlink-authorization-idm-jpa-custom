package org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.typeentity;

import org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.model.CustomAgent;
import org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.model.CustomUser;
import org.picketlink.idm.jpa.annotations.AttributeValue;
import org.picketlink.idm.jpa.annotations.entity.IdentityManaged;
import org.picketlink.idm.jpa.model.sample.simple.IdentityTypeEntity;

import javax.persistence.Entity;

@IdentityManaged({CustomUser.class, CustomAgent.class})
@Entity
public class CustomUserTypeEntity extends IdentityTypeEntity {

    private static final long serialVersionUID = 7081845074962222470L;

    @AttributeValue
    private String userName;

    @AttributeValue
    private String name;

    @AttributeValue
    private String email;

    public CustomUserTypeEntity() {}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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