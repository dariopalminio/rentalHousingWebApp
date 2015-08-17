package com.daro.rental.housing.faces;

import javax.faces.FactoryFinder;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * So you need a way to instantiate the 2.0 FacesContext in a Filter, but when you use the same method that you have in the past, you get NullPointerExceptions all over the place when attempting to access any values through El. The ScopedAttributeElResolver bombs when attempting to set values or access methods in backing beans.) It’s not too hard to get this working again. 
 *
 */
public class FacesContextBuilder
{
    public FacesContext getFacesContext(final ServletRequest request, final ServletResponse response)
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null)
        {
            return facesContext;
        }
 
        FacesContextFactory contextFactory = (FacesContextFactory) FactoryFinder
                .getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
        LifecycleFactory lifecycleFactory = (LifecycleFactory) FactoryFinder
                .getFactory(FactoryFinder.LIFECYCLE_FACTORY);
        Lifecycle lifecycle = lifecycleFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);
 
        ServletContext servletContext = ((HttpServletRequest) request).getSession().getServletContext();
        facesContext = contextFactory.getFacesContext(servletContext, request, response, lifecycle);
        InnerFacesContext.setFacesContextAsCurrentInstance(facesContext);
 
        return facesContext;
    }
 
    public void removeFacesContext()
    {
        InnerFacesContext.setFacesContextAsCurrentInstance(null);
    }
 
    private abstract static class InnerFacesContext extends FacesContext
    {
        protected static void setFacesContextAsCurrentInstance(final FacesContext facesContext)
        {
            FacesContext.setCurrentInstance(facesContext);
        }
    }
 
}