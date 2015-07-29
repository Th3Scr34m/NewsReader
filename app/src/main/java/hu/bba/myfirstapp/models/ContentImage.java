package hu.bba.myfirstapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ContentImage implements Serializable {

    @SerializedName("width")
    public int imageWidth;

    @SerializedName("height")
    public int imageHeight;

    @SerializedName("tbWidth")
    public int imageThumbnailWidth;

    @SerializedName("tbHeight")
    public int imageThumbnailHeight;

    @SerializedName("GsearchResultClass")
    public String searchResult;

    @SerializedName("imageId")
    public String imageId;

    @SerializedName("titleNoFormatting")
    public String formattedTitle;

    @SerializedName("title")
    public String unformattedTitle;

    @SerializedName("contentNoFormatting")
    public String formattedContent;

    @SerializedName("content")
    public String unformattedContent;

    @SerializedName("url")
    public String formattedUrl;

    @SerializedName("unescapedUrl")
    public String unformattedUrl;

    @SerializedName("originalContextUrl")
    public String originalContextUrl;

    @SerializedName("visibleUrl")
    public String visibleUrl;

    @SerializedName("tbUrl")
    public String thumbnailUrl;

    ContentImage() {
    }

    public ContentImage(int imageWidth, int imageHeight, int imageThumbnailWidth,
                        int imageThumbnailHeight, String searchResult, String imageId,
                        String formattedTitle, String unformattedTitle, String formattedContent,
                        String unformattedContent, String formattedUrl, String unformattedUrl,
                        String originalContextUrl, String visibleUrl, String thumbnailUrl) {
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.imageThumbnailWidth = imageThumbnailWidth;
        this.imageThumbnailHeight = imageThumbnailHeight;
        this.searchResult = searchResult;
        this.imageId = imageId;
        this.formattedTitle = formattedTitle;
        this.unformattedTitle = unformattedTitle;
        this.formattedContent = formattedContent;
        this.unformattedContent = unformattedContent;
        this.formattedUrl = formattedUrl;
        this.unformattedUrl = unformattedUrl;
        this.originalContextUrl = originalContextUrl;
        this.visibleUrl = visibleUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getImageThumbnailWidth() {
        return imageThumbnailWidth;
    }

    public void setImageThumbnailWidth(int imageThumbnailWidth) {
        this.imageThumbnailWidth = imageThumbnailWidth;
    }

    public int getImageThumbnailHeight() {
        return imageThumbnailHeight;
    }

    public void setImageThumbnailHeight(int imageThumbnailHeight) {
        this.imageThumbnailHeight = imageThumbnailHeight;
    }

    public String getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(String searchResult) {
        this.searchResult = searchResult;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getFormattedTitle() {
        return formattedTitle;
    }

    public void setFormattedTitle(String formattedTitle) {
        this.formattedTitle = formattedTitle;
    }

    public String getUnformattedTitle() {
        return unformattedTitle;
    }

    public void setUnformattedTitle(String unformattedTitle) {
        this.unformattedTitle = unformattedTitle;
    }

    public String getFormattedContent() {
        return formattedContent;
    }

    public void setFormattedContent(String formattedContent) {
        this.formattedContent = formattedContent;
    }

    public String getUnformattedContent() {
        return unformattedContent;
    }

    public void setUnformattedContent(String unformattedContent) {
        this.unformattedContent = unformattedContent;
    }

    public String getFormattedUrl() {
        return formattedUrl;
    }

    public void setFormattedUrl(String formattedUrl) {
        this.formattedUrl = formattedUrl;
    }

    public String getUnformattedUrl() {
        return unformattedUrl;
    }

    public void setUnformattedUrl(String unformattedUrl) {
        this.unformattedUrl = unformattedUrl;
    }

    public String getOriginalContextUrl() {
        return originalContextUrl;
    }

    public void setOriginalContextUrl(String originalContextUrl) {
        this.originalContextUrl = originalContextUrl;
    }

    public String getVisibleUrl() {
        return visibleUrl;
    }

    public void setVisibleUrl(String visibleUrl) {
        this.visibleUrl = visibleUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
