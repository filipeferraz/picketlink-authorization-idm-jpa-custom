package org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.model;

import org.picketlink.idm.model.AbstractIdentityType;
import org.picketlink.idm.model.annotation.AttributeProperty;
import org.picketlink.idm.model.annotation.InheritsPrivileges;
import org.picketlink.idm.model.annotation.Unique;
import org.picketlink.idm.query.QueryParameter;

public class CustomDepartment extends AbstractIdentityType {

    private static final long serialVersionUID = -2496662437319238891L;

    /**
     * A query parameter used to set the name value.
     */
    public static final QueryParameter NAME = QUERY_ATTRIBUTE.byName("name");

    /**
     * A query parameter used to set the path.
     */
    public static final QueryParameter PATH = QUERY_ATTRIBUTE.byName("path");

    /**
     * A query parameter used to set the parent value.
     */
    public static final QueryParameter PARENT = QUERY_ATTRIBUTE.byName("parent");

    public static final String PATH_SEPARATOR = "/";

    @AttributeProperty
    private String name;

    @InheritsPrivileges
    @AttributeProperty
    private CustomDepartment parent;

    @AttributeProperty
    @Unique
    private String path;

    public CustomDepartment() {}

    public CustomDepartment(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Error creating CustomDepartment - name cannot be null or empty");
        }

        this.name = name;
        this.path = buildPath(this);
    }

    public CustomDepartment(String name, CustomDepartment parent) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Error creating CustomDepartment - name cannot be null or empty");
        }

        if (parent == null) {
            throw new IllegalArgumentException("Error creating CustomDepartment - parent cannot be null");
        }

        this.name = name;
        this.parent = parent;

        this.path = buildPath(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomDepartment getParent() {
        return parent;
    }

    public void setParent(CustomDepartment parent) {
        this.parent = parent;
    }

    public String getPath() {
        this.path = buildPath(this);
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private String buildPath(CustomDepartment customDepartment) {
        String nome = PATH_SEPARATOR + customDepartment.getName();

        if (customDepartment.getParent() != null) {
            nome = buildPath(customDepartment.getParent()) + nome;
        }

        return nome;
    }

}