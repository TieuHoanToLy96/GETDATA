package view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tieuhoan.getdata.R;

import java.util.ArrayList;

import adapter.CategoryAdapter;
import model.CategoryVocabulary;
import ulti.Data;
import ulti.FragmentControl;

/**
 * Created by TieuHoan on 18/05/2017.
 */

public class ListCategoryFragment extends Fragment implements CategoryAdapter.OnClickCategoryVocabulary {

    private Context context;
    private RecyclerView recyclerView;
    private ArrayList<CategoryVocabulary> categorys;
    private CategoryAdapter categoryAdapter;
    private int numberColum = 3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Từ vựng");
//
        Data data = new Data(context);
        categorys = data.getCategoryVocabulary();


//
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_category_fragment, null, false);

        bindView(view);

        return view;
    }

    private void bindView(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleViewListCategory);
        categoryAdapter = new CategoryAdapter(context, categorys);
        GridLayoutManager grid = new GridLayoutManager(context, numberColum);
        recyclerView.setLayoutManager(grid);
        recyclerView.setAdapter(categoryAdapter);
        categoryAdapter.setOnClickCategoryVocabulary(this);

    }

    @Override
    public void OnClickCategory(View view, int position) {
        CategoryVocabulary categoryVocabulary = categorys.get(position);
        ListVocabularyMoreFragment listVocabulary = new ListVocabularyMoreFragment(position + 1, categoryVocabulary.getVietnamese());
        FragmentControl.goToFragmentAddBackStack(R.id.framelayoutToolBar, listVocabulary, context, getClass().getName());
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Từ vựng");
    }
}
