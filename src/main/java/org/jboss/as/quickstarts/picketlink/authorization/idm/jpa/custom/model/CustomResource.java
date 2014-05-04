package org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.model;

import org.picketlink.idm.model.AbstractIdentityType;
import org.picketlink.idm.model.annotation.AttributeProperty;
import org.picketlink.idm.model.annotation.Unique;
import org.picketlink.idm.query.QueryParameter;

public class CustomResource extends AbstractIdentityType {

    private static final long serialVersionUID = -7704305755828380217L;

    /**
     * A query parameter used to set the name value.
     */
    public static final QueryParameter PATH = QUERY_ATTRIBUTE.byName("path");

    private String path;

    public CustomResource() {
    }

    public CustomResource(String path) {
        this.path = path;
    }

    @AttributeProperty
    @Unique
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
