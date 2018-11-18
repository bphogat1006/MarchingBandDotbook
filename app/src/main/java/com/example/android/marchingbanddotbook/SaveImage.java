package com.example.android.marchingbanddotbook;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLConnection;

public class SaveImage {

    private Context mContext;
    private int mImageResourceId;
    private String mFilename;
    private Bitmap.CompressFormat mCompressFormat = Bitmap.CompressFormat.JPEG;

    private File myPath = null;


    public SaveImage(Context context, int imageResourceId) {
        mContext = context;
        mImageResourceId = imageResourceId;
        mFilename = mContext.getResources().getResourceEntryName(mImageResourceId) + ".jpg";
    }
    public void write() {
        /*
         * Create an image file using a drawable resource
         */
        // new Bitmap
        Bitmap bitmapImage = BitmapFactory.decodeResource(mContext.getResources(), mImageResourceId);
        // create a new directory called 'imageDir'
        File directory = new File(mContext.getFilesDir().getAbsolutePath() + File.separator + "imageDir");
        directory.mkdirs();
        // create the file
        myPath = new File(directory, mFilename);

        if(myPath.exists()) {}
        else{
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(myPath);
                // Use the compress method on the BitMap object to write image to the OutputStream
                bitmapImage.compress(mCompressFormat, 100, fos);
//                Toast.makeText(mContext, "File created", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void open() {
        /*
         * Use android FileProvider to send the image file to a photo app using an intent
         */
        if (myPath.exists()) {

            // create a uri for the intent
            Uri fileUri = FileProvider.getUriForFile(mContext, BuildConfig.APPLICATION_ID, myPath);
            // new intent
            Intent viewFile = new Intent(Intent.ACTION_VIEW);

            viewFile.setDataAndType(fileUri, URLConnection.guessContentTypeFromName(fileUri.toString()));

            // give permissions
            viewFile.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            viewFile.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            try {
                // start the intent
                mContext.startActivity(viewFile);

            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(mContext, "Please install an appropriate application to open this file.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(mContext, "file not found.", Toast.LENGTH_SHORT).show();
        }
    }
}
