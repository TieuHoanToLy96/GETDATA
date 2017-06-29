package view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tieuhoan.getdata.R;

import java.util.ArrayList;

import adapter.ViewPagerWriteAlphabetAdapter;
import customview.MyDrawView;
import model.Alphabet;
import ulti.FragmentControl;

/**
 * Created by TieuHoan on 16/05/2017.
 */

public class ViewPagerWriteAlphabetFragment extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {


    private ViewPager viewPager;
    private ArrayList<Alphabet> alphabets;
    private int position;
    private boolean isHiragana;
    private ViewPagerWriteAlphabetAdapter adapter;
    private ArrayList<Fragment> fragments;
    private Context context;
    private ImageView imgRefreshDrawView, imgLeftArrow, imgRightArrow, imgCheck;
    private MyDrawView myDrawView;
    private ArrayList<Alphabet> alphabetsNotSpace;

    public ViewPagerWriteAlphabetFragment(int position, ArrayList<Alphabet> alphabets, boolean isHiragana) {
        this.alphabets = alphabets;
        this.position = position;
        this.isHiragana = isHiragana;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Viết chữ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;


        View view = inflater.inflate(R.layout.viewpager_write_alphabet_fragment, null, false);
        bindView(view);

        //set width height mydraw
        myDrawView = (MyDrawView) view.findViewById(R.id.myDrawView);
        myDrawView.getLayoutParams().width = (int) (height / 2.5);
        myDrawView.getLayoutParams().height = (int) (height / 2.5);


        return view;
    }

    private void bindView(View view) {

        setUpViewPager(view);

        imgRefreshDrawView = (ImageView) view.findViewById(R.id.imgRefreshDrawView);
        imgLeftArrow = (ImageView) view.findViewById(R.id.imgLeftArrow);
        imgRightArrow = (ImageView) view.findViewById(R.id.imgRightArrow);
        imgCheck = (ImageView) view.findViewById(R.id.imgCheck);


        imgRefreshDrawView.setOnClickListener(this);
        imgLeftArrow.setOnClickListener(this);
        imgRightArrow.setOnClickListener(this);
        imgCheck.setOnClickListener(this);
    }

    public void setUpViewPager(View view) {
        alphabetsNotSpace = new ArrayList<>();
        fragments = new ArrayList<>();
        WriteAlphabetFragment alphabetFragment;
        for (int i = 0; i < alphabets.size(); i++) {
            if (!alphabets.get(i).getRomaji().equals("")) {

                //add fragment to list fragment
                alphabetFragment = new WriteAlphabetFragment(i, alphabets, isHiragana);
                fragments.add(alphabetFragment);

                //get all alphabets not space
                alphabetsNotSpace.add(alphabets.get(i));
            }
        }

        adapter = new ViewPagerWriteAlphabetAdapter(getChildFragmentManager(), fragments);

        viewPager = (ViewPager) view.findViewById(R.id.vpWriteAlphabet);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setOnPageChangeListener(this);
        int x = position;
        for (int i = 0; i < x; i++) {
            if (alphabets.get(i).getRomaji().equals("")) {
                position--;
            }
        }
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgRefreshDrawView: {
                myDrawView.clear();
                break;
            }
            case R.id.imgLeftArrow: {
                if (viewPager.getCurrentItem() > viewPager.getLeft()) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
                }
                break;
            }
            case R.id.imgRightArrow: {
                if (viewPager.getCurrentItem() < viewPager.getRight()) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                }
                break;
            }

            case R.id.imgCheck: {
                CheckAlphabetFragment check = new CheckAlphabetFragment(position, alphabetsNotSpace, isHiragana);
                FragmentControl.goToFragmentAddBackStack(R.id.framelayoutToolBar, check, context, getClass().getName());
                break;
            }

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        this.position = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Viết chữ");
    }
}
