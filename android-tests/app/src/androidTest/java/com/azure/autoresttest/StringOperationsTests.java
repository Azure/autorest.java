package com.azure.autoresttest;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.androidtest.fixtures.bodystring.StringOperationClient;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class StringOperationsTests {
    private static StringOperationClient client;
    private CountDownLatch lock = new CountDownLatch(1);

    @BeforeClass
    public static void setup() {
        client = new StringOperationClient.Builder().host("http://100.64.231.51:3000").build();
    }

    @Test
    public void getNull() throws Exception {
        Assert.assertNull(client.getNullWithRestResponse().getValue());
    }
}
