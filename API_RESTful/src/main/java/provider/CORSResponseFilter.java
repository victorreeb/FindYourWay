/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provider;

import java.io.IOException;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author remaki
 */
@Provider
@PreMatching
public class CORSResponseFilter  implements ContainerResponseFilter {

   
private final static Logger log = Logger.getLogger(CORSResponseFilter.class.getName());
    @Override
    public void filter(ContainerRequestContext requestCtx, ContainerResponseContext responseCtx) throws IOException {
        
        
        log.info("Execution du filtre Response");
        
        responseCtx.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseCtx.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseCtx.getHeaders().add("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT");
        responseCtx.getHeaders().add("Access-Control-Allow-Headers", "content-type");
        
    } 
        
        
        
        
    
    
}
