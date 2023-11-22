import com._specs_.azure.core.basic.models.User;
import com._specs_.azure.core.basic.models.UserOrder;

public class JsonMergePatchHelper {
    private static UserAccessor setUserAccessor;

    private static UserOrderAccessor setUserOrderAccessor;

    public interface UserAccessor {
        User prepareModelForJsonMergePatch(User setUser, boolean jsonMergePatchEnabled);
    }

    public interface UserOrderAccessor {
        UserOrder prepareModelForJsonMergePatch(UserOrder setUserOrder, boolean jsonMergePatchEnabled);
    }

    public static void setUserAccessor(UserAccessor accessor) {
        setUserAccessor = accessor;
    }

    public static UserAccessor getUserAccessor() {
        return setUserAccessor;
    }

    public static void setUserOrderAccessor(UserOrderAccessor accessor) {
        setUserOrderAccessor = accessor;
    }

    public static UserOrderAccessor getUserOrderAccessor() {
        return setUserOrderAccessor;
    }
}
