package org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom;

/**
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.model.CustomRole;
import org.jboss.as.quickstarts.picketlink.authorization.idm.jpa.custom.model.CustomResource;
import org.picketlink.Identity;
import org.picketlink.idm.model.Partition;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.io.IOException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Simple {@Filter} to protected companies resources from unauthorized access.</p>
 */
@WebFilter(urlPatterns = ResourceProtectionFilter.PROTECTED_BASE_URI + "/*")
public class ResourceProtectionFilter implements Filter {

    public static final String PROTECTED_BASE_URI = "/protected";
    public static final String APPLICATION_NAME = "Picketlink Authorization IDM JPA Custom";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Inject
    private Instance<Identity> identityInstance;

    @Inject
    private AuthorizationChecker authorizationChecker;

    @Inject
    private AuthorizationData authorizationData;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        boolean isAuthorized = getIdentity().isLoggedIn();

        if (authorizationData.getDepartment() != null) {
            if (getIdentity().isLoggedIn() && isProtectedResource(httpRequest)) {
                isAuthorized = isUserBaseRealmURI(httpRequest);
            }

            if (isAuthorized) {
                chain.doFilter(httpRequest, httpResponse);
            } else {
                forwardAccessDeniedPage(httpRequest, httpResponse);
            }
        } else {
            // Necessary to select a department
            forwardAccessAssignDepartment(httpRequest, httpResponse);
        }
    }

    private boolean isUserBaseRealmURI(HttpServletRequest httpRequest) {
        Identity identity = getIdentity();
        Partition partition = identity.getAccount().getPartition(); // this is safe

        // Load roles associed with the selected department
        List<CustomRole> roles = authorizationChecker.getRoles(authorizationData.getDepartment());

        // Load authorized roles
        Set<CustomResource> customResources = new HashSet<CustomResource>();
        for (CustomRole role : roles) {
            customResources.addAll(authorizationChecker.getResources(role));
        }

        boolean grantAccess = false;

        for (CustomResource customResource : customResources) {
            String allowedRealmBaseURI = getRealmBaseURI(httpRequest) + "/" + customResource.getPath();
            if (httpRequest.getRequestURI().startsWith(allowedRealmBaseURI)) {
                grantAccess = true;
            }
        }

        // let's check if the user trying to access a resource from his realm. if not deny.
        return grantAccess;
    }

    private void forwardAccessAssignDepartment(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {
        httpRequest.getServletContext().getRequestDispatcher("/protected/department.jsf").forward(httpRequest, httpResponse);
    }

    private void forwardAccessDeniedPage(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {
        httpRequest.getServletContext().getRequestDispatcher("/accessDenied.jsf").forward(httpRequest, httpResponse);
    }

    private String getRealmBaseURI(HttpServletRequest httpRequest) {
        return httpRequest.getContextPath() + PROTECTED_BASE_URI;
    }

    private boolean isProtectedResource(HttpServletRequest request) {
        boolean protectedResource = true;

        if (request.getRequestURI().equals(getRealmBaseURI(request) + "/index.jsf") ||
            request.getRequestURI().equals(getRealmBaseURI(request) + "/department.jsf")) {
            protectedResource = false;
        }

        return protectedResource;
    }

    private Identity getIdentity() {
        return this.identityInstance.get();
    }

    @Override
    public void destroy() {
    }
}

