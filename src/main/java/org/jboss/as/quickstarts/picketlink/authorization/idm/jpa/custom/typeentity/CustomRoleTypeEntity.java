package org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.typeentity;

import org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.model.CustomRole;
import org.picketlink.idm.jpa.annotations.AttributeValue;
import org.picketlink.idm.jpa.annotations.entity.IdentityManaged;
import org.picketlink.idm.jpa.model.sample.simple.IdentityTypeEntity;

import javax.persistence.Entity;

@IdentityManaged(CustomRole.class)
@Entity
public class CustomRoleTypeEntity extends IdentityTypeEntity {

    private static final long serialVersionUID = -7620085901556760045L;

    @AttributeValue
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
