package com.example.kyrs.Integration;

import android.os.Build;
import android.support.test.runner.AndroidJUnit4;

import androidx.test.core.app.ActivityScenario;

import com.example.kyrs.TestUrl;
import com.example.kyrs.registrationActivity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

@RunWith(AndroidJUnit4.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class RegistrationTest {
    private static final String url = "http://192.168.56.1:8080/user/register";

    @Test
    public void registration(){
        registrationActivity registrationActivity = new registrationActivity();

        TestUrl testUrl = new TestUrl();


        ActivityScenario<registrationActivity> scenario =
                ActivityScenario.launch(registrationActivity.class);
        scenario.onActivity(activity -> {
            activity.login.setText("test");
            activity.password.setText("test");
            activity.registrationNew.performClick();
        });
        //String response = mainActivity.execute(UTILS_PATH).get();
        Assert.assertEquals("", registrationActivity.result);



    }
}
