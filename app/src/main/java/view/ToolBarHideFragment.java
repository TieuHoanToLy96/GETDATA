package view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tieuhoan.getdata.R;

import ulti.FragmentControl;

/**
 * Created by TieuHoan on 28/06/2017.
 */

public class ToolBarHideFragment extends Fragment {
    private Context context;
    private Fragment fragment;
    private Toolbar toolbar;

    public ToolBarHideFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.toolbar_hide_fragment, null, false);
        bindView(view);
        FragmentControl.goToFragmentNoAddBackStack(R.id.framelayoutToolBarHide, fragment, context);
        return view;
    }

    private void bindView(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.my_toolbar_hide);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
