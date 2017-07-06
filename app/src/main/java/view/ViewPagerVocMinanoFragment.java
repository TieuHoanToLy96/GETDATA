package view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tieuhoan.getdata.R;

import java.util.ArrayList;

import adapter.ViewPagerWriteAlphabetAdapter;

/**
 * Created by TieuHoan on 21/06/2017.
 */

public class ViewPagerVocMinanoFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private ViewPagerWriteAlphabetAdapter adapter;
    private ArrayList<Fragment> fragments;
    private int position;

    public ViewPagerVocMinanoFragment(int position, ArrayList<Fragment> fragments) {
        this.position = position;
        this.fragments = fragments;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Từ vựng bài " + (position + 1));


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.viewpager_voc_minano_fragment, null, false);
        viewPager = (ViewPager) view.findViewById(R.id.vpVocMinano);
        adapter = new ViewPagerWriteAlphabetAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setOnPageChangeListener(this);
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Từ vựng bài " + (position + 1));
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
