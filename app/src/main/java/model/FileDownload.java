package model;

/**
 * Created by TieuHoan on 18/05/2017.
 */

public class FileDownload {
    private String filePath, fileName;
    private Boolean checkedFile;
    private Boolean isVisibleCheckBox;

    public FileDownload(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.isVisibleCheckBox = false;
        this.checkedFile = false;
    }

    public Boolean getVisibleCheckBox() {
        return isVisibleCheckBox;
    }

    public FileDownload setVisibleCheckBox(Boolean visibleCheckBox) {
        isVisibleCheckBox = visibleCheckBox;
        return this;
    }

    public String getFilePath() {
        return filePath;
    }

    public FileDownload setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public FileDownload setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public Boolean getCheckedFile() {
        return checkedFile;
    }

    public FileDownload setCheckedFile(Boolean checkedFile) {
        this.checkedFile = checkedFile;
        return this;
    }
}
