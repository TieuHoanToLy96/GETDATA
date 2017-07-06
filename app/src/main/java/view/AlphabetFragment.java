package view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tieuhoan.getdata.R;

import java.util.ArrayList;

import adapter.AlphabetAdapter;
import model.Alphabet;
import ulti.FragmentControl;

/**
 * Created by TieuHoan on 14/05/2017.
 */

public class AlphabetFragment extends Fragment implements AlphabetAdapter.OnClickItemRecycleView {


    private Context context;
    private RecyclerView recyclerView;
    private AlphabetAdapter alphabetAdapter;
    private boolean isHiragana;
    private int numberColum = 5;
    private ArrayList<Alphabet> alphabets;


    public AlphabetFragment(boolean isHiragana , ArrayList<Alphabet> alphabets) {
        this.isHiragana = isHiragana;
        this.alphabets = alphabets;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alphabet_fragment, null, false);
        bindView(view);
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
    }


}
