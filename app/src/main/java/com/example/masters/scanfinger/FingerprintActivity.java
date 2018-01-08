package com.example.masters.scanfinger;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.credenceid.biometrics.Biometrics;
import com.credenceid.biometrics.BiometricsActivity;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Random;
import static android.R.attr.bitmap;
import static android.R.attr.key;
import static android.R.attr.path;

public class FingerprintActivity extends BiometricsActivity {

    String ID;
    ImageView imageView;
    TextView statustext;
    Button start, delete, save;
    int i = 0;

    private static int close_cmd_counter = 0;
    private static int open_cmd_counter = 0;
    private Biometrics mBiometrics;


//    public static void createFolder() {
//        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES.toString() + "/123");
//        try {
//            if (!dir.exists()) {
//                dir.mkdir();
//            }
//        } catch (Exception ex) {
//
//        }
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint);

        ID = getIntent().getStringExtra("ID");
//
        imageView = (ImageView) findViewById(R.id.imageView);
        statustext = (TextView) findViewById(R.id.status);

        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statustext.setText("Grab Fingerprint Start");
                imageView.setImageDrawable(null);
                grabFingerprint();
            }
        });


        delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageDrawable(null);
                onCloseFingerprintReader(null);

//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                intent.setType("image/*");
//                startActivityForResult(Intent.createChooser(intent,"Select Picture "),222);

            }
        });



        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UploadPictureViaWebservice(fname,bitmap);
            }
        });
//        Bitmap icon = BitmapFactory.decodeResource(getResources(),
//                R.id.imageView);
//
//        SaveImage(icon);
//        SaveImage(Bitmap);

//        start = (Button) findViewById(R.id.start);
//        start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onCapture();
//            }
//        });
//        start = (Button) findViewById(R.id.start);
//        start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                statustext.setText("Grab Fingerprint Start");
//                imageView.setImageDrawable(null);
//                grabFingerprint();
////                ImageView imgView = (ImageView) findViewById(R.id.imageView);
////                imgView.setImageResource(android.R.color.transparent);
//
////                File dir= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES.toString() + "/WAC");
////                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
////                fileName = device_id + "_" + timeStamp + ".jpg";
////
////                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////                File f = new File(dir, fileName);
////                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
////                uri = Uri.fromFile(f);
////                startActivityForResult(intent, 111);
//
//            }
//        });


//        createFolder();
    }

//    private static final String KEY = "";
    public static String fileName = Key.idKey ;
    public static byte[] fileContents = null;
    String device_id = "";
    Uri uri;

//    public void onButtonClicked(View v) {
//
//        start = (Button) findViewById(R.id.start);
//        start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                statustext.setText("Grab Fingerprint Start");
//                imageView.setImageDrawable(null);
//                grabFingerprint();
//            }
//        });
//        delete = (Button) findViewById(R.id.delete);
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//                startActivity(getIntent());
////                Intent intent = getIntent();
////                finish();
////                startActivity(intent);
////                statustext.setText("Grab Fingerprint Start");
////                imageView.setImageDrawable(null);
////                grabFingerprint();
//            }
//        });
//        save = (Button) findViewById(R.id.save);
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
//
//
//
//
////        switch (v.getId()) {
////            case R.id.start:
////                statustext.setText("Grab Fingerprint Start");
////                imageView.setImageDrawable(null);
////                grabFingerprint();
////
////            case R.id.delete:
////                Intent i = new Intent(getApplicationContext(), FingerprintActivity.class);
////                startActivity(i);
//////                status.setText("Smartcard Start");
//////                openClose(false);
//////                openClose(true);
////            case R.id.save:
////                    //uploadPhoto(v);
////                    Intent intent = new Intent(FingerprintActivity.this, ResultActivity.class);
//////                    intent.
////                    startActivity(intent);
////                    finish();
//////                statustext.setText("Camera Start");
//////                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES.toString());
//////                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//////                fileName = timeStamp + ".jpg";
//////
//////                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//////                File f = new File(dir, fileName);
//////                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
//////                uri = Uri.fromFile(f);
//////                startActivityForResult(intent, 111);
////        }
//    }


    @Override
    public void onFingerprintGrabbed(ResultCode result, Bitmap bitmap,
                                     byte[] iso, String filepath, String status) {
//        if (status != null)
//            statustext.setText(status);
//        if (bitmap != null)
//            imageView.setImageBitmap(bitmap);
//        SaveImage(bitmap);

        if(status != null){
            statustext.setText(status);
        }
        if(bitmap != null){
                imageView.setImageBitmap(bitmap);
//            if( delete != null){
                SaveImage(bitmap);
//            }

        }
//        SaveImage(bitmap);
//        MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "", "");
//        onButtonClicked(imageView);
    }

    public void onCloseFingerprintReader(CloseReasonCode reasonCode) {
        statustext.setText("Sensor Closed. Reason=" + reasonCode);
    }

    private void SaveImage(Bitmap finalBitmap) {
    i+=1;
//        String root = Environment.getExternalStorageDirectory().toString();
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES.toString()).toString();
        File myDir = new File(root + "/" + Key.idKey);

        myDir.mkdirs();
