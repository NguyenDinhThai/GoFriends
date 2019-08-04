package com.example.thainguyen.gofriends;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity {

    EditText edtID;
    EditText edtPass;
    Button btnLogin;
    int idGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtID = findViewById(R.id.edt_id);
        edtPass = findViewById(R.id.edt_pass);
        btnLogin = findViewById(R.id.btn_login);
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String pass = edtID.getText().toString().trim();
        try {
            idGroup = Integer.parseInt(pass);
        }catch (NumberFormatException e)
        {

        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStart();

                //Toast.makeText(MainActivity.this, idGroup, Toast.LENGTH_SHORT).show();
                //getGroupPass();
            }
        });

    }
    public void getStart()
    {
        try {
            final String NAMESPACE = "http://ndinhthai.org/";

            final String METHOD_NAME = "HelloWorld";

            final String SOAP_ACTION = NAMESPACE + METHOD_NAME;

            final String URL  = "http://www.ndinthai.somee.com/myWebService.asmx?WSDL";

            SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

            httpTransportSE.call(SOAP_ACTION,envelope);

            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

            Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void getGroupPass()
    {
        try
        {
            final String  Namespace =  "http://ndinhthai.org/";

            final String MethodName = "getPassGroup";

            final String URL  = "http://www.ndinthai.somee.com/myWebService.asmx?WSDL";

            final String SoapAction = Namespace + MethodName;

            SoapObject request = new SoapObject(Namespace,MethodName);

            request.addProperty("id",idGroup);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            //MarshalFloat marshal=new MarshalFloat();
            //marshal.register(envelope);

            HttpTransportSE httpTransportSE  = new HttpTransportSE(URL);

            httpTransportSE.call(SoapAction,envelope);

            SoapObject getPass = (SoapObject) envelope.getResponse();

            for(int i=0;i<=getPass.getPropertyCount();i++)
            {
                SoapObject pass  = (SoapObject) getPass.getProperty(i);
                String groupPass = pass.getProperty("passgroup").toString();
                edtPass.setText(groupPass);
            }



            ///envelope.setOutputSoapObject(getPass);


            //SoapPrimitive response = (SoapPrimitive) envelope.getResponse();



        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
