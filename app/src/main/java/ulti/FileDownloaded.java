package ulti;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import model.FileDownload;

/**
 * Created by TieuHoan on 18/05/2017.
 */

public class FileDownloaded {

    public ArrayList<FileDownload> getFileDownLoad(String typeFile) {

        File home = new File(DownloadTask.PATH_FOLDER);
        FileDownload fileDownload;
        String filePath, fileName;

        ArrayList<FileDownload> fileDownloaded = new ArrayList<>();
        if (home.exists() && home.listFiles(new FileFilter(typeFile)).length > 0) {
            for (File file : home.listFiles(new FileFilter(typeFile))) {
                filePath = file.getPath();
                fileName = file.getName().substring(0, (file.getName().length() - 4));
                fileDownload = new FileDownload(filePath, fileName);
                fileDownloaded.add(fileDownload);
            }
        }


        return fileDownloaded;
    }


    public class FileFilter implements FilenameFilter {
        String typeFile;

        public FileFilter(String typeFile) {
            this.typeFile = typeFile;
        }

        @Override
        public boolean accept(File dir, String name) {
            return (name.endsWith(typeFile));
        }

    }

}