//        Random generator = new Random();
//        int n = 10;
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        n = generator.nextInt(n);
//            String fname = "Image-" + timeStamp + "-" +Integer.valueOf(i) + ".jpg";
            String fname = "Image-" + Integer.valueOf(i).toString() + ".jpg";
            File file = new File(myDir, fname );
            if (file.exists())
                file.delete();
            try {
                FileOutputStream out = new FileOutputStream(file);
                finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            final Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            final Uri contentUri = Uri.fromFile(myDir);
            scanIntent.setData(contentUri);
            sendBroadcast(scanIntent);
        } else {
            final Intent intent = new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory()));
            sendBroadcast(intent);
        }

//        sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
//                Uri.parse("file://" + Environment.getExternalStorageDirectory())));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 111) {  // take photo

                int orientation = -1;
                ExifInterface exif;

                Uri selectedImage = uri;
                getContentResolver().notifyChange(selectedImage, null);
                Bitmap reducedSizeBitmap = getBitmap(uri.getPath()); // convert source picture to bitmap 500KB

                //+
                try {
                    exif = new ExifInterface(selectedImage.getPath());
                    orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                            ExifInterface.ORIENTATION_NORMAL);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:   // 6
                        reducedSizeBitmap = rotateImage(reducedSizeBitmap, 90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:  // 3
                        reducedSizeBitmap = rotateImage(reducedSizeBitmap, 180);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:  // 8
                        reducedSizeBitmap = rotateImage(reducedSizeBitmap, 270);
                        break;
                    default:   // 0
                        //reducedSizeBitmap = rotateImage(reducedSizeBitmap, 0);
                }
                //-

                ImageView imgView = (ImageView) findViewById(R.id.imageView);
                imgView.setImageBitmap(reducedSizeBitmap);

                delete.setVisibility(View.VISIBLE);
                galleryAddPic();
            }
        }
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {  // rotate bitmap
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    public Bitmap getBitmap(String path) {            // return Bitmap from file path

        Uri uri = Uri.fromFile(new File(path));
        InputStream in = null;
        try {
            final int IMAGE_MAX_SIZE = 500000; // 500 KB
            in = getContentResolver().openInputStream(uri);

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(in, null, o);
            in.close();


            int scale = 1;
            while ((o.outWidth * o.outHeight) * (1 / Math.pow(scale, 2)) >
                    IMAGE_MAX_SIZE) {
                scale++;
            }
            //Log.d("", "scale = " + scale + ", orig-width: " + o.outWidth + ", orig-height: " + o.outHeight);

            Bitmap b = null;
            in = getContentResolver().openInputStream(uri);
            if (scale > 1) {
                scale--;
                // scale to max possible inSampleSize that still yields an image
                // larger than target
                o = new BitmapFactory.Options();
                o.inSampleSize = scale;
                b = BitmapFactory.decodeStream(in, null, o);

                // resize to desired dimensions
                int height = b.getHeight();
                int width = b.getWidth();
                //Log.d("", "1th scale operation dimenions - width: " + width + ", height: " + height);

                double y = Math.sqrt(IMAGE_MAX_SIZE
                        / (((double) width) / height));
                double x = (y / height) * width;

                Bitmap scaledBitmap = Bitmap.createScaledBitmap(b, (int) x,
                        (int) y, true);
                b.recycle();
                b = scaledBitmap;

                System.gc();
            } else {
                b = BitmapFactory.decodeStream(in);
            }
            in.close();

            //Log.d("", "bitmap size - width: " + b.getWidth() + ", height: " + b.getHeight());
            return b;
        } catch (IOException e) {
            //Log.e("", e.getMessage(), e);
            return null;
        }
    }

    private void galleryAddPic() {
//        try {
//            if (Build.VERSION.SDK_INT >= 9) {
//        Bitmap icon = BitmapFactory.decodeResource(getResources(),
//                        R.id.imageView);
//                SaveImage(icon);
//            } else {
//                sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory()+ "/"+ Key.idKey)));
//            }
////            File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES.toString() + "/"+ Key.idKey);
////            File f = new File(dir, fileName);
////            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(f)));
//        } catch (Exception e) {
//            e.printStackTrace();
//            //Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
//            //DisplayToast(e.toString());
//        }
        try {
            if (Build.VERSION.SDK_INT >= 9) {
                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                File f1 = new File("file://" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/"+ Key.idKey));
                Uri contentUri = Uri.fromFile(f1);
                mediaScanIntent.setData(contentUri);
                sendBroadcast(mediaScanIntent);
            } else {
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory()+ "/"+ Key.idKey)));
            }
            File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES.toString() + "/"+ Key.idKey);
            File f = new File(dir, fileName);
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(f)));
        } catch (Exception e) {
            e.printStackTrace();
            //Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
            //DisplayToast(e.toString());
        }

    }

