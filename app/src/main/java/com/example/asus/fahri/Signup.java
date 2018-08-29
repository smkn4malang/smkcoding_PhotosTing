package com.example.asus.fahri;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Signup extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    @BindView(R.id.input_name) EditText _nametext;
    @BindView(R.id.input_email) EditText _emailText;
    @BindView (R.id.input_password) EditText _passwordText;
    @BindView (R.id.btn_create) EditText _signupButton;
    @BindView (R.id.btn_login) EditText _loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        } );

        _loginLink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void signup(){
        Log.d(TAG, "Signup");

        if (!validate()){
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Signup.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = _nametext.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        //TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
            new Runnable(){
                @Override
                public void run(){
                    onSignupSuccess();
                    progressDialog.dismiss();
                }
            }, 3000);
        }

        public void onSignupSuccess(){
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
        }

    public void onSignupFailed(){
        Toast.makeText(getBaseContext(), "Login Faiiled", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled (true);
    }

        public boolean validate(){
        boolean valid = true;
        String name = _nametext.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3){
            _nametext.setError("at least 3 chacharacters");
            valid = false;
        }else{
            _nametext.setError(null);
        }

            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                _emailText.setError("enter a valid email address");
                valid = false;
            }else{
                _emailText.setError(null);
            }

            if (password.isEmpty() || password.length() < 4 || password.length() >10 ){
                _passwordText.setError("between 4 and alphanumeric characters");
                valid = false;
            }else{
                _passwordText.setError(null);
            }

            return valid;
        }

}







