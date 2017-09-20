
package com.readlearncode.dukesbookshop.domain;

import java.net.URI;
import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author i.dritsas
 */
@XmlRootElement
public class LinkResource {
    
    //Logical Relationship
    private String rel;
    
    //HTTP method type
    private String type;
    
    //Link
    private URI uri;

    public LinkResource() {}
        
    //Important: accepts Link input
    public LinkResource(Link link) {
        this.rel = link.getRel();
        this.type = link.getType();
        this.uri = link.getUri();
    }

    
    
    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
    
    
}
