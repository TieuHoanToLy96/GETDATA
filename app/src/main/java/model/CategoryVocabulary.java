package model;

/**
 * Created by TieuHoan on 18/05/2017.
 */

public class CategoryVocabulary {
    private int id;
    private String vietnamese, thumbnail;

    public CategoryVocabulary(int id, String thumbnail, String vietnamese) {
        this.id = id;
        this.thumbnail = thumbnail;
        this.vietnamese = vietnamese;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVietnamese() {
        return vietnamese;
    }

    public void setVietnamese(String vietnamese) {
        this.vietnamese = vietnamese;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
