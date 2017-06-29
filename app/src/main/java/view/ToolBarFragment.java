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
import android.widget.ToggleButton;

import com.example.tieuhoan.getdata.R;

import ulti.FragmentControl;

/**
 * Created by TieuHoan on 14/05/2017.
 */

public class ToolBarFragment extends Fragment {
    private Context context;
    private Fragment fragment;
    private Toolbar toolbar;
    private ToggleButton toggleWidget;
    private boolean isisVisibleToggle;

    public ToolBarFragment(Fragment fragment) {
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
        View view = inflater.inflate(R.layout.toolbar_fragment, null, false);
        bindView(view);
        FragmentControl.goToFragmentNoAddBackStack(R.id.framelayoutToolBar, fragment, context);
        return view;
    }


    public void bindView(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.my_toolbar);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


}
