package org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.model;

import org.picketlink.idm.model.AbstractIdentityType;
import org.picketlink.idm.model.annotation.AttributeProperty;
import org.picketlink.idm.model.annotation.Unique;
import org.picketlink.idm.query.QueryParameter;

public class CustomRole extends AbstractIdentityType {

    private static final long serialVersionUID = 7326946897042888190L;

    /**
     * A query parameter used to set the name value.
     */
    public static final QueryParameter NAME = QUERY_ATTRIBUTE.byName("name");

    private String name;

    public CustomRole() {
    }

    public CustomRole(String name) {
        this.name = name;
    }

    @AttributeProperty
    @Unique
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
