package view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tieuhoan.getdata.R;

import java.util.ArrayList;

import adapter.ListVocabularyMinanoAdapter;
import model.VocabularyMinanoLesson;
import ulti.FragmentControl;

/**
 * Created by TieuHoan on 21/06/2017.
 */

public class ListVocabularyMinanoFragment extends Fragment implements ListVocabularyMinanoAdapter.OnClickVocMinanoLesson {
    private Context context;

    private ListVocabularyMinanoAdapter minanoAdapter;
    private RecyclerView recyclerViewVocMinano;
    private ArrayList<VocabularyMinanoLesson> minanoLessons;
    private ArrayList<Fragment> fragments;
    private VocabularyMinanoFragment minanoFragment;

    public ListVocabularyMinanoFragment(ArrayList<VocabularyMinanoLesson> minanoLessons) {
        this.minanoLessons = minanoLessons;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        initViewPager();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Từ vựng Minano Nihongo");

    }


    public void initViewPager() {
        fragments = new ArrayList<>();
        for (int i = 1; i < 51; i++) {
             minanoFragment = new VocabularyMinanoFragment(i);
            fragments.add(minanoFragment);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_vocanulary_minano_fragment, null, false);

        bindView(view);
        return view;

    }

    public void bindView(View view) {
        recyclerViewVocMinano = (RecyclerView) view.findViewById(R.id.recycleViewListVocabularyMinano);
        minanoAdapter = new ListVocabularyMinanoAdapter(context, minanoLessons);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerViewVocMinano.setAdapter(minanoAdapter);
        recyclerViewVocMinano.setLayoutManager(linearLayoutManager);

        minanoAdapter.setOnClickVocMinanoLesson(this);
    }

    @Override
    public void OnClickVocMinano(View v, int position) {
        ViewPagerVocMinanoFragment vocMinanoFragment = new ViewPagerVocMinanoFragment(position, fragments);
        FragmentControl.goToFragmentAddBackStack(R.id.framelayoutToolBar, vocMinanoFragment, context, getClass().getName());
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Từ vựng Minano Nihongo");
    }
}
