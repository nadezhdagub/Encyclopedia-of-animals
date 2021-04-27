package com.example.kyrs.Integration;

import android.os.Build;
import android.support.test.runner.AndroidJUnit4;

import androidx.test.core.app.ActivityScenario;

import com.example.kyrs.MainActivity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

@RunWith(AndroidJUnit4.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class AuthorizationTest {


    @Test
    public void authorization(){
       MainActivity mainActivity = new MainActivity();

            ActivityScenario<MainActivity> scenario =
                    ActivityScenario.launch(MainActivity.class);
            scenario.onActivity(activity -> {
                activity.login.setText("test");
                activity.password.setText("test");
                activity.mainMenu.performClick();
                    });
            //String response = mainActivity.execute(UTILS_PATH).get();
          System.out.println( mainActivity.resul);
            Assert.assertEquals("logged", mainActivity.resul);



    }

    @Test
    public void errorAuthorization(){
        MainActivity mainActivity = new MainActivity();

        ActivityScenario<MainActivity> scenario =
                ActivityScenario.launch(MainActivity.class);
        scenario.onActivity(activity -> {
            activity.login.setText("test");
            activity.password.setText("test111");
            activity.mainMenu.performClick();
        });
        //String response = mainActivity.execute(UTILS_PATH).get();
        System.out.println( mainActivity.resul);
        Assert.assertEquals("no", mainActivity.resul);



    }
}
