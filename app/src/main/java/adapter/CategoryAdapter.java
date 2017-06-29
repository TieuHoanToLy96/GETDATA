package adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tieuhoan.getdata.R;

import java.util.ArrayList;

import model.CategoryVocabulary;

/**
 * Created by TieuHoan on 18/05/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<CategoryVocabulary> categorys;
    private CategoryVocabulary category;

    public CategoryAdapter(Context context, ArrayList<CategoryVocabulary> categorys) {
        this.context = context;
        this.categorys = categorys;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_category, null, false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        category = categorys.get(position);
        CategoryViewHolder viewHolder = (CategoryViewHolder) holder;
        viewHolder.tvCategoryName.setText(category.getVietnamese());


        String name = category.getThumbnail();
        int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        Drawable drawable = context.getResources().getDrawable(id);
        viewHolder.imgCategoryThum.setImageDrawable(drawable);

    }

    @Override
    public int getItemCount() {
        return categorys.size();
    }

    public interface OnClickCategoryVocabulary {

        void OnClickCategory(View view, int position);
    }

    OnClickCategoryVocabulary onClickCategoryVocabulary;

    public void setOnClickCategoryVocabulary(OnClickCategoryVocabulary onClickCategoryVocabulary) {
        this.onClickCategoryVocabulary = onClickCategoryVocabulary;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgCategoryThum;
        TextView tvCategoryName;


        public CategoryViewHolder(View itemView) {
            super(itemView);

            imgCategoryThum = (ImageView) itemView.findViewById(R.id.imgCategoryThum);
            tvCategoryName = (TextView) itemView.findViewById(R.id.tvCategoryName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickCategoryVocabulary.OnClickCategory(v, getPosition());
        }
    }
}
