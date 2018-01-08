package com.example.masters.scanfinger;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.credenceid.biometrics.Biometrics;
import com.credenceid.biometrics.BiometricsActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.R.attr.data;

public class MainActivity extends BiometricsActivity {

    private static int close_cmd_counter = 0;
    private static int open_cmd_counter = 0;
    private Biometrics mBiometrics;

    //    String ID;
    TextView show;
    private static final String KEY = "";
    ImageView image_view;
//    TextView text_view;

    public static String fileName = "";
    public static byte[] fileContents = null;
    String device_id= "";
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ID = getIntent().getStringExtra("ID");
        show = (TextView) findViewById(R.id.idshow);
        show.setText(Key.idKey);

    }


    public void onButtonClicked(View v) {

        switch (v.getId()) {
            case R.id.finger:
                Intent a = new Intent(getApplicationContext(), FingerprintActivity.class);
                startActivity(a);
                break;
            case R.id.face:
                Intent b = new Intent(getApplicationContext(), FaceActivity.class);
                startActivity(b);
////                text_view.setText("Camera Start");
//                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES.toString());
//                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//                fileName = timeStamp + ".jpg";
//
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                File f = new File(dir, fileName);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
//                uri = Uri.fromFile(f);
//                startActivityForResult(intent, 111);
                break;
            case R.id.eye:
                Intent c = new Intent(getApplicationContext(), IrisActivity.class);
                startActivity(c);
//                text_view.setText("Camera Start");
//                File dir= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES.toString());
//                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//                fileName =  timeStamp + ".jpg";
//
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                File f = new File(dir, fileName);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
//                uri = Uri.fromFile(f);
//                startActivityForResult(intent, 111);
                break;
        }
    }


}
