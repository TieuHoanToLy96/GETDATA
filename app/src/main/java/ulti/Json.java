package ulti;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import model.Alphabet;
import model.ContentLesson;
import model.DetailLesson;

/**
 * Created by TieuHoan on 14/05/2017.
 */

public class Json {

    public String urlAssetAlphabets = "dulieu/kana.json";
    public String urlAssetContentLessons = "dulieu/";
    private Context context;

    public Json(Context context) {
        this.context = context;
    }


    public String loadAlphabets(String urlAsset) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(urlAsset);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public DetailLesson getDetailLesson(String sttLesson) {
        DetailLesson detailLesson = new DetailLesson();
        JSONObject obj = null;
        try {
            obj = new JSONObject(loadAlphabets(urlAssetContentLessons + "lesson_" + sttLesson + ".json"));
            JSONObject lesson = obj.getJSONArray("lesson_" + sttLesson).getJSONObject(0);

            String detail = lesson.getString("detail");
            String main_audio = lesson.getString("main_audio");
            String grammar = lesson.getString("grammar");
            detailLesson = new DetailLesson(detail, grammar, main_audio);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return detailLesson;

    }

    public ArrayList<ContentLesson> getContentLesson(String sttLesson) {

        ArrayList<ContentLesson> contentLessons = new ArrayList<>();

        try {

            JSONObject obj = new JSONObject(loadAlphabets(urlAssetContentLessons + "lesson_" + sttLesson + ".json"));

            JSONArray dialogue = obj.getJSONArray("lesson_" + sttLesson).getJSONObject(0).getJSONArray("dialogue");

            for (int i = 0; i < dialogue.length(); i++) {
                JSONObject jsonObject = dialogue.getJSONObject(i);
                String kana_name = jsonObject.getString("kana_name");
                String romaji_name = jsonObject.getString("romaji_name");
                String kana = jsonObject.getString("kana");
                String romaji = jsonObject.getString("romaji");
                String vn = jsonObject.getString("vn");
                String audio = jsonObject.getString("audio");

                ContentLesson contentLesson = new ContentLesson(kana_name, romaji_name, kana, romaji, vn, audio);
                contentLessons.add(contentLesson);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return contentLessons;
    }

    public ArrayList<Alphabet> getAlphabets() {


        ArrayList<Alphabet> alphabets;
        alphabets = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(loadAlphabets(urlAssetAlphabets));

            JSONArray kana = obj.getJSONArray("kana");

            for (int i = 0; i < kana.length(); i++) {
                JSONObject jsonObject = kana.getJSONObject(i);
                String hiragana = jsonObject.getString("hiragana");
                String katakana = jsonObject.getString("katakana");
                String romaji = jsonObject.getString("romaji");

                Alphabet alphabet = new Alphabet(hiragana, katakana, romaji);
                alphabets.add(alphabet);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return alphabets;
    }

}
