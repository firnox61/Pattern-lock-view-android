package com.enes.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

import io.paperdb.Paper;

public class LockScreenActivity extends AppCompatActivity {
    String save_pattern_key = "pattern_code";
    PatternLockView mPatternLockView;
    String final_pattern="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);

        Paper.init(this);
        String save_pattern = Paper.book().read(save_pattern_key);
        if (save_pattern != null && !save_pattern.equals("null")) {
            setContentView(R.layout.pattern_screen);
            mPatternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
            mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
                @Override
                public void onStarted() {

                }

                @Override
                public void onProgress(List<PatternLockView.Dot> progressPattern) {

                }

                @Override
                public void onComplete(List<PatternLockView.Dot> pattern) {
                    final_pattern= PatternLockUtils.patternToString(mPatternLockView,pattern);
                    if(final_pattern.equals(save_pattern))
                        Toast.makeText(LockScreenActivity.this, "Password Doğru", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(LockScreenActivity.this, "Password hatalı", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCleared() {

                }
            });
        }
        else {
            setContentView(R.layout.activity_lock_screen);
            mPatternLockView=(PatternLockView) findViewById(R.id.pattern_lock_view);
            mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
                @Override
                public void onStarted() {

                }

                @Override
                public void onProgress(List<PatternLockView.Dot> progressPattern) {

                }

                @Override
                public void onComplete(List<PatternLockView.Dot> pattern) {
                    final_pattern=PatternLockUtils.patternToString(mPatternLockView,pattern);
                }

                @Override
                public void onCleared() {

                }
            });
            Button btnSetup=(Button) findViewById(R.id.btnSetPattern);
            btnSetup.setOnClickListener((v)->{

                Paper.book().write(save_pattern_key,final_pattern);
                Toast.makeText(LockScreenActivity.this, "SavePAttern okay", Toast.LENGTH_SHORT).show();
               // Intent i=new Intent(LockScreenActivity.this,HomeActivity.class);
                finish();


            });
        }
    }
}