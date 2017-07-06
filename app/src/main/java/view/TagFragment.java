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

public class TagFragment extends Fragment implements TagAdapter.OnClickItemTag {
    private RecyclerView recycleViewTag;
    private TagAdapter tagAdapter;
    private Context context;

    private TextView tvJustDoIt;

    private ArrayList<Tag> tags;
    private ArrayList<Fragment> fragments;

    public TagFragment(ArrayList<Tag> tags, ArrayList<Fragment> fragments) {
        this.tags = tags;
        this.fragments = fragments;
    }

    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Tiếng nhật cơ bản");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

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
        Fragment fragment = fragments.get(position);
        FragmentControl.goToFragmentAddBackStack(R.id.framelayoutToolBar, fragment, context, fragment.getClass().getName());
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Tiếng nhật cơ bản");
    }
}
