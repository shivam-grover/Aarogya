package com.example.fgw.sahay_emvehivle;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.editText4)
    EditText editText4;
    @BindView(R.id.linear)
    LinearLayout linear;
    @BindView(R.id.imageButton)
    Button imageButton;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"CaviarDreams_Bold.ttf");
        imageButton.setTypeface(typeface);
        mAuth = FirebaseAuth.getInstance();


    }

    @OnClick({R.id.imageButton, R.id.textView, R.id.textView2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageButton:
                login();

                break;
            case R.id.textView:
                break;
            case R.id.textView2:
                startActivity(new Intent(this,SignUpActivity.class));
                break;
        }
    }

    private void login() {
        final String emailstring = editText.getText ().toString ().trim ();

        // to get Password from user and store it in variable called Password
        final String PassWord = editText4.getText ().toString ().trim ();
        if(emailstring.isEmpty ())
        {
            //set an error
            editText.setError ("Email is required");
            //and highlight that box
            editText.requestFocus ();
            return;
        }
        if(PassWord.isEmpty ())
        {
            //set an error
            editText4.setError ("Password is required");
            //it will focus on password
            editText4.requestFocus ();
            return;
        }
        if(PassWord.length ()<6)
        {
            editText4.setError ("Minimum length of password required is 6");
            editText4.requestFocus ();
            return;
        }
        if(android.util.Patterns.EMAIL_ADDRESS.matcher(emailstring).matches())
        {
            mAuth.signInWithEmailAndPassword (emailstring,PassWord).addOnCompleteListener (new OnCompleteListener<AuthResult>( ) {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
//                    progressBar.setVisibility (View.GONE);
                    if (task.isSuccessful ())
                    {
                        //if login is successful then
                        Intent intent = new Intent (MainActivity.this, SignUpActivity.class);
                        intent .addFlags (intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity (intent);
                        Toast.makeText (getApplicationContext (),"log in",Toast.LENGTH_SHORT).show ();

                    }else
                    {
                        //else
                        Toast.makeText (getApplicationContext (), task.getException ().getMessage (),Toast.LENGTH_SHORT).show ();
                    }
                }
            });}

    }
}
