package com.example.tieuhoan.getdata;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import model.Alphabet;
import model.CategoryVocabulary;
import model.Lesson;
import model.Tag;
import model.VocabularyMinanoLesson;
import ulti.Data;
import ulti.DownloadTask;
import ulti.FragmentControl;
import ulti.Json;
import view.ListCategoryFragment;
import view.ListLessonFragment;
import view.ListVocabularyMinanoFragment;
import view.TagFragment;
import view.ViewPagerAlphabetFragment;
import view.ViewPagerFileFragment;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;
    private ArrayList<Alphabet> alphabets;
    private Toolbar toolbar;
    private ArrayList<Lesson> lessons;
    private ArrayList<CategoryVocabulary> categorys;
    private ArrayList<VocabularyMinanoLesson> minanoLessons;
    private VocabularyMinanoLesson minanoLesson;
    private ArrayList<Tag> tags;
    private Tag tag;
    private ArrayList<Fragment> fragments;
    private TagFragment tagFragment;
    private boolean isMainMenu;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
        initPermission();
        iniTag();
        initToolbar();
        createFolder();
        isMainMenu = true;
        tagFragment = new TagFragment(tags, fragments);
        FragmentControl.goToFragmentNoAddBackStack(R.id.framelayoutToolBar, tagFragment, MainActivity.this);

    }

    private void getData() {
        alphabets = getAlphabets();
        lessons = getLesson();
        categorys = getCategorys();
        minanoLessons = createNameMinanoLesson();
    }

    private void initToolbar() {
        toolbar = (Toolbar) this.findViewById(R.id.my_toolbar);
//        toolbar.setTitle("Tiếng nhật cơ bản");
        toolbar.setTitleTextColor(Color.WHITE);
//        toolbar.setNavigationIcon(R.drawable.menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
//        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


    private ViewPagerAlphabetFragment viewPagerAlphabetFragment;
    private ListVocabularyMinanoFragment listVocabularyMinanoFragment;
    private ListLessonFragment listLessonFragment;
    private ViewPagerFileFragment viewPagerFileFragment;
    private ListCategoryFragment listCategoryFragment;

    private void iniTag() {
        fragments = new ArrayList<>();
        getData();

        viewPagerAlphabetFragment = new ViewPagerAlphabetFragment(false, alphabets);
        listVocabularyMinanoFragment = new ListVocabularyMinanoFragment(minanoLessons);
        listLessonFragment = new ListLessonFragment(lessons);
        viewPagerFileFragment = new ViewPagerFileFragment();
        listCategoryFragment = new ListCategoryFragment(categorys);

        fragments.add(viewPagerAlphabetFragment);
        fragments.add(listVocabularyMinanoFragment);
        fragments.add(listLessonFragment);
        fragments.add(viewPagerFileFragment);
        fragments.add(listCategoryFragment);
        tags = new ArrayList<>();
        tag = new Tag();
        tag.setImageTag(R.mipmap.tag1).setNameTag("Bảng chữ cái").setQuotation("( Học hành vất vả kết quả ngọt bùi )");
        tags.add(tag);

        tag = new Tag();
        tag.setImageTag(R.mipmap.tag2).setNameTag("Từ vựng Minano Nihongo").setQuotation("( Học để làm người )");
        tags.add(tag);

        tag = new Tag();
        tag.setImageTag(R.mipmap.tag3).setNameTag("Bài học").setQuotation("( Học một biết mười )");
        tags.add(tag);

        tag = new Tag();
        tag.setImageTag(R.mipmap.tag4).setNameTag("File Download").setQuotation("( Luyện mãi thành tài , miệt mài tất giỏi )");
        tags.add(tag);

        tag = new Tag();
        tag.setImageTag(R.mipmap.tag5).setNameTag("Từ vựng").setQuotation("( Có cày có thóc , có học có chữ )");
        tags.add(tag);
    }

    public ArrayList<Alphabet> getAlphabets() {
        Json json = new Json(this);
        alphabets = json.getAlphabets();
        return alphabets;
    }

    public ArrayList<Lesson> getLesson() {
        Data data = new Data(this);
        lessons = data.getLesson();
        return lessons;
    }

    public ArrayList<CategoryVocabulary> getCategorys() {
        Data data = new Data(this);
        categorys = data.getCategoryVocabulary();
        return categorys;
    }

    public ArrayList<VocabularyMinanoLesson> createNameMinanoLesson() {
        minanoLessons = new ArrayList<>();
        for (int i = 1; i < 51; i++) {
            minanoLesson = new VocabularyMinanoLesson("Từ vựng Minano Nihongo Bài " + i);
            minanoLessons.add(minanoLesson);
        }
        return minanoLessons;
    }

    public void createFolder() {
        File myDirectory = new File(Environment.getExternalStorageDirectory(), DownloadTask.FOLDER);
        if (!myDirectory.exists()) {
            myDirectory.mkdirs();
        }
    }

    private void bindView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    boolean doublePressBack = false;

    @Override
    public void onBackPressed() {
        //close navigation

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            return;
        }

        // Hide keyboard
        View view = MainActivity.this.getCurrentFocus();
        if (view != null && view instanceof EditText) {
            InputMethodManager im = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }


        // pop back stack
        if (getSupportFragmentManager().getBackStackEntryCount() == 0 && !isMainMenu) {
            selectNaviItem(tagFragment);
            isMainMenu = true;
            return;
        } else if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home: {
                tagFragment = new TagFragment(tags, fragments);
                selectNaviItem(tagFragment);
                break;
            }
            case R.id.nav_alphabet: {
                viewPagerAlphabetFragment = new ViewPagerAlphabetFragment(false, alphabets);

                selectNaviItem(viewPagerAlphabetFragment);
                break;
            }
            case R.id.nav_lesson: {
                listLessonFragment = new ListLessonFragment(lessons);

                selectNaviItem(listLessonFragment);
                break;
            }
            case R.id.nav_vocabulary: {
                listVocabularyMinanoFragment = new ListVocabularyMinanoFragment(minanoLessons);
                selectNaviItem(listVocabularyMinanoFragment);
                break;
            }

            case R.id.nav_more: {
                listCategoryFragment = new ListCategoryFragment(categorys);
                selectNaviItem(listCategoryFragment);
                break;
            }
        }
        return true;
    }

    public void selectNaviItem(Fragment fragment) {
        isMainMenu = false;
        drawerLayout.closeDrawers();
        FragmentControl.removeFragmentInBackStack(this);
        FragmentControl.goToFragmentNoAddBackStack(R.id.framelayoutToolBar, fragment, this);
    }
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

}
