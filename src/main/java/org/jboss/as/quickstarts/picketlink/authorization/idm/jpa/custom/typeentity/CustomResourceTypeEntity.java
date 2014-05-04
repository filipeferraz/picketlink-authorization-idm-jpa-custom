package org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.typeentity;

import org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.model.CustomResource;
import org.picketlink.idm.jpa.annotations.AttributeValue;
import org.picketlink.idm.jpa.annotations.entity.IdentityManaged;
import org.picketlink.idm.jpa.model.sample.simple.IdentityTypeEntity;

import javax.persistence.Entity;

@IdentityManaged(CustomResource.class)
@Entity
public class CustomResourceTypeEntity extends IdentityTypeEntity {

    private static final long serialVersionUID = 6866154636896400514L;

    @AttributeValue
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
