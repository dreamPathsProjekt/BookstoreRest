
package com.readlearncode.dukesbookshop.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author i.dritsas
 */
@XmlRootElement
public class Author implements Serializable{
    
    private String id;
    private String firstName;
    private String lastName;
    private String blogURL;

    public Author() {
    }

    public Author(String id, String firstName, String lastName, String blogURL) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.blogURL = blogURL;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.firstName);
        hash = 79 * hash + Objects.hashCode(this.lastName);
        hash = 79 * hash + Objects.hashCode(this.blogURL);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Author other = (Author) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.blogURL, other.blogURL)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Author{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", blogURL=" + blogURL + '}';
    }

    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBlogURL() {
        return blogURL;
    }

    public void setBlogURL(String blogURL) {
        this.blogURL = blogURL;
    }
    
    
}
