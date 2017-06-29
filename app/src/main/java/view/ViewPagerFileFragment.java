package view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tieuhoan.getdata.R;

import java.util.ArrayList;

import adapter.ViewPagerAlphabetAdapter;

/**
 * Created by TieuHoan on 18/05/2017.
 */

public class ViewPagerFileFragment extends Fragment {
    private ViewPager viewPager;
    private Context context;
    private TabLayout tabLayout;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private CoordinatorLayout view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Danh sách file");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpage_alphbet_fragment, null, false);

        bindView(view);
        return view;


    }

    private void bindView(View view) {

        // file guide
        this.view = (CoordinatorLayout) view.findViewById(R.id.fileGuide);

        Snackbar snackbar = Snackbar
                .make(this.view, "Giữ để tùy chọn", Snackbar.LENGTH_LONG);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) snackbar.getView().getLayoutParams();
        layoutParams.gravity = Gravity.BOTTOM;
        snackbar.getView().setLayoutParams(layoutParams);
        snackbar.show();

        viewPager = (ViewPager) view.findViewById(R.id.viewPagerAlphabet);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        fragments = new ArrayList<>();
        fragments.add(new FileFragment(false));
        fragments.add(new FileFragment(true));
        titles = new ArrayList<>();
        titles.add("Audio");
        titles.add("PDF");


        ViewPagerAlphabetAdapter vpgAA = new ViewPagerAlphabetAdapter(getChildFragmentManager(), fragments, titles);
        viewPager.setAdapter(vpgAA);
        tabLayout.setupWithViewPager(viewPager);
    }
}
