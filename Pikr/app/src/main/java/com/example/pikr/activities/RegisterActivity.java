package com.example.pikr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pikr.R;
import com.example.pikr.models.Login;


/**
 * Conduct profile registration and store corresponding information
 *
 * Handle differently based whether the profile is being edited or created
 */
public class RegisterActivity extends AppCompatActivity {
    private static final int MIN_PASSWORD_LENGTH = 6;
    ImageView profilePic;
    private EditText name, email, password, phone, major, dartClass;
    private RadioButton isFemale, isMale;
    private Login currentLogin;
    private Class changeToActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        changeToActivity = LoginActivity.class;

        currentLogin = new Login(getApplicationContext());

        if(currentLogin.getNeedAutofill()) setTitle("Profile");

        changeActionBar();
        registerButton();
    }

    private void changeActionBar(){
        if(!currentLogin.getNeedAutofill()) setTitle("Sign up");
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register_menu, menu);
//        if(currentLogin.getNeedAutofill()){
//            menu.findItem(R.id.confirm_register).setTitle("Save");
//        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.confirm_register) {
            handleRegistration();
        }
        else {
            if(changeToActivity.equals(LoginActivity.class)) finishAffinity();      // Clear activity stack if going back to Login
            startActivity(new Intent(this, changeToActivity));
        }
        return super.onOptionsItemSelected(item);
    }

    private void setProfileVariables(){
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        major = findViewById(R.id.major);
        dartClass = findViewById(R.id.dartmouth_class);
        isFemale = findViewById(R.id.is_female);
        isMale  = findViewById(R.id.is_male);
    }

    /**
     * Check whether required profile variables are empty or improperly formatted
     * If incorrect formatting, produce an error using helper method
     *
     * @return whether the text box inputs were all formatted correctly
     */
    private boolean someProfileVarsEmpty(){
        boolean invalid = false;
        if(name.getText().toString().equals("")){
            requestError(name);
            invalid = true;
        }
        if(email.getText().toString().equals("")){
            requestError(email);
            invalid = true;
        }
        // Use default Android method to determine whether the email was formatted properly
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            email.setError("This email address is invalid");
            invalid = true;
        }
        if(password.getText().toString().equals("")){
            requestError(password);
            invalid = true;
        }
        if(!(isMale.isChecked() || isFemale.isChecked())){
            Toast.makeText(getApplicationContext(), "Gender is a required field", Toast.LENGTH_LONG).show();
            invalid = true;
        }
        return invalid;
    }

    /**
     * Helper method to provide an error for a required EditText component having an empty field
     */
    private void requestError(EditText errorProducer){
        errorProducer.setError("This field is required");
    }

    private void saveLogin(){
        String oldPass = currentLogin.getPassword();    // Store old password for later tracking
        currentLogin.clearProfile();
        currentLogin.setName(name.getText().toString());
        currentLogin.setEmail(email.getText().toString());
        currentLogin.setPassword(password.getText().toString());
        currentLogin.setPhone(phone.getText().toString());
        currentLogin.setMajor(major.getText().toString());
        currentLogin.setDartClass(dartClass.getText().toString());
        if(isMale.isChecked()) currentLogin.setGender("male");
        else currentLogin.setGender("female");
        Toast.makeText(getApplicationContext(), "Profile Saved", Toast.LENGTH_LONG).show();

        // Whether or not you're in edit profile mode, if the password has been changed user must login again
        if(!oldPass.equals(currentLogin.getPassword())) changeToActivity=LoginActivity.class;

        if(changeToActivity.equals(LoginActivity.class)) finishAffinity();      // Clear activity stack if going back to Login
        startActivity(new Intent(this, changeToActivity));
    }

    private void registerButton(){
        Button registerButton = findViewById(R.id.confirm_register);
        registerButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                handleRegistration();
            }
        });
    }

    private void handleRegistration(){
        setProfileVariables();
        if(!someProfileVarsEmpty()){
            if(password.getText().toString().length() >= MIN_PASSWORD_LENGTH) {
                saveLogin();
            }
            else{
                password.setError("Password must be at least six characters");
            }
        }
    }
//
//    /**
//     * If the profile needs to be autofilled with current information (occurs in edit profile mode
//     * in MainActivity), fill in the stored values
//     */
//    private void autofill(){
//        try{
//            ((TextView)(findViewById(R.id.confirm_register))).setText(R.string.save);
//            // Load all of the standard profile information values with whatever the user has saved in the past
//            ((EditText)(findViewById(R.id.name))).setText(currentLogin.getName());
//            if(currentLogin.getGender().equals("male")) ((RadioButton)(findViewById(R.id.is_male))).setChecked(true);
//            else((RadioButton)(findViewById(R.id.is_female))).setChecked(true);
//            ((EditText)(findViewById(R.id.email))).setText(currentLogin.getEmail());
//            (findViewById(R.id.email)).setFocusable(false);     //Make the email uneditable
//            ((EditText)(findViewById(R.id.password))).setText(currentLogin.getPassword());
//            ((EditText)(findViewById(R.id.phone))).setText(currentLogin.getPhone());
//            ((EditText)(findViewById(R.id.major))).setText(currentLogin.getMajor());
//            ((EditText)(findViewById(R.id.dartmouth_class))).setText(currentLogin.getDartClass());
//            if(currentLogin.getGender().equals("male")) ((RadioButton)(findViewById(R.id.is_male))).setChecked(true);
//            else((RadioButton)(findViewById(R.id.is_female))).setChecked(true);
//
//            // Once save is clicked, user should be brought to MainActivity (not LoginActivity)
//            changeToActivity = MainActivity.class;
//        }
//        catch(Exception e){
//            System.err.println("Profile not loaded correctly for Autofill");
//            e.printStackTrace();
//        }
//    }
}
