package model;

/**
 * Created by TieuHoan on 14/05/2017.
 */

public class Alphabet {
    private String hiragana, katakana, romaji;

    public Alphabet(String hiragana, String katakana, String romaji) {
        this.hiragana = hiragana;
        this.katakana = katakana;
        this.romaji = romaji;
    }

    public Alphabet() {
    }

    public String getHiragana() {
        return hiragana;
    }

    public void setHiragana(String hiragana) {
        this.hiragana = hiragana;
    }

    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

    public String getKatakana() {
        return katakana;
    }

    public void setKatakana(String katakana) {
        this.katakana = katakana;
    }
}
