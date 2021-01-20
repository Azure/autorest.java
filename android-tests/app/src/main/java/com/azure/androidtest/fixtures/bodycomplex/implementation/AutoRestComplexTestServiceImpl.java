package com.azure.androidtest.fixtures.bodycomplex.implementation;

import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.internal.util.serializer.SerializerAdapter;
import com.azure.android.core.internal.util.serializer.SerializerFormat;

/**
 * Initializes a new instance of the AutoRestComplexTestService type.
 */
public final class AutoRestComplexTestServiceImpl {
    /**
     * The serializer.
     */
    final SerializerAdapter serializerAdapter = SerializerAdapter.createDefault();

    /**
     * The Azure Core generic ServiceClient to setup interceptors and produce retrofit proxy.
     */
    private ServiceClient serviceClient;

    /**
     * Gets The Azure Core generic ServiceClient to setup interceptors and produce retrofit proxy.
     * 
     * @return the serviceClient value.
     */
    public ServiceClient getServiceClient() {
        return this.serviceClient;
    }

    /**
     * server parameter.
     */
    private final String host;

    /**
     * Gets server parameter.
     * 
     * @return the host value.
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Api Version.
     */
    private final String apiVersion;

    /**
     * Gets Api Version.
     * 
     * @return the apiVersion value.
     */
    public String getApiVersion() {
        return this.apiVersion;
    }

    /**
     * The BasicsImpl object to access its operations.
     */
    private final BasicsImpl basics;

    /**
     * Gets the BasicsImpl object to access its operations.
     * 
     * @return the BasicsImpl object.
     */
    public BasicsImpl getBasics() {
        return this.basics;
    }

    /**
     * The PrimitivesImpl object to access its operations.
     */
    private final PrimitivesImpl primitives;

    /**
     * Gets the PrimitivesImpl object to access its operations.
     * 
     * @return the PrimitivesImpl object.
     */
    public PrimitivesImpl getPrimitives() {
        return this.primitives;
    }

    /**
     * The ArraysImpl object to access its operations.
     */
    private final ArraysImpl arrays;

    /**
     * Gets the ArraysImpl object to access its operations.
     * 
     * @return the ArraysImpl object.
     */
    public ArraysImpl getArrays() {
        return this.arrays;
    }

    /**
     * The DictionarysImpl object to access its operations.
     */
    private final DictionarysImpl dictionarys;

    /**
     * Gets the DictionarysImpl object to access its operations.
     * 
     * @return the DictionarysImpl object.
     */
    public DictionarysImpl getDictionarys() {
        return this.dictionarys;
    }

    /**
     * The InheritancesImpl object to access its operations.
     */
    private final InheritancesImpl inheritances;

    /**
     * Gets the InheritancesImpl object to access its operations.
     * 
     * @return the InheritancesImpl object.
     */
    public InheritancesImpl getInheritances() {
        return this.inheritances;
    }

    /**
     * The PolymorphismsImpl object to access its operations.
     */
    private final PolymorphismsImpl polymorphisms;

    /**
     * Gets the PolymorphismsImpl object to access its operations.
     * 
     * @return the PolymorphismsImpl object.
     */
    public PolymorphismsImpl getPolymorphisms() {
        return this.polymorphisms;
    }

    /**
     * The PolymorphicrecursivesImpl object to access its operations.
     */
    private final PolymorphicrecursivesImpl polymorphicrecursives;

    /**
     * Gets the PolymorphicrecursivesImpl object to access its operations.
     * 
     * @return the PolymorphicrecursivesImpl object.
     */
    public PolymorphicrecursivesImpl getPolymorphicrecursives() {
        return this.polymorphicrecursives;
    }

    /**
     * The ReadonlypropertysImpl object to access its operations.
     */
    private final ReadonlypropertysImpl readonlypropertys;

    /**
     * Gets the ReadonlypropertysImpl object to access its operations.
     * 
     * @return the ReadonlypropertysImpl object.
     */
    public ReadonlypropertysImpl getReadonlypropertys() {
        return this.readonlypropertys;
    }

    /**
     * The FlattencomplexsImpl object to access its operations.
     */
    private final FlattencomplexsImpl flattencomplexs;

    /**
     * Gets the FlattencomplexsImpl object to access its operations.
     * 
     * @return the FlattencomplexsImpl object.
     */
    public FlattencomplexsImpl getFlattencomplexs() {
        return this.flattencomplexs;
    }

