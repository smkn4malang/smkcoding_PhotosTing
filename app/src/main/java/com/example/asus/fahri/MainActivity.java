package com.example.asus.fahri;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.asus.fahri.R.id.btn_create;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private static final int REQUEST_TIMELINE = 0;

    @BindView(R.id.input_email) EditText _emailText;
    @BindView (R.id.input_password) EditText _passwordText;
    @BindView (R.id.btn_login) EditText _loginButton;
    @BindView (R.id.btn_create) EditText _signupLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });


        _signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

        Drawable buttonBg = getResources().getDrawable(R.drawable.ic_launcher_background);

        buttonBg.setAlpha(10);
    }
    public void login() {
        Log.d(TAG, "Login");

        if (!validate()){
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password =_passwordText.getText().toString();

        // TODO: Implement your own authentication Logic here.

        //new Handler().postDelayed(
          //      new Runnable() {
           //             Intent intent = new Intent(getApplicationContext(), TimeLine.class);
             //           startActivityForResult(intent, REQUEST_TIMELINE);
               //         onLoginSucces;

                 //       progressDialog.dismiss();
                //}, 3000);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_SIGNUP){
            if (resultCode == RESULT_OK){

                //TODO: Implement successful signup Logic here
                //By default we just finish Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }

    public void onLoginSucces(){
        _loginButton.setEnabled(true);
        finish();
    }
    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate(){
        boolean valid = true;
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            _emailText.setError("enter a valid email address");
            valid = false;
        }else{
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length()>10){
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        }else{
            _passwordText.setError(null);
        }
        return valid;
    }
}





