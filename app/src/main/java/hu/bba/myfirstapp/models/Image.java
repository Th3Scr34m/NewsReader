package hu.bba.myfirstapp.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root()
public class Image implements Serializable {

    @Attribute(name = "url", required = false)
    private String url;

    @Attribute(name = "caption", required = false)
    private String caption;

    public Image(String url, String caption) {
        this.url = url;
        this.caption = caption;
    }

    Image() {}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }


    @Override
    public String toString() {
        return "Image { " + "url= " + url + ", caption=" + caption + "}";
    }
}
