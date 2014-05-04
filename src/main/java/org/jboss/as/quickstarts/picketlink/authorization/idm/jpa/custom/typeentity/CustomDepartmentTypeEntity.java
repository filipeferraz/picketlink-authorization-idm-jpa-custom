package org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.typeentity;

import org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.model.CustomDepartment;
import org.picketlink.idm.jpa.annotations.AttributeValue;
import org.picketlink.idm.jpa.annotations.entity.IdentityManaged;
import org.picketlink.idm.jpa.model.sample.simple.IdentityTypeEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@IdentityManaged(CustomDepartment.class)
@Entity
public class CustomDepartmentTypeEntity extends IdentityTypeEntity {

    private static final long serialVersionUID = 4910826120822857913L;

    @AttributeValue
    private String name;

    @AttributeValue
    private String path;

    @ManyToOne
    @AttributeValue (name = "parent")
    private CustomDepartmentTypeEntity parent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public CustomDepartmentTypeEntity getParent() {
        return parent;
    }

    public void setParent(CustomDepartmentTypeEntity parent) {
        this.parent = parent;
    }

}

