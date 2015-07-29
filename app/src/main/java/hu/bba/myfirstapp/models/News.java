package hu.bba.myfirstapp.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "item")
public class News implements Serializable {

    @Element(name = "title")
    private String name;

    @Element(name = "pubDate")
    private String pubDate;

    @Element(name = "description")
    private String description;

    @Element(name = "address")
    private Address address;

    @Element(name = "image")
    private Image image;

    public News(String name, String pubDate, String description, Address address, Image image) {
        this.name = name;
        this.pubDate = pubDate;
        this.description = description;
        this.address = address;
        this.image = image;
    }

    News() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
