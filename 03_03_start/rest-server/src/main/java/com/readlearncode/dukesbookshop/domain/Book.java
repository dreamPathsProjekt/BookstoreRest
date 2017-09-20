
package com.readlearncode.dukesbookshop.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author i.dritsas
 */
@XmlRootElement //JAXB type works for Json too.
public class Book implements Serializable{

    private String id;
    private String title;
    private String description;
    private List<Author> authors;
    private Float price;
    private String imageFileName;
    private String link;
    private Date published;

    public Book() {
    }

    //If we don't create a no-argument constructor serialization and de-serialization fails.
    
    public Book(String id, String title, String description, List<Author> authors, Float price, String imageFileName, String link, Date published) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.authors = authors;
        this.price = price;
        this.imageFileName = imageFileName;
        this.link = link;
        this.published = published;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.title);
        hash = 79 * hash + Objects.hashCode(this.description);
        hash = 79 * hash + Objects.hashCode(this.authors);
        hash = 79 * hash + Objects.hashCode(this.price);
        hash = 79 * hash + Objects.hashCode(this.imageFileName);
        hash = 79 * hash + Objects.hashCode(this.link);
        hash = 79 * hash + Objects.hashCode(this.published);
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
        final Book other = (Book) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.imageFileName, other.imageFileName)) {
            return false;
        }
        if (!Objects.equals(this.link, other.link)) {
            return false;
        }
        if (!Objects.equals(this.authors, other.authors)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.published, other.published)) {
            return false;
        }
        return true;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }
    
    
}
