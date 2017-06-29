package model;

import android.os.Parcel;

/**
 * Created by TieuHoan on 21/05/2017.
 */

public class Vocabulary{
    private String romaji, japanese, vietnamese;
    private int category_id;



    public Vocabulary(String romaji, String japanese, String vietnamese, int category_id) {
        this.romaji = romaji;
        this.japanese = japanese;
        this.vietnamese = vietnamese;
        this.category_id = category_id;
    }

    public Vocabulary(String romaji, String japanese) {
        this.romaji = romaji;
        this.japanese = japanese;
    }

    protected Vocabulary(Parcel in) {
        romaji = in.readString();
        japanese = in.readString();
        vietnamese = in.readString();
        category_id = in.readInt();
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getVietnamese() {
        return vietnamese;
    }

    public void setVietnamese(String vietnamese) {
        this.vietnamese = vietnamese;
    }

    public String getJapanese() {
        return japanese;
    }

    public void setJapanese(String japanese) {
        this.japanese = japanese;
    }

    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

}
