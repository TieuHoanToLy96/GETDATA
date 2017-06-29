package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tieuhoan.getdata.R;

import java.util.ArrayList;

import model.FileDownload;

/**
 * Created by TieuHoan on 18/05/2017.
 */

public class FileDownloadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<FileDownload> files;
    private Context context;
    private boolean isPDF;
    ArrayList<CheckBox> checkBoxes;


    public FileDownloadAdapter(ArrayList<FileDownload> files, Context context, boolean isPDF) {
        this.files = files;
        this.context = context;
        this.isPDF = isPDF;
        checkBoxes = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_file_download, parent, false);
        return new FileDownLoadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FileDownload fileDownload = files.get(position);

        FileDownLoadViewHolder fileDownLoad = (FileDownLoadViewHolder) holder;
        fileDownLoad.tvFileDownloadName.setText(fileDownload.getFileName());
        fileDownLoad.checkFileDown.setChecked(fileDownload.getCheckedFile());

        if (fileDownload.getVisibleCheckBox()) {
            fileDownLoad.checkFileDown.setVisibility(View.VISIBLE);
        } else {
            fileDownLoad.checkFileDown.setVisibility(View.GONE);
        }
//        checkBoxes.add(fileDownLoad.checkFileDown);

        if (isPDF) {
            fileDownLoad.imgFileType.setImageResource(R.mipmap.file_pdf);
        } else {
            fileDownLoad.imgFileType.setImageResource(R.mipmap.file_audio);
        }

    }

    @Override
    public int getItemCount() {
        return files.size();
    }


    public interface OnClickFileDownload {
        void OnClicKFileDown(View view, int position, CheckBox checkBox);
    }

    public OnClickFileDownload onClickFileDownload;

    public void setOnClickFileDownload(OnClickFileDownload onClickFileDownload) {
        this.onClickFileDownload = onClickFileDownload;
    }


    public interface OnLongClickFileDownload {
        void OnLongClickFileDown(View view, int position, CheckBox checkFileDown, ArrayList<CheckBox> checkBoxes);
    }

    public OnLongClickFileDownload onLongClickFileDownload;

    public void setOnLongClickFileDownload(OnLongClickFileDownload onLongClickFileDownload) {
        this.onLongClickFileDownload = onLongClickFileDownload;
    }

    public ArrayList<CheckBox> getCheckBoxes() {
        return checkBoxes;
    }


    public interface OnClickCheckFileDown {
        void OnClickCheckFile(View view, int position, CheckBox checkFileDown);
    }

    public OnClickCheckFileDown onClickCheckFileDown;

    public void setOnClickCheckFileDown(OnClickCheckFileDown onClickCheckFileDown) {
        this.onClickCheckFileDown = onClickCheckFileDown;
    }

    public class FileDownLoadViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public TextView tvFileDownloadName;
        public ImageView imgFileType;
        public CheckBox checkFileDown;

        public CheckBox getCheckFileDown() {
            return checkFileDown;
        }

        public FileDownLoadViewHolder(View itemView) {
            super(itemView);
            tvFileDownloadName = (TextView) itemView.findViewById(R.id.tvFileDownloadName);
            imgFileType = (ImageView) itemView.findViewById(R.id.imgFileType);
            checkFileDown = (CheckBox) itemView.findViewById(R.id.checkFileDownload);
            tvFileDownloadName.setOnClickListener(this);
            checkFileDown.setOnClickListener(this);
            tvFileDownloadName.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tvFileDownloadName: {
                    onClickFileDownload.OnClicKFileDown(v, getLayoutPosition(), getCheckFileDown());
                    break;
                }
                case R.id.checkFileDownload: {
                    onClickCheckFileDown.OnClickCheckFile(v, getLayoutPosition(), getCheckFileDown());
                    break;
                }

            }
        }

        @Override
        public boolean onLongClick(View v) {
            onLongClickFileDownload.OnLongClickFileDown(v, getPosition(), getCheckFileDown(), getCheckBoxes());
            return true;
        }
    }
}
