import com.cadl.specialheaders.models.Resource;

public class JsonMergePatchHelper {
    private static ResourceAccessor setResourceAccessor;

    public interface ResourceAccessor {
        Resource prepareModelForJsonMergePatch(Resource setResource, boolean jsonMergePatchEnabled);
    }

    public static void setResourceAccessor(ResourceAccessor accessor) {
        setResourceAccessor = accessor;
    }

    public static ResourceAccessor getResourceAccessor() {
        return setResourceAccessor;
    }
}
