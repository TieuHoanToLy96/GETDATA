package view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tieuhoan.getdata.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import adapter.FileDownloadAdapter;
import model.FileDownload;
import ulti.DownloadTask;
import ulti.FileDownloaded;

import static android.view.View.GONE;

/**
 * Created by TieuHoan on 18/05/2017.
 */

public class FileFragment extends Fragment implements FileDownloadAdapter.OnClickFileDownload, FileDownloadAdapter.OnLongClickFileDownload, FileDownloadAdapter.OnClickCheckFileDown {
    private RecyclerView recyclerView;
    private boolean isPDF;
    private FileDownloaded fileDownloaded;
    private Context context;
    private FileDownloadAdapter downloadAdapter;
    private ArrayList<FileDownload> files;
    private LinearLayout toolFileDown;
    private ImageView imgDelete, imgSendFile, imgCancel, imgRename;

    private TextView tvCancel, tvOK;
    private EditText edtNewName;

    public FileFragment(boolean isPDF) {
        this.isPDF = isPDF;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        fileDownloaded = new FileDownloaded();
        if (isPDF) {
            files = fileDownloaded.getFileDownLoad("pdf");
        } else {
            files = fileDownloaded.getFileDownLoad("mp3");
        }


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.file_fragment, null, false);
        bindView(view);
        return view;
    }


    public void bindView(View view) {


        //tool file
        toolFileDown = (LinearLayout) view.findViewById(R.id.toolFileDown);
        imgDelete = (ImageView) view.findViewById(R.id.imgDelete);
        imgSendFile = (ImageView) view.findViewById(R.id.imgSendFile);
        imgCancel = (ImageView) view.findViewById(R.id.imgCancel);
        imgRename = (ImageView) view.findViewById(R.id.imgRename);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleViewFile);
        downloadAdapter = new FileDownloadAdapter(files, context, isPDF);
        LinearLayoutManager linear = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(downloadAdapter);
        recyclerView.setLayoutManager(linear);

        downloadAdapter.setOnClickFileDownload(this);
        if (toolFileDown.getVisibility() == GONE) {
            downloadAdapter.setOnLongClickFileDownload(this);
        }
        downloadAdapter.setOnClickCheckFileDown(this);

    }

    Intent intent;

    @Override
    public void OnClicKFileDown(View view, int position, CheckBox checkFileDown) {
        if (toolFileDown.getVisibility() == GONE) {

            FileDownload fileDownload = files.get(position);
            intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_VIEW);
            File file = new File(fileDownload.getFilePath());
            if (isPDF) {
                intent.setDataAndType(Uri.fromFile(file), "application/pdf");
            } else {
                intent.setDataAndType(Uri.fromFile(file), "audio/mp3");
            }
            startActivity(intent);
        } else {

            if (checkFileDown.isChecked()) {
                checkFileDown.setChecked(false);
                files.get(position).setCheckedFile(false);
            } else {
                checkFileDown.setChecked(true);
                files.get(position).setCheckedFile(true);
            }

            hideAndShowRename();

        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void OnLongClickFileDown(View view, final int position, CheckBox checkFileDown, final ArrayList<CheckBox> checkBoxes) {

        toolFileDown.setVisibility(View.VISIBLE);
        showAllCheckFile(checkBoxes);
        checkFileDown.setChecked(true);
        files.get(position).setCheckedFile(true);

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventDeleteFile(checkBoxes);
            }

        });
        imgSendFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventSendFile(checkBoxes);
            }
        });

        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventCancel(checkBoxes);
            }
        });
        imgRename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventRename(position, checkBoxes);
            }
        });
    }

    public void eventRename(int position, ArrayList<CheckBox> checkBoxes) {

        createDialogRename();

        hideAllCheckFile(checkBoxes);

    }

    public void createDialogRename() {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_rename);
        tvCancel = (TextView) dialog.findViewById(R.id.tvCancel);
        tvOK = (TextView) dialog.findViewById(R.id.tvOK);
        edtNewName = (EditText) dialog.findViewById(R.id.edtNewName);


        ///event
        int positionRename = 0;
        for (int i = 0; i < files.size(); i++) {
            if (files.get(i).getCheckedFile()) {
                edtNewName.setText(files.get(i).getFileName());
                positionRename = i;
                break;
            }
        }

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        final int finalPositionRename = positionRename;
        tvOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rename(finalPositionRename, dialog);

            }
        });
        dialog.show();
    }

    private void rename(int positionRename, Dialog dialog) {
        // Hide keyboard
        View view = getActivity().getCurrentFocus();
        if (view != null && view instanceof EditText) {
            InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }

        String newName = String.valueOf(edtNewName.getText());
        String type;
        if (isPDF) {
            type = ".pdf";
        } else {
            type = ".mp3";
        }
        File from = new File(DownloadTask.PATH_FOLDER, files.get(positionRename).getFileName() + type);
        File to = new File(DownloadTask.PATH_FOLDER, newName + type);
        from.renameTo(to);

        files.get(positionRename).setFileName(newName);
        downloadAdapter.notifyDataSetChanged();
        dialog.dismiss();


    }

    public void eventCancel(ArrayList<CheckBox> checkBoxes) {
        hideAllCheckFile(checkBoxes);
    }


    public void eventSendFile(ArrayList<CheckBox> checkBoxes) {


        Intent sendFile = new Intent(Intent.ACTION_SEND_MULTIPLE);
        sendFile.putExtra(Intent.EXTRA_SUBJECT, "Send file");
        if (isPDF) {
            sendFile.setType("application/pdf");
        } else {
            sendFile.setType("audio/mp3");
        }
        ArrayList<Uri> listFileSend = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            if (files.get(i).getCheckedFile()) {
                File file = new File(files.get(i).getFilePath());

                Uri uri = Uri.fromFile(file);
                listFileSend.add(uri);
            }
        }

        sendFile.putParcelableArrayListExtra(Intent.EXTRA_STREAM, listFileSend);
        startActivity(sendFile);

        //remake
        hideAllCheckFile(checkBoxes);

    }

    public void eventDeleteFile(ArrayList<CheckBox> checkBoxes) {


        // remove element in files array
        Iterator<FileDownload> it = files.iterator();
        while (it.hasNext()) {
            FileDownload fileDownload = it.next();
            if (fileDownload.getCheckedFile()) {
                File file = new File(fileDownload.getFilePath());
                if (file.exists()) {
                    file.delete();
                }
                it.remove();
            }
        }

        downloadAdapter.notifyDataSetChanged();
        hideAllCheckFile(checkBoxes);

    }


    private int countCheckFile;

    @Override
    public void OnClickCheckFile(View view, int position, CheckBox checkFileDown) {
        if (checkFileDown.isChecked()) {
            files.get(position).setCheckedFile(true);
        } else {
            files.get(position).setCheckedFile(false);
        }

        hideAndShowRename();
    }

    public void hideAndShowRename() {
        countCheckFile = 0;

        for (int i = 0; i < files.size(); i++) {
            if (files.get(i).getCheckedFile()) {
                countCheckFile++;
            }
        }
        if (countCheckFile != 1) {
            imgRename.setClickable(false);
            imgRename.setAlpha((float) 0.15);
        } else {
            imgRename.setClickable(true);
            imgRename.setAlpha((float) 1.0);
        }


    }

    /////////////////////////
    public void remakeFiles(ArrayList<FileDownload> files) {
        for (int i = 0; i < files.size(); i++) {
            files.get(i).setCheckedFile(false);
        }
        countCheckFile = 0;
    }

    public void hideAllCheckFile(ArrayList<CheckBox> checkBoxes) {

        for (FileDownload fd : files) {
            fd.setVisibleCheckBox(false);
        }
        toolFileDown.setVisibility(GONE);
        downloadAdapter.notifyDataSetChanged();
        remakeFiles(files);
    }

    public void showAllCheckFile(ArrayList<CheckBox> checkBoxes) {
        for (FileDownload fd : files) {
            fd.setVisibleCheckBox(true);
        }
        downloadAdapter.notifyDataSetChanged();
    }
}
