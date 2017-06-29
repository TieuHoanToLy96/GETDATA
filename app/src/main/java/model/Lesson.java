package model;

import java.io.Serializable;

/**
 * Created by TieuHoan on 14/05/2017.
 */

public class Lesson  implements Serializable{
    private String image, description, name  ,linkAudio ,linkPdf;

    public Lesson() {
    }

    public Lesson(String image, String description, String name, String linkAudio, String linkPdf) {
        this.image = image;
        this.description = description;
        this.name = name;
        this.linkAudio = linkAudio;
        this.linkPdf = linkPdf;
    }

    public String getLinkAudio() {
        return linkAudio;
    }

    public void setLinkAudio(String linkAudio) {
        this.linkAudio = linkAudio;
    }

    public String getLinkPdf() {
        return linkPdf;
    }

    public void setLinkPdf(String linkPdf) {
        this.linkPdf = linkPdf;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
