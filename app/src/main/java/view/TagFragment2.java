package view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tieuhoan.getdata.R;

import java.util.ArrayList;

import adapter.TagAdapter;
import model.Tag;
import ulti.FragmentControl;

/**
 * Created by TieuHoan on 04/07/2017.
 */

public class TagFragment2 extends Fragment implements TagAdapter.OnClickItemTag {
    private RecyclerView recycleViewTag;
    private TagAdapter tagAdapter;
    private Context context;
    private ArrayList<Tag> tags;
    private Tag tag;
    private ArrayList<Fragment> fragments;

    private TextView tvJustDoIt;


    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        iniTag();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Tiếng nhật cơ bản");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    private void iniTag() {
        fragments = new ArrayList<>();
        fragments.add(new ViewPagerAlphabetFragment(false));
        fragments.add(new ListVocabularyMinanoFragment());
        fragments.add(new ListLessonFragment());
        fragments.add(new ViewPagerFileFragment());
        fragments.add(new ListCategoryFragment());
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tag_fragment2, null, false);
        bindView(view);
        return view;
    }

    private void bindView(View view) {
        recycleViewTag = (RecyclerView) view.findViewById(R.id.recycleViewTag);
        tvJustDoIt = (TextView) view.findViewById(R.id.tvJustDoIt);
        tvJustDoIt.setSelected(true);
        tagAdapter = new TagAdapter(tags, context);
        recycleViewTag.setLayoutManager(new GridLayoutManager(context, 2));
        recycleViewTag.setAdapter(tagAdapter);
        tagAdapter.setOnClickItemTag(this);

    }

    @Override
    public void OnClickTag(View view, int position, ImageView imgBgTag) {

        FragmentControl.goToFragmentAddBackStack(R.id.framelayout, new ToolBarFragment(fragments.get(position), true), context, getClass().getName());
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Tiếng nhật cơ bản");
    }
}
