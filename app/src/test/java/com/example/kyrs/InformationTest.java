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
public class InformationTest extends TestCase {
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
        server.enqueue(new MockResponse().setBody("Волк"));
        String url = server.url("/").toString();

        Information information = new Information();
        TestUrl testUrl = new TestUrl();

        testUrl.setUrl(url);
        ActivityScenario<Information> scenario =
                ActivityScenario.launch(Information.class);
        scenario.onActivity(activity -> {
            testUrl.setUrl(url);
        });
        assertEquals("Волк", information.result);


        //assertEquals("Волк", information.result);
    }
}