package org.jufe.erp.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Raomengnan on 2016/9/4.
 */
public class FileUtil {

    public static boolean deleteFile(String path){
        File file = new File(path);
        if(file.exists())
            return file.delete();
        return false;
    }

    public static boolean writeFile(String path, String name, byte[] bytes) throws IOException {
        FileOutputStream outputStream = null;
        try {
            File filePath = new File(path);
            if(!filePath.exists())
                filePath.mkdirs();

            File file = new File(filePath, name);
            outputStream = new FileOutputStream(file);
            outputStream.write(bytes);
        }finally {
            if(outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return true;

    }
}
