package model;

/**
 * Created by TieuHoan on 17/05/2017.
 */

public class DetailLesson {
    String detail, main_audio, grammar;

    public DetailLesson(String detail, String grammar, String main_audio) {
        this.detail = detail;
        this.grammar = grammar;
        this.main_audio = main_audio;
    }

    public DetailLesson() {
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getMain_audio() {
        return main_audio;
    }

    public void setMain_audio(String main_audio) {
        this.main_audio = main_audio;
    }

    public String getGrammar() {
        return grammar;
    }

    public void setGrammar(String grammar) {
        this.grammar = grammar;
    }
}
