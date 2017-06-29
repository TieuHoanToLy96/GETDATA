package ulti;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import model.CategoryVocabulary;
import model.Vocabulary;

/**
 * Created by TieuHoan on 18/05/2017.
 */

public class Test {


    private static final String NAME_DATABASE = "learnjapanese";
    public String PATH = Environment.getDataDirectory() +
            "/data/com.example.tieuhoan.getdata/databases/learnjapanese";
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    private static final String CATEGORY_TABLE = "category";

    private static final String ID_CATEGORY = "_id", VIETNAMESE_CATEGORY = "vietnamese", THUM_CATEGORY = "thumbnail";

    private static final String VOCABULARY_TABLE = "phrase", CATEGORY_ID_VOC = "category_id", ROMANJI_VOC = "pinyin", JAPANESE_VOC = "japanese", VIETNAMESE_VOC = "vietnamese";

    public ArrayList<CategoryVocabulary> getCategoryVocabulary() {
        openDataBase();
        ArrayList<CategoryVocabulary> categorys = new ArrayList<>();
        CategoryVocabulary category;
        Cursor cursor = sqLiteDatabase.query(CATEGORY_TABLE, null, null, null, null, null, null);
        int indexID_CATEGORY = cursor.getColumnIndex(ID_CATEGORY);
        int indexVIETNAMESE_CATEGORY = cursor.getColumnIndex(VIETNAMESE_CATEGORY);
        int indexTHUM_CATEGORY = cursor.getColumnIndex(THUM_CATEGORY);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(indexID_CATEGORY);
            String vietnamese = cursor.getString(indexVIETNAMESE_CATEGORY);
            String thumbnail = cursor.getString(indexTHUM_CATEGORY);
            category = new CategoryVocabulary(id, thumbnail, vietnamese);
            categorys.add(category);
            cursor.moveToNext();
        }

        closeDataBase();


        return categorys;
    }

    public ArrayList<Vocabulary> getAllVocabulary() {
        openDataBase();
        ArrayList<Vocabulary> vocabularies = new ArrayList<>();
        Vocabulary vocabulary;
        Cursor cursor = sqLiteDatabase.query(VOCABULARY_TABLE, null, null, null, null, null, null);
        int indexCATEGORY_ID_VOC = cursor.getColumnIndex(CATEGORY_ID_VOC);
        int indexROMANJI_VOC = cursor.getColumnIndex(ROMANJI_VOC);
        int indexJAPANESE_VOC = cursor.getColumnIndex(JAPANESE_VOC);
        int indexVIETNAMESE_VOC = cursor.getColumnIndex(VIETNAMESE_VOC);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int category_id = cursor.getInt(indexCATEGORY_ID_VOC);
            String romanji = cursor.getString(indexROMANJI_VOC);
            String japanese = cursor.getString(indexJAPANESE_VOC);
            String vietnamese = cursor.getString(indexVIETNAMESE_VOC);
            vocabulary = new Vocabulary(romanji, japanese, vietnamese, category_id);
            vocabularies.add(vocabulary);
            cursor.moveToNext();
        }

        closeDataBase();
        return vocabularies;
    }

    public Test(Context context) {
        this.context = context;
        try {
            copyDatabase(PATH, NAME_DATABASE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openDataBase() {
        sqLiteDatabase = context.openOrCreateDatabase(PATH, Context.MODE_PRIVATE, null);
    }

    private void closeDataBase() {
        sqLiteDatabase.close();
    }

    private void copyDatabase(String path, String nameDataBase) throws Exception {

        File file = new File(path);
        if (!file.exists()) {
            File parent = file.getParentFile();
            parent.mkdirs();
            file.createNewFile();
            InputStream inputStream = context.getAssets().open(nameDataBase);
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] b = new byte[1024];
            int count = inputStream.read(b);
            while (count != -1) {
                outputStream.write(b, 0, count);
                count = inputStream.read(b);
            }
            outputStream.close();
            inputStream.close();
        }


    }
}
