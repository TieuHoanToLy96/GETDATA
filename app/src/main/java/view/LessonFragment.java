package view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tieuhoan.getdata.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import adapter.ContentLessonAdapter;
import model.ContentLesson;
import model.DetailLesson;
import model.Lesson;
import ulti.DownloadTask;
import ulti.Json;

/**
 * Created by TieuHoan on 17/05/2017.
 */

public class LessonFragment extends Fragment implements ContentLessonAdapter.OnClickHeaderRecycleView, ContentLessonAdapter.OnClickItemRecycleViewContentLesson {
    private Context context;
    private ArrayList<ContentLesson> contentLessons;
    private int sttLesson;
    private DetailLesson detailLesson;
    private RecyclerView recyclerView;
    private Lesson lesson;
    private ContentLessonAdapter contentLessonAdapter;
    public static MediaPlayer player;
    private CoordinatorLayout coordinatorLayout;

    private boolean isShort ;

    public LessonFragment(int sttLesson, Lesson lesson) {
        this.sttLesson = sttLesson;
        this.lesson = lesson;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        if (sttLesson <10){
            isShort= true ;
        }else {
            isShort = false ;
        }
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(lesson.getName());
        //get content
        Json json = new Json(context);
        contentLessons = json.getContentLesson(String.valueOf(sttLesson));
        detailLesson = json.getDetailLesson(String.valueOf(sttLesson));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lesson_fragment, null, false);
        bindView(view);
        return view;
    }

    public void bindView(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleViewLesson);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        contentLessonAdapter = new ContentLessonAdapter(contentLessons, context, detailLesson, lesson);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(contentLessonAdapter);
        contentLessonAdapter.setOnClickHeaderRecycleView(this);
        contentLessonAdapter.setOnClick(this);

        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinatorLayout);

    }

    @Override
    public void OnLickDownAudio(View view, int position) {
        eventDownLoad("mp3", lesson.getLinkAudio(), "Downloading Audio");
    }

    @Override
    public void OnClickDownPDF(View view, int position) {
        eventDownLoad("pdf", lesson.getLinkPdf(), "Downloading PDF");
    }

    public void eventDownLoad(String typeFile, String linkFile, String message) {


        File file = new File(DownloadTask.PATH_FOLDER + "/" + lesson.getName() + "." + typeFile);
        if (file.exists()) {
            Snackbar snackbar = Snackbar.make(coordinatorLayout, "File đã tồn tại", Snackbar.LENGTH_SHORT);
            snackbar.show();
        } else {

            ProgressDialog mProgressDialog;
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setMessage(message);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setCancelable(true);


            final DownloadTask downloadTask = new DownloadTask(context, mProgressDialog, "." + typeFile, lesson.getName(), coordinatorLayout ,isShort);
            downloadTask.execute(linkFile);

            mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    downloadTask.cancel(true);
                }
            });

        }
    }

    public void initMedia(String nameAudio) {
        AssetFileDescriptor afd = null;
        try {
            afd = getActivity().getAssets().openFd("audio/lesson/" + nameAudio);

            player = new MediaPlayer();
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playAudio(final ImageView img, String nameAudio) {
        if (isPlaying) {
            isPlaying = false;
            img.setImageResource(R.mipmap.play_all_audio);
            player.release();
        } else {
            initMedia(nameAudio);
            isPlaying = true;
            img.setImageResource(R.mipmap.play_all_audio_down);
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    img.setImageResource(R.mipmap.play_all_audio);
                    player.release();
                    isPlaying = false;

                }
            });

        }
    }


    public boolean isPlaying = false;

    @Override
    public void OnClickPlayAllVideo(View view, int position, final ImageView imgPlayAllAudio) {

        playAudio(imgPlayAllAudio, detailLesson.getMain_audio());


    }

    @Override
    public void onStop() {
        super.onStop();
        if (isPlaying) {
            player.release();
        }
    }

    @Override
    public void OnLickPlayAudio(View view, int position, ImageView imgPlayAudio) {
        ContentLesson contentLesson = contentLessons.get(position - 1);
        if (!isPlaying) {
            playAudio(imgPlayAudio, contentLesson.getAudio());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(lesson.getName());
    }


}
