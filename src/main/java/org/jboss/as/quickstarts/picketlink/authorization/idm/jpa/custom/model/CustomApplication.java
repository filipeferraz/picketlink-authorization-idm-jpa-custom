package org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.model;

import org.picketlink.idm.model.AbstractIdentityType;
import org.picketlink.idm.model.annotation.AttributeProperty;
import org.picketlink.idm.model.annotation.Unique;
import org.picketlink.idm.query.QueryParameter;

public class CustomApplication extends AbstractIdentityType {

    private static final long serialVersionUID = -409891165353192308L;

    /**
     * A query parameter used to set the name value.
     */
    public static final QueryParameter NAME = QUERY_ATTRIBUTE.byName("name");

    /**
     * A query parameter used to set the path.
     */
    public static final QueryParameter PATH = QUERY_ATTRIBUTE.byName("path");

    @AttributeProperty
    @Unique
    private String name;

    @AttributeProperty
    @Unique
    private String path;

    public CustomApplication() {}

    public CustomApplication(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Error creating CustomApplication - name cannot be null or empty");
        }

        this.name = name;
    }

    public CustomApplication(String name, String path) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Error creating CustomApplication - name cannot be null or empty");
        }
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Error creating CustomApplication - path cannot be null or empty");
        }

        this.name = name;
        this.path = path;
    }

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

}