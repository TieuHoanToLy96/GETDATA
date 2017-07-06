package view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tieuhoan.getdata.R;

import java.util.ArrayList;

import adapter.ViewPagerAlphabetAdapter;
import model.Alphabet;

/**
 * Created by TieuHoan on 14/05/2017.
 */

public class ViewPagerAlphabetFragment extends Fragment {
    private ViewPager viewPager;
    private Context context;
    private TabLayout tabLayout;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private Boolean isVisibleToggle;
    private ArrayList<Alphabet> alphabets;

    public ViewPagerAlphabetFragment(Boolean isVisibleToggle, ArrayList<Alphabet> alphabets) {
        this.isVisibleToggle = isVisibleToggle;
        this.alphabets = alphabets;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Bảng chữ cái");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpage_alphbet_fragment, null, false);

        bindView(view);
        return view;


    }

    public void bindView(View view) {

        viewPager = (ViewPager) view.findViewById(R.id.viewPagerAlphabet);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        fragments = new ArrayList<>();
        fragments.add(new AlphabetFragment(true, alphabets));
        fragments.add(new AlphabetFragment(false, alphabets));
        titles = new ArrayList<>();
        titles.add("Hiragana");
        titles.add("Katakana");


        ViewPagerAlphabetAdapter vpgAA = new ViewPagerAlphabetAdapter(getChildFragmentManager(), fragments, titles);
        viewPager.setAdapter(vpgAA);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Bảng chữ cái");
    }
}
