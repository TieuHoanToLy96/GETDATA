package model;

/**
 * Created by TieuHoan on 17/05/2017.
 */

public class ContentLesson {
    String kana_name, romaji_name, kana, romaji, vn, audio;


    public String getKana_name() {
        return kana_name;
    }

    public void setKana_name(String kana_name) {
        this.kana_name = kana_name;
    }

    public String getRomaji_name() {
        return romaji_name;
    }

    public void setRomaji_name(String romaji_name) {
        this.romaji_name = romaji_name;
    }

    public String getKana() {
        return kana;
    }

    public void setKana(String kana) {
        this.kana = kana;
    }

    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

    public String getVn() {
        return vn;
    }

    public void setVn(String vn) {
        this.vn = vn;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public ContentLesson(String kana_name, String romaji_name, String kana, String romaji, String vn, String audio) {
        this.kana_name = kana_name;
        this.romaji_name = romaji_name;
        this.kana = kana;
        this.romaji = romaji;
        this.vn = vn;
        this.audio = audio;

    }
}
