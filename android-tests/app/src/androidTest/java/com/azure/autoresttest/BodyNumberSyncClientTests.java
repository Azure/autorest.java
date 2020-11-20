package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.android.core.internal.util.serializer.exception.MalformedValueException;
import com.azure.androidtest.fixtures.bodynumber.AutoRestNumberTestServiceClient;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class BodyNumberSyncClientTests {
    private static AutoRestNumberTestServiceClient client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestNumberTestServiceClient.Builder().host(TestConstants.TestServerRootUrl).build();
    }

    @Test
    public void getNull() {
        final Response<Float> getResponse = client.getNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertNull(getResponse.getValue());
    }

    @Test
    public void getInvalidFloat() {
        try {
            client.getInvalidFloatWithRestResponse();
            fail();
        } catch (Exception exception) {
            assertEquals(MalformedValueException.class, exception.getClass());
        }
    }

    @Test
    public void getInvalidDouble() {
        try {
            client.getInvalidDoubleWithRestResponse();
            fail();
        } catch (Exception exception) {
            Assert.assertEquals(MalformedValueException.class, exception.getClass());
        }
    }

    @Test
    public void putBigFloat() {
        final Response<Void> putResponse = client.putBigFloatWithRestResponse(3.402823e+20f);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void putBigDouble() {
        final Response<Void> putResponse = client.putBigDoubleWithRestResponse(2.5976931e+101);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getBigFloat() {
        final Response<Float> getResponse = client.getBigFloatWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertEquals(3.402823e+20f, getResponse.getValue(), 0.0f);
    }

    @Test
    public void getBigDouble() {
        final Response<Double> getResponse = client.getBigDoubleWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Assert.assertEquals(2.5976931e+101, getResponse.getValue(), 0.0f);
    }

    @Test
    public void putBigDoublePositiveDecimal() {
        final Response<Void> putResponse = client.putBigDoublePositiveDecimalWithRestResponse();
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getBigDoublePositiveDecimal() {
        final Response<Double> getResponse = client.getBigDoublePositiveDecimalWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Assert.assertEquals(99999999.99, getResponse.getValue(), 0.0f);
    }

    @Test
    public void putBigDoubleNegativeDecimal() {
        final Response<Void> putResponse = client.putBigDoubleNegativeDecimalWithRestResponse();
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getBigDoubleNegativeDecimal() {
        final Response<Double> getResponse = client.getBigDoubleNegativeDecimalWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Assert.assertEquals(-99999999.99, getResponse.getValue(), 0.0f);
    }

    @Test
    public void putSmallFloat() {
        final Response<Void> putResponse = client.putSmallFloatWithRestResponse(3.402823e-20f);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getSmallFloat() {
        final Response<Double> getResponse = client.getSmallFloatWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        assertEquals(3.402823e-20, getResponse.getValue(), 0.0f);
    }

    @Test
    public void putSmallDouble() {
        final Response<Void> putResponse = client.putSmallDoubleWithRestResponse(2.5976931e-101);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getSmallDouble() {
        final Response<Double> getResponse = client.getSmallDoubleWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Assert.assertEquals(2.5976931e-101, getResponse.getValue(), 0.0f);
    }

    @Test
    public void putBigDecimalPositiveDecimalTest() {
        final Response<Void> putResponse = client.putBigDecimalPositiveDecimalWithRestResponse();
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void putBigDecimalNegativeDecimalTest() {
        final Response<Void> putResponse = client.putBigDecimalNegativeDecimalWithRestResponse();
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getBigDecimalTest() {
        final Response<BigDecimal> getResponse = client.getBigDecimalWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Assert.assertEquals(BigDecimal.valueOf(2.5976931E+101), getResponse.getValue());
    }

    @Test
    public void getBigDecimalPositiveDecimalTest() {
        final Response<BigDecimal> getResponse = client.getBigDecimalPositiveDecimalWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Assert.assertEquals(BigDecimal.valueOf(99999999.99), getResponse.getValue());
    }

    @Test
    public void getBigDecimalNegativeDecimalTest() {
        final Response<BigDecimal> getResponse = client.getBigDecimalNegativeDecimalWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Assert.assertEquals(BigDecimal.valueOf(-99999999.99), getResponse.getValue());
    }

    @Test
    public void putBigDecimalTest() {
        BigDecimal request = new BigDecimal("2.5976931e+101");
        final Response<Void> putResponse = client.putBigDecimalWithRestResponse(request);
        assertEquals(200, putResponse.getStatusCode());
    }
}
