
package com.readlearncode.dukesbookshop.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author i.dritsas
 */
@XmlRootElement
public class Hypermedia {
    
    private List<LinkResource> linkResources = new ArrayList<>();
    
    public void addLinkResource(LinkResource linkResource){
        this.linkResources.add(linkResource);
    }

    public List<LinkResource> getLinkResources() {
        return linkResources;
    }

    public void setLinkResources(List<LinkResource> linkResources) {
        this.linkResources = linkResources;
    }
    
    
}
