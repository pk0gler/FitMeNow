package app.fitmenow.LoginActivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.fitmenow.R;

/**
 *
 */
public class LogInActivity extends AppCompatActivity {
    private static final String TAG = "LogInActivity";

    private EditText emailText;
    private EditText passwordText;
    private Button loginButton;
    private TextView signUpLink;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = (EditText) findViewById(R.id.input_email);
        passwordText = (EditText) findViewById(R.id.input_password);
        loginButton = (Button) findViewById(R.id.btn_signup);
        loginButton.setText(getResources().getString(R.string.login));
        signUpLink = (TextView) findViewById(R.id.link_login);
        signUpLink.setText(getResources().getString(R.string.not_a_member_sign));

        //When loginLink pressed
        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Finish the registartion and return to the login Activity
                Intent i = new Intent(getBaseContext(), SignUpActivity.class);
                startActivity(i);
                finish();
            }
        });

        //Adding Listener
        //When btn signup pressed
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    /**
     *
     */
    public void login() {
        Log.d(TAG, "LogIn");

        if (this.validate() == false) {
            onLoginFailed();
            return;
        }

        loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LogInActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getResources().getString(R.string.login));
        progressDialog.show();

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        //Signup Logic here !!!

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onLoginSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    /**
     *
     */
    private void onLoginSuccess() {
        loginButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    /**
     *
     */
    private void onLoginFailed() {
        Toast.makeText(getBaseContext(), getResources().getString(R.string.login_failed), Toast.LENGTH_LONG).show();
        loginButton.setEnabled(true);
    }

    /**
     * This Method validates all data inputs and gives feedback when
     * parameters are wrong
     *
     * @return
     */
    public boolean validate() {
        boolean valid = true;

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError(getResources().getString(R.string.enter_valid_email));
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError(getResources().getString(R.string.password_validation_message));
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }

}
