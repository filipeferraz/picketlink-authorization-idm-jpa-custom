package org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom;


import org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.model.CustomDepartment;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * This is a utility bean that may be used to store session attributes used in
 * authorization process.
 *
 * @author Filipe Ferraz
 */
@Named
@SessionScoped
public class AuthorizationData implements Serializable {

    private CustomDepartment department;

    public void clear() {
        department = null;
    }

    public CustomDepartment getDepartment() {
        return department;
    }

    public void setDepartment(CustomDepartment department) {
        this.department = department;
    }

}
