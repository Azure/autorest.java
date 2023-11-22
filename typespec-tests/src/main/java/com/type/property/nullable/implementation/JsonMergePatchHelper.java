import com.type.property.nullable.models.BytesProperty;
import com.type.property.nullable.models.CollectionsByteProperty;
import com.type.property.nullable.models.CollectionsModelProperty;
import com.type.property.nullable.models.DatetimeProperty;
import com.type.property.nullable.models.DurationProperty;
import com.type.property.nullable.models.InnerModel;
import com.type.property.nullable.models.StringProperty;

public class JsonMergePatchHelper {
    private static StringPropertyAccessor setStringPropertyAccessor;

    private static BytesPropertyAccessor setBytesPropertyAccessor;

    private static DatetimePropertyAccessor setDatetimePropertyAccessor;

    private static DurationPropertyAccessor setDurationPropertyAccessor;

    private static CollectionsBytePropertyAccessor setCollectionsBytePropertyAccessor;

    private static CollectionsModelPropertyAccessor setCollectionsModelPropertyAccessor;

    private static InnerModelAccessor setInnerModelAccessor;

    public interface StringPropertyAccessor {
        StringProperty prepareModelForJsonMergePatch(StringProperty setStringProperty, boolean jsonMergePatchEnabled);
    }

    public interface BytesPropertyAccessor {
        BytesProperty prepareModelForJsonMergePatch(BytesProperty setBytesProperty, boolean jsonMergePatchEnabled);
    }

    public interface DatetimePropertyAccessor {
        DatetimeProperty prepareModelForJsonMergePatch(DatetimeProperty setDatetimeProperty,
            boolean jsonMergePatchEnabled);
    }

    public interface DurationPropertyAccessor {
        DurationProperty prepareModelForJsonMergePatch(DurationProperty setDurationProperty,
            boolean jsonMergePatchEnabled);
    }

    public interface CollectionsBytePropertyAccessor {
        CollectionsByteProperty prepareModelForJsonMergePatch(CollectionsByteProperty setCollectionsByteProperty,
            boolean jsonMergePatchEnabled);
    }

    public interface CollectionsModelPropertyAccessor {
        CollectionsModelProperty prepareModelForJsonMergePatch(CollectionsModelProperty setCollectionsModelProperty,
            boolean jsonMergePatchEnabled);
    }

    public interface InnerModelAccessor {
        InnerModel prepareModelForJsonMergePatch(InnerModel setInnerModel, boolean jsonMergePatchEnabled);
    }

    public static void setStringPropertyAccessor(StringPropertyAccessor accessor) {
        setStringPropertyAccessor = accessor;
    }

    public static StringPropertyAccessor getStringPropertyAccessor() {
        return setStringPropertyAccessor;
    }

    public static void setBytesPropertyAccessor(BytesPropertyAccessor accessor) {
        setBytesPropertyAccessor = accessor;
    }

    public static BytesPropertyAccessor getBytesPropertyAccessor() {
        return setBytesPropertyAccessor;
    }

    public static void setDatetimePropertyAccessor(DatetimePropertyAccessor accessor) {
        setDatetimePropertyAccessor = accessor;
    }

    public static DatetimePropertyAccessor getDatetimePropertyAccessor() {
        return setDatetimePropertyAccessor;
    }

    public static void setDurationPropertyAccessor(DurationPropertyAccessor accessor) {
        setDurationPropertyAccessor = accessor;
    }

    public static DurationPropertyAccessor getDurationPropertyAccessor() {
        return setDurationPropertyAccessor;
    }

    public static void setCollectionsBytePropertyAccessor(CollectionsBytePropertyAccessor accessor) {
        setCollectionsBytePropertyAccessor = accessor;
    }

    public static CollectionsBytePropertyAccessor getCollectionsBytePropertyAccessor() {
        return setCollectionsBytePropertyAccessor;
    }

    public static void setCollectionsModelPropertyAccessor(CollectionsModelPropertyAccessor accessor) {
        setCollectionsModelPropertyAccessor = accessor;
    }

    public static CollectionsModelPropertyAccessor getCollectionsModelPropertyAccessor() {
        return setCollectionsModelPropertyAccessor;
    }

    public static void setInnerModelAccessor(InnerModelAccessor accessor) {
        setInnerModelAccessor = accessor;
    }

    public static InnerModelAccessor getInnerModelAccessor() {
        return setInnerModelAccessor;
    }
}
