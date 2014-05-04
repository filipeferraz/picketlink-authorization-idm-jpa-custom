package org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.typeentity;

import org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.model.CustomApplication;
import org.picketlink.idm.jpa.annotations.AttributeValue;
import org.picketlink.idm.jpa.annotations.entity.IdentityManaged;
import org.picketlink.idm.jpa.model.sample.simple.IdentityTypeEntity;

import javax.persistence.Entity;

@IdentityManaged(CustomApplication.class)
@Entity
public class CustomApplicationTypeEntity extends IdentityTypeEntity {

    private static final long serialVersionUID = -6027680614875480250L;

    @AttributeValue
    private String name;

    @AttributeValue
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