    /**
     * Initializes an instance of AutoRestComplexTestService client.
     * 
     * @param serviceClient The Azure Core generic ServiceClient to setup interceptors and produce retrofit proxy.
     * @param host server parameter.
     */
    public AutoRestComplexTestServiceImpl(ServiceClient serviceClient, String host) {
        this.serviceClient = serviceClient;
        this.host = host;
        this.apiVersion = "2016-02-29";
        this.basics = new BasicsImpl(this);
        this.primitives = new PrimitivesImpl(this);
        this.arrays = new ArraysImpl(this);
        this.dictionarys = new DictionarysImpl(this);
        this.inheritances = new InheritancesImpl(this);
        this.polymorphisms = new PolymorphismsImpl(this);
        this.polymorphicrecursives = new PolymorphicrecursivesImpl(this);
        this.readonlypropertys = new ReadonlypropertysImpl(this);
        this.flattencomplexs = new FlattencomplexsImpl(this);
    }

    String readAsString(okhttp3.ResponseBody body) {
        if (body == null) {
            return "";
        }
        try {
            return new String(body.bytes());
        } catch(java.io.IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            body.close();
        }
    }

    <T> T deserializeContent(okhttp3.Headers headers, okhttp3.ResponseBody body, java.lang.reflect.Type type) {
        if (type.equals(byte[].class)) {
            try {
                if (body.contentLength() == 0) {
                    return null;
                }
                return (T) body.bytes();
            } catch(java.io.IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        final String str = readAsString(body);
        try {
            final String mimeContentType = headers.get(CONTENT_TYPE);
            return this.serializerAdapter.deserialize(str, type, resolveSerializerFormat(mimeContentType));
        } catch(java.io.IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    <T> T deserializeHeaders(okhttp3.Headers headers, java.lang.reflect.Type type) {
        try {
            return this.serializerAdapter.deserialize(headers, type);
        } catch(java.io.IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    <T> retrofit2.Response<T> executeRetrofitCall(retrofit2.Call<T> call) {
        try {
            return call.execute();
        } catch(java.io.IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    java.lang.reflect.ParameterizedType createParameterizedType(Class<?> rawClass, java.lang.reflect.Type... genericTypes) {
        return new java.lang.reflect.ParameterizedType() {
            @Override
            public java.lang.reflect.Type[] getActualTypeArguments() {
                return genericTypes;
            }
            @Override
            public java.lang.reflect.Type getRawType() {
                return rawClass;
            }
            @Override
            public java.lang.reflect.Type getOwnerType() {
                return null;
            }
        };
    }

    private static final String CONTENT_TYPE = "Content-Type";

    private static final java.util.Map<String, SerializerFormat> SUPPORTED_MIME_TYPES = new java.util.TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    private static final java.util.TreeMap<String, SerializerFormat> SUPPORTED_SUFFIXES = new java.util.TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    private static final SerializerFormat DEFAULT_ENCODING = SerializerFormat.JSON;
    static {
        SUPPORTED_MIME_TYPES.put("text/xml", SerializerFormat.XML);
        SUPPORTED_MIME_TYPES.put("application/xml", SerializerFormat.XML);
        SUPPORTED_MIME_TYPES.put("application/json", SerializerFormat.JSON);
        SUPPORTED_SUFFIXES.put("xml", SerializerFormat.XML);
        SUPPORTED_SUFFIXES.put("json", SerializerFormat.JSON);
    }

    SerializerFormat resolveSerializerFormat(String mimeContentType) {
        if (mimeContentType == null || mimeContentType.isEmpty()) {
            return DEFAULT_ENCODING;
        }
        final String[] parts = mimeContentType.split(";");
        final SerializerFormat encoding = SUPPORTED_MIME_TYPES.get(parts[0]);
        if (encoding != null) {
            return encoding;
        }
        final String[] mimeTypeParts = parts[0].split("/");
        if (mimeTypeParts.length != 2) {
            return DEFAULT_ENCODING;
        }
        final String subtype = mimeTypeParts[1];
        final int lastIndex = subtype.lastIndexOf("+");
        if (lastIndex == -1) {
            return DEFAULT_ENCODING;
        }
        final String mimeTypeSuffix = subtype.substring(lastIndex + 1);
        final SerializerFormat serializerEncoding = SUPPORTED_SUFFIXES.get(mimeTypeSuffix);
        if (serializerEncoding != null) {
            return serializerEncoding;
        }
        return DEFAULT_ENCODING;
    }
}
