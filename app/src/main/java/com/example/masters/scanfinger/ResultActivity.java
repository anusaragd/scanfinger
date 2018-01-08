package com.example.masters.scanfinger;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultActivity extends Activity {

    String deviceName = "";
    String device_id= "";

    String fileNameR="";
    byte[] fileContentsR = null;

    TextView txtStudentID;
    TextView txtStudentName;
    TextView txtStudentScore;

    static String  strID="";
    static String  strName="";
    static String  strScore="";


    @Override
    public void onBackPressed() {
        if(isTaskRoot()) {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setTitle("Result");

        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        deviceName = android.os.Build.BRAND.toLowerCase() + "-" + android.os.Build.MODEL.toLowerCase();
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        device_id = tm.getDeviceId();


        fileNameR = FingerprintActivity.fileName;
        fileContentsR = FingerprintActivity.fileContents;

        uploadPhoto();

    }



    private void uploadPhoto() {

        String strResult = UploadPictureViaWebservice(fileNameR,fileContentsR);

        strResult = strResult + "#" + "XXXXXXXXXXXX" + "#" + "Unkonown" + "#" + "0/0";

        String _return = strResult.replaceAll("\\s","");
        if (_return=="false") {
            Toast.makeText(ResultActivity.this,"Failed!!", Toast.LENGTH_LONG).show();
        }else {   ;
            String[] arr_str = strResult.split("#");

            strID = arr_str[1];
            strName = arr_str[2];
            strScore = arr_str[3];

            strResult = arr_str[0];

            byte[] imageAsBytes = Base64.decode(strResult.getBytes(), Base64.DEFAULT);
            ImageView image = (ImageView) findViewById(R.id.imgView);
            image.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));

            Toast.makeText(ResultActivity.this,"Successed!", Toast.LENGTH_LONG).show();

            txtStudentID = (TextView) findViewById(R.id.txtStudentID);
            txtStudentName = (TextView) findViewById(R.id.txtStudentName);
            txtStudentScore = (TextView) findViewById(R.id.txtStudentScore);
            txtStudentID.setText("   ID : " + strID);
            txtStudentName.setText("   Name : " + strName);
            txtStudentScore.setText("   Score : " + strScore);

        }

        fileContentsR = null;
        FingerprintActivity.fileContents = null;

    }
    public String UploadPictureViaWebservice(String filename, byte[] imgArr) {

        if (filename == "") {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            filename = device_id + "_" + timeStamp + ".jpg";
            fileNameR = filename;
        }

        String strResponse="";
        String encodedImage = Base64.encodeToString(imgArr, Base64.DEFAULT);

//		String URL =  "http://203.151.213.80/ServiceAforge/Wacservice.asmx";
//		String NAMESPACE = "http://tempuri.org/";
//		String METHOD_NAME = "_MainProcessStringImage";
//		String SOAP_ACTION = "http://tempuri.org/_MainProcessStringImage/";
        String URL =  "http://10.0.0.49/serviceneurotec/WebService1.asmx";
        String NAMESPACE = "http://tempuri.org/";
        String METHOD_NAME = "NeuroEnroll";
        String SOAP_ACTION = "http://tempuri.org/NeuroEnroll/";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


//        encodedImage = "1";
//        filename = "2";
//		/**** with parameter *****/
        PropertyInfo pi;
        pi=new PropertyInfo();
        pi.setName("ID");
        pi.setValue(filename);
        pi.setType(String.class);
        request.addProperty(pi);

        pi=new PropertyInfo();
        pi.setName("StringBase64");
        pi.setValue(encodedImage);
        pi.setType(String.class);
        request.addProperty(pi);
//		/*************************/

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        androidHttpTransport.debug = true;
        try
        {
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapObject response;
            response= (SoapObject) envelope.bodyIn;
            strResponse = response.getProperty(0).toString();


        }
        catch (Exception e)
        {
            //e.printStackTrace();
            strResponse = e.toString();
        }

        return strResponse;

    }
}