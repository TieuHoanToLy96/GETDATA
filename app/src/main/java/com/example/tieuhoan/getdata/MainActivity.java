package com.example.tieuhoan.getdata;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import model.Lesson;
import ulti.DownloadTask;
import ulti.FragmentControl;
import view.TagFragment;


public class MainActivity extends AppCompatActivity {


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPermission();
        File myDirectory = new File(Environment.getExternalStorageDirectory(), DownloadTask.FOLDER);
        if (!myDirectory.exists()) {
            myDirectory.mkdirs();
        }

        FragmentControl.goToFragmentNoAddBackStack(R.id.framelayout, new TagFragment(), MainActivity.this);

    }


    boolean doublePressBack = false;

    @Override
    public void onBackPressed() {

        // Hide keyboard
        View view = MainActivity.this.getCurrentFocus();
        if (view != null && view instanceof EditText) {
            InputMethodManager im = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }


        // pop back stack
        if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
            getSupportFragmentManager().popBackStack();
        } else {

            if (!doublePressBack) {
                doublePressBack = true;
                Toast.makeText(MainActivity.this, "Ấn back lần nữa để thoát !!!", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doublePressBack = false;
                    }
                }, 2000);

            } else {
                super.onBackPressed();
            }
        }
    }

    private String linkLesson = "https://www.nhk.or.jp/lesson/vietnamese/learn/list/";
    private String linkDownLoadLesson = "https://www.nhk.or.jp/lesson/vietnamese/download/";
    private ArrayList<Lesson> arrayLesson;


//    public class LessonDownLoad extends AsyncTask<Void, Void, Void> {
//
//
//        @Override
//        protected Void doInBackground(Void... params) {
//
//            Document document = null;
//
//            try {
//                document = Jsoup.connect(linkDownLoadLesson).data("query", "Java")
//                        .userAgent("Mozilla")
//                        .cookie("auth", "token")
//                        .timeout(100000)
//                        .get();
//
//
//                Elements lessons = document.select("ul.dl-list > li.dl-list-inner");
//                ArrayList<String> audios = new ArrayList<>();
//                ArrayList<String> pdfs = new ArrayList<>();
//                for (int i = 0; i < lessons.size(); i++) {
//                    String downAudio = lessons.get(i).select("ul.clearfix > li").get(0).select("a[href$=.mp3]").attr("href");
//                    String downPdf = lessons.get(i).select("ul.clearfix > li").get(1).select("a[href$=.file_pdf]").attr("href");
//                    if (!downAudio.equals("") && !downPdf.equals("")) {
//                        String audio = "https://www.nhk.or.jp" + downAudio;
//                        String file_pdf = "https://www.nhk.or.jp" + downPdf;
//                        audios.add(audio);
//                        pdfs.add(file_pdf);
//
//                    }
//
//                }
//
//                for (int i = 0; i < arrayLesson.size(); i++) {
//                    arrayLesson.get(i).setLinkPdf(pdfs.get(i));
//                    arrayLesson.get(i).setLinkAudio(audios.get(i));
//                }
//
//                for (Lesson lesson : arrayLesson) {
//                    Data data = new Data(MainActivity.this);
//                    data.putDataToLessonTable(lesson);
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }
//
//    public class LessonHtml extends AsyncTask<Void, Void, Void> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            Document document = null;
//            arrayLesson = new ArrayList<>();
//
//
//            try {
//                document = Jsoup.connect(linkLesson).data("query", "Java")
//                        .userAgent("Mozilla")
//                        .cookie("auth", "token")
//                        .timeout(100000)
//                        .get();
//
//
//                Elements lessons = document.select("div#w-box");
//                Elements lessons2 = lessons.get(0).select("li > a");
//
//                for (int i = 0; i < lessons2.size(); i++) {
//                    String imageLesson = "https://www.nhk.or.jp" + lessons2.get(i).select("img").get(0).attr("src");
//
//                    String nameLesson = lessons2.get(i).select("h2").get(0).text();
//                    String sttLesson = lessons2.get(i).select("h2 > span").get(0).text();
//                    String description = lessons2.get(i).select("p").get(0).text();
//
////                    Log.e("tieuhoan", "imageLesson :" + imageLesson);
////                    Log.e("tieuhoan", "sttLesson :" + sttLesson);
////                    Log.e("tieuhoan", "nameLesson :" + nameLesson);
////                    Log.e("tieuhoan", "description :" + description);
//                    Lesson lesson = new Lesson();
//                    lesson.setImage(imageLesson);
//                    lesson.setStt(sttLesson);
//                    lesson.setName(nameLesson);
//                    lesson.setDescription(description);
//
//                    arrayLesson.add(lesson);
//
//
////                    Data data = new Data(MainActivity.this);
////                    data.putDataToLessonTable(lesson);
//                }
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//
//            new LessonDownLoad().execute();
//        }
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != 1) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
