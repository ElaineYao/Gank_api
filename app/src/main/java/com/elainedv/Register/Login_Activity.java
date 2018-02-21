package com.elainedv.Register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.elainedv.Gank.MainActivity;
import com.elainedv.Gank.R;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG=Login_Activity.class.getSimpleName();
    public static final int SUCCEED=0;
    public static final int INCORRECT=1;
    public static final int NOT_EXIST=2;
    EditText login_user,login_pass;
    Button login_bt;
    TextView login_link;
    ProgressDialog dialog;
    Realm realm;
    String user_name,password;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Realm.init(this);
        Log.i("-----"+TAG+"------",TAG);
        initView();
    }

    public void initView(){
        login_user=(EditText)findViewById(R.id.login_user);
        login_pass=(EditText)findViewById(R.id.login_password);
        login_bt=(Button)findViewById(R.id.login_bt);
        login_link=(TextView)findViewById(R.id.login_tv);
        RealmConfiguration configuration=new RealmConfiguration.Builder()
                                            .name("myRealm.realm")
                                            .build();
        realm=Realm.getInstance(configuration);
        dialog=new ProgressDialog(Login_Activity.this,R.style.AppTheme_Dialog);

        login_bt.setOnClickListener(this);
        login_link.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        int index=SUCCEED;
        login_bt.setEnabled(false);
        user_name=login_user.getText().toString();
        password=login_pass.getText().toString();
        switch (view.getId()){
            case R.id.login_bt:
                dialog.setMessage("Authenticating...");
                dialog.setIndeterminate(true);
                dialog.show();

                RealmResults<User> users=realm.where(User.class).findAll();
                for(User user:users){
                    if(user_name.equals(user.getUser())){
                        if(password.equals(user.getPassword())){

                            index=SUCCEED;
                            Log.i("---"+TAG+"  onClick succeed--","..");
                            break;
                        }else {
                            index=INCORRECT;
                            Log.i("---"+TAG+"  onClick incorrect-","..");
                        }
                    }else {
                        index=NOT_EXIST;
                        Log.i("---"+TAG+"  onClick notexist--","..");
                    }
                }
                dialog.dismiss();
            switch (index){
                case SUCCEED:
                    Intent intent=new Intent(Login_Activity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
                    break;
                case INCORRECT:
                    login_pass.setError("The password is not correct.");
                    login_bt.setEnabled(true);
                    break;
                case NOT_EXIST:
                    login_user.setError("The user name doesn't exist.");
                    login_bt.setEnabled(true);
                    break;
            }
            break;
            case R.id.login_tv:
                Intent intent=new Intent(Login_Activity.this,Signup_Activity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
