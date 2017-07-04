package view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tieuhoan.getdata.R;

import ulti.FragmentControl;

/**
 * Created by TieuHoan on 24/05/2017.
 */

public class TagFragment extends Fragment implements View.OnClickListener {

    private ImageView imgAlphabet, imgLesson, imgVocabulary, imgFavorite, imgNote;
    private Context context;
    private Boolean isVisibleToggle;
    private boolean isHideArrowLeft = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tag_fragment, null, false);
        bindView(view);
        return view;
    }

    public void bindView(View view) {
        imgAlphabet = (ImageView) view.findViewById(R.id.imgAlphabet);
        imgLesson = (ImageView) view.findViewById(R.id.imgLesson);
        imgFavorite = (ImageView) view.findViewById(R.id.imgFavorite);
        imgVocabulary = (ImageView) view.findViewById(R.id.imgVocabulary);
        imgNote = (ImageView) view.findViewById(R.id.imgNote);

        imgAlphabet.setOnClickListener(this);
        imgLesson.setOnClickListener(this);
        imgFavorite.setOnClickListener(this);
        imgVocabulary.setOnClickListener(this);
        imgNote.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgAlphabet: {
                eventImgAlphabet();
                break;
            }
            case R.id.imgLesson: {
                eventImgLesson();
                break;
            }
            case R.id.imgFavorite: {
                eventImgFavorite();
                break;
            }
            case R.id.imgVocabulary: {
                eventVocabularyMinano();
                break;
            }
            case R.id.imgNote: {
                eventVocabularyMore();
                break;
            }
        }
    }

    public void eventVocabularyMinano() {
        ListVocabularyMinanoFragment minanoFragment = new ListVocabularyMinanoFragment();
        FragmentControl.goToFragmentAddBackStack(R.id.framelayout, new ToolBarFragment(minanoFragment, isHideArrowLeft), context, getClass().getName());
    }

    public void eventVocabularyMore() {
        ListCategoryFragment listCategoryFragment = new ListCategoryFragment();
        FragmentControl.goToFragmentAddBackStack(R.id.framelayout, new ToolBarFragment(listCategoryFragment, isHideArrowLeft), context, getClass().getName());
    }

    public void eventImgFavorite() {
        ViewPagerFileFragment vpgFd = new ViewPagerFileFragment();
        FragmentControl.goToFragmentAddBackStack(R.id.framelayout, new ToolBarFragment(vpgFd, isHideArrowLeft), context, getClass().getName());
    }

    public void eventImgLesson() {
        ListLessonFragment listLessonFragment = new ListLessonFragment();
        FragmentControl.goToFragmentAddBackStack(R.id.framelayout, new ToolBarFragment(listLessonFragment, isHideArrowLeft), context, getClass().getName());
    }

    public void eventImgAlphabet() {
        ViewPagerAlphabetFragment vpgAF = new ViewPagerAlphabetFragment(getStatusVisibleToogle());
        FragmentControl.goToFragmentAddBackStack(R.id.framelayout, new ToolBarFragment(vpgAF, isHideArrowLeft), context, getClass().getName());
    }


    public Boolean getStatusVisibleToogle() {
        //get status visible toggle in sharereference
        this.isVisibleToggle = true;
        return isVisibleToggle;
    }
}
