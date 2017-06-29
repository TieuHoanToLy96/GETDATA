package ulti;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import model.CategoryVocabulary;
import model.Lesson;
import model.Vocabulary;

/**
 * Created by TieuHoan on 14/05/2017.
 */

public class Data {

    private static final String NAME_DATABASE = "japan";
    public String PATH = Environment.getDataDirectory() +
            "/data/com.example.tieuhoan.getdata/databases/japan";
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    private static final String LESSON_TABLE = "LESSON";
    private static final String ID_LESSON = "ID", NAME_LESSON = "NAMELESSON", IMAGE_LESSON = "IMAGELESSON", DESCRIPTION_LESSON = "DESCRIPTION", STT_LESSON = "STTLESSON", AUDIO_LESSON = "AUDIO", PDF_LESSON = "PDF";
    private static final String CATEGORY_TABLE = "category";

    private static final String ID_CATEGORY = "id", VIETNAMESE_CATEGORY = "vietnamese", THUM_CATEGORY = "thumbnail";
    private static String VOCABULARY_TABLE = "vocabulary", CATEGORY_ID_VOC = "category_id", ROMANJI_VOC = "romanji", JAPANESE_VOC = "japanese", VIETNAMESE_VOC = "vietnamese";

    public ArrayList<Lesson> getLesson() {
        openDataBase();
        ArrayList<Lesson> lessons = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(LESSON_TABLE, null, null, null, null, null, null);
        int indexNAME_LESSON = cursor.getColumnIndex(NAME_LESSON);
        int indexIMAGE_LESSON = cursor.getColumnIndex(IMAGE_LESSON);
        int indexDESCRIPTION_LESSON = cursor.getColumnIndex(DESCRIPTION_LESSON);
        int indexAUDIO_LESSON = cursor.getColumnIndex(AUDIO_LESSON);
        int indexPDF_LESSON = cursor.getColumnIndex(PDF_LESSON);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String name_lesson = cursor.getString(indexNAME_LESSON);
            String image_lesson = cursor.getString(indexIMAGE_LESSON);
            String description_lesson = cursor.getString(indexDESCRIPTION_LESSON);
            String audio_lesson = cursor.getString(indexAUDIO_LESSON);
            String pdf_lesson = cursor.getString(indexPDF_LESSON);

            Lesson lesson = new Lesson(image_lesson, description_lesson, name_lesson, audio_lesson, pdf_lesson);
            lessons.add(lesson);
            if (lessons.size() == 44) break;

            cursor.moveToNext();
        }
        closeDataBase();
        return lessons;
    }


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


    public ArrayList<Vocabulary> getVocabulary(String idCategory) {


        openDataBase();
        ArrayList<Vocabulary> vocabularies = new ArrayList<>();
        Vocabulary vocabulary;

        String[] tableColumns = new String[]{
                CATEGORY_ID_VOC, ROMANJI_VOC, JAPANESE_VOC, VIETNAMESE_VOC
        };
        String whereClause = CATEGORY_ID_VOC + " = ? OR " + CATEGORY_ID_VOC + " = ?";
        String[] whereArgs = new String[]{
                idCategory
        };

        Cursor cursor = sqLiteDatabase.query(VOCABULARY_TABLE, tableColumns, whereClause, whereArgs, null, null, null);
        int indexCATEGORY_ID_VOC = cursor.getColumnIndex(CATEGORY_ID_VOC);
        int indexROMANJI_VOC = cursor.getColumnIndex(ROMANJI_VOC);
        int indexJAPANESE_VOC = cursor.getColumnIndex(JAPANESE_VOC);
        int indexVIETNAMESE_VOC = cursor.getColumnIndex(VIETNAMESE_VOC);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int category_id = cursor.getInt(indexCATEGORY_ID_VOC);
            String romaji = cursor.getString(indexROMANJI_VOC);
            String japanese = cursor.getString(indexJAPANESE_VOC);
            String vietnamese = cursor.getString(indexVIETNAMESE_VOC);
            vocabulary = new Vocabulary(romaji, japanese, vietnamese, category_id);
            vocabularies.add(vocabulary);
            cursor.moveToNext();
        }

        closeDataBase();

        closeDataBase();
        return vocabularies;

    }

    public void putCategory(CategoryVocabulary vocabulary) {

        openDataBase();
        ContentValues cv = new ContentValues();
        cv.put(ID_CATEGORY, vocabulary.getId());
        cv.put(VIETNAMESE_CATEGORY, vocabulary.getVietnamese());
        cv.put(THUM_CATEGORY, vocabulary.getThumbnail());
        sqLiteDatabase.insert(CATEGORY_TABLE, null, cv);
        Log.e("tieuhoan", "putCategory");
        closeDataBase();

    }


    public Data(Context context) {
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

    public void putVocabulary(Vocabulary vocabulary) {
        openDataBase();
        ContentValues cv = new ContentValues();
        cv.put(CATEGORY_ID_VOC, vocabulary.getCategory_id());
        cv.put(ROMANJI_VOC, vocabulary.getRomaji());
        cv.put(JAPANESE_VOC, vocabulary.getJapanese());
        cv.put(VIETNAMESE_VOC, vocabulary.getVietnamese());
        sqLiteDatabase.insert(VOCABULARY_TABLE, null, cv);
        Log.e("tieuhoan", "insert success");
        closeDataBase();

    }

    public void putDataToLessonTable(Lesson lesson) {
        openDataBase();
        ContentValues cv = new ContentValues();
        cv.put(NAME_LESSON, lesson.getName());
        cv.put(IMAGE_LESSON, lesson.getImage());
        cv.put(DESCRIPTION_LESSON, lesson.getDescription());
        cv.put(AUDIO_LESSON, lesson.getLinkAudio());
        cv.put(PDF_LESSON, lesson.getLinkPdf());
        sqLiteDatabase.insert(LESSON_TABLE, ID_LESSON, cv);
        Log.e("tieuhoan", "success");
        closeDataBase();
    }

}
