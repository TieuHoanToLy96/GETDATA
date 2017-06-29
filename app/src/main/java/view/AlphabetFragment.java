package view;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.example.tieuhoan.getdata.R;

import java.util.ArrayList;

import adapter.AlphabetAdapter;
import model.Alphabet;
import ulti.FragmentControl;
import ulti.Json;

/**
 * Created by TieuHoan on 14/05/2017.
 */

public class AlphabetFragment extends Fragment implements AlphabetAdapter.OnClickItemRecycleView {


    public ArrayList<Alphabet> alphabets;
    private Context context;
    private RecyclerView recyclerView;
    private AlphabetAdapter alphabetAdapter;
    private boolean isHiragana;
    private int numberColum = 5;
    private Boolean isVisibleToggle;
    private ToggleButton toggleWidget;

    public AlphabetFragment(boolean isHiragana, Boolean isVisibleToggle) {
        this.isHiragana = isHiragana;
        this.isVisibleToggle = isVisibleToggle;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        Json json = new Json(context);
        alphabets = json.getAlphabets();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alphabet_fragment, null, false);
        bindView(view);
        setHasOptionsMenu(isVisibleToggle);
        return view;
    }

    public void bindView(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleViewAlphabet);
        recyclerView.setLayoutManager(new GridLayoutManager(context, numberColum));
        alphabetAdapter = new AlphabetAdapter(alphabets, context, isHiragana);
        recyclerView.setAdapter(alphabetAdapter);
        alphabetAdapter.setOnClickItemRecycleView(this);

    }

    @Override
    public void OnClick(View view, int position) {

        if (!alphabets.get(position).getRomaji().equals(""))
            FragmentControl.goToFragmentAddBackStack(R.id.framelayoutToolBar, new ViewPagerWriteAlphabetFragment(position, alphabets, isHiragana), context, getClass().getName());
//            FragmentControl.goToFragmentAddBackStack(R.id.framelayout, new ToolBarFragment(new ViewPagerWriteAlphabetFragment(position, alphabets, isHiragana)), context, getClass().getName());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar, menu);
        toggleWidget = (ToggleButton) menu.findItem(R.id.toggle).getActionView().findViewById(R.id.switch_show);
//        toggleWidget.setChecked(isVisibleToggle);
        toggleWidget.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
                Log.e("tieuhoan", "click toggle");
                Intent pickIntent = new Intent(AppWidgetManager.ACTION_APPWIDGET_PICK);
                startActivity(pickIntent);
//                }
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }


}
