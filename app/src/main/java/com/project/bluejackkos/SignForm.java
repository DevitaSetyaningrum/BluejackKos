package com.project.bluejackkos;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignForm extends AppCompatActivity {

    EditText newuser_name;
    EditText newuser_number;
    EditText newuser_password;
    EditText newuser_confirmpassword;
    RadioGroup newuser_gender;
    RadioButton female_button;
    TextView result;
    Button register;
    CheckBox checkAgree;

    String newuserId = "US000";

    Button newuser_dob;
    TextView user_dob;

    SimpleDateFormat sdf;

    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                user_dob.setText(sdf.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("RegisterForm");
        setContentView(R.layout.signup_form);

        newuser_name = findViewById(R.id.new_username);
        newuser_number = findViewById(R.id.number_user);
        newuser_password = findViewById(R.id.newpassword_user);
        newuser_confirmpassword = findViewById(R.id.confirmpassword_user);
        female_button = findViewById(R.id.female_gender);
        register = findViewById(R.id.register);
        result = findViewById(R.id.result);
        checkAgree = findViewById(R.id.check_agree);

        newuser_dob = findViewById(R.id.dob);
        user_dob = findViewById(R.id.user_dob);

        female_button.toggle();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validationAvailable();
            }
        });

        sdf = new SimpleDateFormat("dd-MM-yyy", Locale.US);
        newuser_dob.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
    }

    public void doneRegister(){
        Context ctx = getApplicationContext();
        Toast toas = Toast.makeText(ctx, "Successfully registered", Toast.LENGTH_SHORT);
        toas.show();
        finish();
    }

    boolean isSame(){
        CharSequence newpassword = newuser_password.getText().toString();
        CharSequence confirmpassword = newuser_confirmpassword.getText().toString();

        if(newpassword.equals(confirmpassword)) {
            return true;
        } else {
            Context ctx = getApplicationContext();
            Toast t = Toast.makeText(ctx, "Password doesn't match", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
    }

    boolean validPassword(){
        Pattern pattern;
        Matcher matcher;
        CharSequence password = newuser_password.getText().toString();

        String pass = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{6,}$";
        pattern = Pattern.compile(pass);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

    boolean validUsername(){
        Pattern pattern2;
        Matcher matcher2;
        String username = newuser_name.getText().toString();

        String name = "^(?=.*[A-z])(?=.*[0-9]).{3,25}$";
        pattern2 = Pattern.compile(name);
        matcher2 = pattern2.matcher(username);

        return matcher2.matches();
    }

    void validationAvailable(){
        String userName = newuser_name.getText().toString();
        String userPassword = newuser_password.getText().toString();
        String userNumber = newuser_number.getText().toString();

        String userDob = user_dob.getText().toString();

        // Username
        if(userName.isEmpty()){
            newuser_name.setError("Name required");
        }
        if(userName.length() < 3 || userName.length() > 25){
            newuser_name.setError("Username must be between 3 and 25 characters");
        }
        if(!validUsername()){
            Context ctx = getApplicationContext();
            Toast invalidU = Toast.makeText(ctx, "Username must be unique", Toast.LENGTH_SHORT);
            invalidU.show();
        }

        //Password
        if(userPassword.isEmpty()){
            newuser_password.setError("Password required");
        }
        if(userPassword.length() < 6){
            newuser_password.setError("Password must be more than 6 characters");
        }
        if(!validPassword()){
            Context ctx = getApplicationContext();
            Toast invalidP = Toast.makeText(ctx, "Password must be more than 6 Characters and contains at least 1 lowercase letter, 1 uppercase letter and 1 digit", Toast.LENGTH_SHORT);
            invalidP.show();
        }

        //PhoneNumber
        boolean isPhoneValid = true;
        if(userNumber.isEmpty()){
            newuser_number.setError("Phone Number required");
            isPhoneValid = false;
        }
        if(userNumber.length() < 10 || userNumber.length() > 12){
            newuser_number.setError("Phone Number must be between 10 and 12 digits");
            isPhoneValid = false;
        }

//       DateofBirth
        String dateofBirth = "DD/MM/YYYY";
        if(dateofBirth.equals(userDob)){
            newuser_dob.setError("Date required");
        }

//      Gender
        String  gendernya = (female_button.isChecked())? "Female" :  "Male";
        if (!checkAgree.isChecked()){
            Toast.makeText(this, "Terms must be checked", Toast.LENGTH_SHORT).show();
        }

        if( isPhoneValid && !dateofBirth.equals(userDob)
                && isSame() && checkAgree.isChecked() && validUsername() && validPassword()){
            doneRegister();
        }

        int idx = Helper.dataUser.size() + 1;
        if(idx > 99){
            newuserId = "US" + idx;
        } else if( idx > 9){
            newuserId = "US0" + idx;
        } else {
            newuserId = "US00" + idx;
        }

        User user = new User(userName, userPassword, userNumber, gendernya, userDob, newuserId);
        Helper.dataUser.add(user);
    }
}
