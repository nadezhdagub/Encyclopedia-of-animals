package com.example.kyrs;

import android.os.Build;
import android.support.test.runner.AndroidJUnit4;

import androidx.test.core.app.ActivityScenario;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

@RunWith(AndroidJUnit4.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class registrationActivityTest extends TestCase {
    public final MockWebServer server = new MockWebServer();

    @Before
    public void serverSetupUp() throws IOException {
        server.start(8100);
    }

    @After
    public void serverShutDown() throws IOException {
        server.shutdown();
    }

    @Test
    public void testAddOnButton() {
        server.enqueue(new MockResponse().setBody(""));
        String url = server.url("/").toString();

        registrationActivity registrationAct = new registrationActivity();
        assertEquals(null, registrationAct.login);
        assertEquals(null, registrationAct.password);

        ActivityScenario<registrationActivity> scenario =
                ActivityScenario.launch(registrationActivity.class);
        //registrationAct.setUrl(url);
        scenario.onActivity(activity -> {
            activity.login.setText("test");
            activity.password.setText("test");
            activity.registrationNew.performClick();
        });

        assertEquals("", registrationAct.result);
    }
}