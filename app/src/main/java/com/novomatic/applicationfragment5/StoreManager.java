package com.novomatic.applicationfragment5;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by fpirazzi on 03/03/2015.
 */
public class StoreManager {


    public void SaveData(Context context, String dati){

        String FILENAME = "Prospect";
        String string = dati;

        try {
            FileOutputStream fos;
            fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(string.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String GetData(Context context){

        String FILENAME = "Prospect";
        String string = "";
        StringBuffer fileContent = new StringBuffer("");

        try {
            FileInputStream fis;
            fis = context.openFileInput(FILENAME);

            byte[] buffer = new byte[1024];

            int n;

            while ((n = fis.read(buffer)) != -1)
            {
                fileContent.append(new String(buffer, 0, n));
            }

            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileContent.toString();

    }


}