//    private void uploadPhoto(View v) {
//
//        String strResult = UploadPictureViaWebservice(fileName, fileContents);
//        String _return = strResult.replaceAll("\\s", "");
//        if (_return == "false") {
//            Toast.makeText(FingerprintActivity.this, "Failed!!", Toast.LENGTH_LONG).show();
//        } else {
//            byte[] imageAsBytes = Base64.decode(strResult.getBytes(), Base64.DEFAULT);
//            ImageView image = (ImageView) findViewById(R.id.imageView);
//            image.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
//            Toast.makeText(FingerprintActivity.this, "Successed!", Toast.LENGTH_LONG).show();
//        }
//
//        fileContents = null;
//
//    }

    public String UploadPictureViaWebservice(String filename, byte[] imgArr) {

        if (filename == "") {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            filename = device_id + "_" + timeStamp + ".jpg";
            fileName = filename;
        }

        String strResponse = "";
        String encodedImage = Base64.encodeToString(imgArr, Base64.DEFAULT);

        String URL = "http://10.0.0.49/serviceneurotec/WebService1.asmx";
        String NAMESPACE = "http://tempuri.org/";
        String METHOD_NAME = "NeuroEnroll";
        String SOAP_ACTION = "http://tempuri.org/NeuroEnroll";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        /**** with parameter *****/
        PropertyInfo pi;
        pi = new PropertyInfo();
        pi.setName("ID");
        pi.setValue(filename);
        pi.setType(String.class);
        request.addProperty(pi);

        pi = new PropertyInfo();
        pi.setName("StringBase64");
        pi.setValue(encodedImage);
        pi.setType(String.class);
        request.addProperty(pi);
        /*************************/

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        androidHttpTransport.debug = true;
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapObject response;
            response = (SoapObject) envelope.bodyIn;
            strResponse = response.getProperty(0).toString();
        } catch (Exception e) {
            //e.printStackTrace();
            strResponse = e.toString();
        }

        return strResponse;

    }
}