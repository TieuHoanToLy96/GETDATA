package view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.tieuhoan.getdata.R;

import java.util.ArrayList;

import adapter.ListLessonAdapter;
import model.Lesson;
import ulti.FragmentControl;

/**
 * Created by TieuHoan on 16/05/2017.
 */

public class ListLessonFragment extends Fragment implements ListLessonAdapter.OnClickItemRecycleView {
    private Context context;
    private ArrayList<Lesson> lessons;
    private RecyclerView recyclerViewLesson;
    private ListLessonAdapter listLessonAdapter;
    private int numberColumLesson = 2;


    public ListLessonFragment(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        setUpToolbar();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_lesson_fragment, null, false);
        bindVew(view);
        return view;
    }

    private void bindVew(View view) {
        listLessonAdapter = new ListLessonAdapter(lessons, context);
        listLessonAdapter.setOnClickItemRecycleView(this);

        recyclerViewLesson = (RecyclerView) view.findViewById(R.id.recycleViewListLesson);
        recyclerViewLesson.setLayoutManager(new GridLayoutManager(context, numberColumLesson));
        recyclerViewLesson.setAdapter(listLessonAdapter);

    }

    @Override
    public void OnClick(View view, int position) {
        Lesson lesson = lessons.get(position);
        LessonFragment lessonFragment = new LessonFragment(position + 1, lesson);
        FragmentControl.goToFragmentAddBackStack(R.id.framelayoutToolBar, lessonFragment, context, getClass().getName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
            Log.e("tieuhoan", "click back");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void setUpToolbar() {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Bài học");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.mipmap.back_arrow_white);


    }


    @Override
    public void onResume() {
        super.onResume();
        setUpToolbar();
    }
}
