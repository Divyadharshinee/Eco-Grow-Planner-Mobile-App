package com.example.ecogrowplanner.ui.community;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class RealPathUtil {
    public static String getRealPath(Context context, Uri uri) {
        String result = null;
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            if (cursor.moveToFirst()) {
                result = cursor.getString(columnIndex);
            }
            cursor.close();
        }
        return result;
    }
}


