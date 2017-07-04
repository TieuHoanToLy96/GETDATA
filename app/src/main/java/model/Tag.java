package model;

/**
 * Created by TieuHoan on 04/07/2017.
 */

public class Tag {
    private String nameTag, quotation;
    private int imageTag;

    public Tag() {
    }

    public Tag(int imageTag, String quotation, String nameTag) {
        this.imageTag = imageTag;
        this.quotation = quotation;
        this.nameTag = nameTag;
    }

    public String getNameTag() {
        return nameTag;
    }

    public Tag setNameTag(String nameTag) {
        this.nameTag = nameTag;
        return  this;
    }

    public String getQuotation() {
        return quotation;
    }

    public Tag setQuotation(String quotation) {
        this.quotation = quotation;
        return this ;
    }

    public int getImageTag() {
        return imageTag;
    }

    public Tag setImageTag(int imageTag) {
        this.imageTag = imageTag;
        return this;
    }
}
