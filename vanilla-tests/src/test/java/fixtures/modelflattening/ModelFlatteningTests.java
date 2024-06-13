package fixtures.modelflattening;

import fixtures.modelflattening.models.FlattenParameterGroup;
import fixtures.modelflattening.models.FlattenedProduct;
import fixtures.modelflattening.models.FlattenedProductPropertiesProvisioningStateValues;
import fixtures.modelflattening.models.Resource;
import fixtures.modelflattening.models.ResourceCollection;
import fixtures.modelflattening.models.SimpleProduct;
import fixtures.modelflattening.models.SimpleProductPropertiesMaxProductCapacity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelFlatteningTests {
    private static AutoRestResourceFlatteningTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestResourceFlatteningTestServiceBuilder().buildClient();
    }

    @Test
    public void getArray() {
        List<FlattenedProduct> result = client.getArray();
        assertEquals(3, result.size());
        // Resource 1
        assertEquals("1", result.get(0).getId());
        assertEquals(FlattenedProductPropertiesProvisioningStateValues.OK, result.get(0).getProvisioningStateValues());
        assertEquals("Product1", result.get(0).getPName());
        assertEquals("Flat", result.get(0).getTypePropertiesType());
        assertEquals("Building 44", result.get(0).getLocation());
        assertEquals("Resource1", result.get(0).getName());
        assertEquals("Succeeded", result.get(0).getProvisioningState());
        assertEquals("Microsoft.Web/sites", result.get(0).getType());
        assertEquals("value1", result.get(0).getTags().get("tag1"));
        assertEquals("value3", result.get(0).getTags().get("tag2"));
        // Resource 2
        assertEquals("2", result.get(1).getId());
        assertEquals("Resource2", result.get(1).getName());
        assertEquals("Building 44", result.get(1).getLocation());
        // Resource 3
        assertEquals("3", result.get(2).getId());
        assertEquals("Resource3", result.get(2).getName());
    }

    @Test
    public void putArray() {
        List<Resource> body = new ArrayList<>();
        FlattenedProduct product = new FlattenedProduct();
        product.setLocation("West US");
        product.setTags(new HashMap<>());
        product.getTags().put("tag1", "value1");
        product.getTags().put("tag2", "value3");
        body.add(product);
        FlattenedProduct product1 = new FlattenedProduct();
        product1.setLocation("Building 44");
        body.add(product1);
        client.putArray(body);
    }

    @Test
    public void getDictionary() {
        Map<String, FlattenedProduct> result = client.getDictionary();
        assertEquals(3, result.size());
        // Resource 1
        assertEquals("1", result.get("Product1").getId());
        assertEquals(FlattenedProductPropertiesProvisioningStateValues.OK,
            result.get("Product1").getProvisioningStateValues());
        assertEquals("Product1", result.get("Product1").getPName());
        assertEquals("Flat", result.get("Product1").getTypePropertiesType());
        assertEquals("Building 44", result.get("Product1").getLocation());
        assertEquals("Resource1", result.get("Product1").getName());
        assertEquals("Succeeded", result.get("Product1").getProvisioningState());
        assertEquals("Microsoft.Web/sites", result.get("Product1").getType());
        assertEquals("value1", result.get("Product1").getTags().get("tag1"));
        assertEquals("value3", result.get("Product1").getTags().get("tag2"));
        // Resource 2
        assertEquals("2", result.get("Product2").getId());
        assertEquals("Resource2", result.get("Product2").getName());
        assertEquals("Building 44", result.get("Product2").getLocation());
        // Resource 3
        assertEquals("3", result.get("Product3").getId());
        assertEquals("Resource3", result.get("Product3").getName());
    }

    @Test
    public void putDictionary() {
        Map<String, FlattenedProduct> body = new HashMap<>();
        FlattenedProduct product = new FlattenedProduct();
        product.setLocation("West US");
        product.setTags(new HashMap<>());
        product.getTags().put("tag1", "value1");
        product.getTags().put("tag2", "value3");
        product.setPName("Product1");
        product.setTypePropertiesType("Flat");
        body.put("Resource1", product);
        FlattenedProduct product1 = new FlattenedProduct();
        product1.setLocation("Building 44");
        product1.setPName("Product2");
        product1.setTypePropertiesType("Flat");
        body.put("Resource2", product1);
        client.putDictionary(body);
    }

    @Test
    public void getResourceCollection() {
        ResourceCollection resultResource = client.getResourceCollection();
        //Dictionaryofresources
        assertEquals(3, resultResource.getDictionaryofresources().size());
        // Resource 1
        assertEquals("1", resultResource.getDictionaryofresources().get("Product1").getId());
        assertEquals(FlattenedProductPropertiesProvisioningStateValues.OK,
            resultResource.getDictionaryofresources().get("Product1").getProvisioningStateValues());
        assertEquals("Product1", resultResource.getDictionaryofresources().get("Product1").getPName());
        assertEquals("Flat", resultResource.getDictionaryofresources().get("Product1").getTypePropertiesType());
        assertEquals("Building 44", resultResource.getDictionaryofresources().get("Product1").getLocation());
        assertEquals("Resource1", resultResource.getDictionaryofresources().get("Product1").getName());
        assertEquals("Succeeded", resultResource.getDictionaryofresources().get("Product1").getProvisioningState());
        assertEquals("Microsoft.Web/sites", resultResource.getDictionaryofresources().get("Product1").getType());
        assertEquals("value1", resultResource.getDictionaryofresources().get("Product1").getTags().get("tag1"));
        assertEquals("value3", resultResource.getDictionaryofresources().get("Product1").getTags().get("tag2"));
        // Resource 2
        assertEquals("2", resultResource.getDictionaryofresources().get("Product2").getId());
        assertEquals("Resource2", resultResource.getDictionaryofresources().get("Product2").getName());
        assertEquals("Building 44", resultResource.getDictionaryofresources().get("Product2").getLocation());
        // Resource 3
        assertEquals("3", resultResource.getDictionaryofresources().get("Product3").getId());
        assertEquals("Resource3", resultResource.getDictionaryofresources().get("Product3").getName());

        //Arrayofresources
        assertEquals(3, resultResource.getArrayofresources().size());
        // Resource 1
        assertEquals("4", resultResource.getArrayofresources().get(0).getId());
        assertEquals(FlattenedProductPropertiesProvisioningStateValues.OK,
            resultResource.getArrayofresources().get(0).getProvisioningStateValues());
        assertEquals("Product4", resultResource.getArrayofresources().get(0).getPName());
        assertEquals("Flat", resultResource.getArrayofresources().get(0).getTypePropertiesType());
        assertEquals("Building 44", resultResource.getArrayofresources().get(0).getLocation());
        assertEquals("Resource4", resultResource.getArrayofresources().get(0).getName());
        assertEquals("Succeeded", resultResource.getArrayofresources().get(0).getProvisioningState());
        assertEquals("Microsoft.Web/sites", resultResource.getArrayofresources().get(0).getType());
        assertEquals("value1", resultResource.getArrayofresources().get(0).getTags().get("tag1"));
        assertEquals("value3", resultResource.getArrayofresources().get(0).getTags().get("tag2"));
        // Resource 2
        assertEquals("5", resultResource.getArrayofresources().get(1).getId());
        assertEquals("Resource5", resultResource.getArrayofresources().get(1).getName());
        assertEquals("Building 44", resultResource.getArrayofresources().get(1).getLocation());
        // Resource 3
        assertEquals("6", resultResource.getArrayofresources().get(2).getId());
        assertEquals("Resource6", resultResource.getArrayofresources().get(2).getName());

        //productresource
        assertEquals("7", resultResource.getProductresource().getId());
        assertEquals("Resource7", resultResource.getProductresource().getName());
    }

    @Test
    public void putResourceCollection() {
        Map<String, FlattenedProduct> resources = new HashMap<>();
        resources.put("Resource1", new FlattenedProduct());
        resources.get("Resource1").setLocation("West US");
        resources.get("Resource1").setPName("Product1");
        resources.get("Resource1").setTypePropertiesType("Flat");
        resources.get("Resource1").setTags(new HashMap<>());
        resources.get("Resource1").getTags().put("tag1", "value1");
        resources.get("Resource1").getTags().put("tag2", "value3");

        resources.put("Resource2", new FlattenedProduct());
        resources.get("Resource2").setLocation("Building 44");
        resources.get("Resource2").setPName("Product2");
        resources.get("Resource2").setTypePropertiesType("Flat");

        ResourceCollection complexObj = new ResourceCollection();
        complexObj.setDictionaryofresources(resources);
        complexObj.setArrayofresources(new ArrayList<>());
        complexObj.getArrayofresources().add(resources.get("Resource1"));
        FlattenedProduct p1 = new FlattenedProduct();
        p1.setLocation("East US");
        p1.setPName("Product2");
        p1.setTypePropertiesType("Flat");
        complexObj.getArrayofresources().add(p1);
        FlattenedProduct pr = new FlattenedProduct();
        pr.setLocation("India");
        pr.setPName("Azure");
        pr.setTypePropertiesType("Flat");
        complexObj.setProductresource(pr);

        client.putResourceCollection(complexObj);
    }

    @Test
    public void putSimpleProduct() {
        SimpleProduct simpleProduct = new SimpleProduct();
        simpleProduct.setDescription("product description");
        simpleProduct.setProductId("123");
        simpleProduct.setMaxProductDisplayName("max name");
        simpleProduct.setCapacity(SimpleProductPropertiesMaxProductCapacity.LARGE);
        simpleProduct.setOdataValue("http://foo");
        simpleProduct.setGenericValue("https://generic");

        SimpleProduct product = client.putSimpleProduct(simpleProduct);
        assertSimpleProductEquals(simpleProduct, product);
    }

    @Test
    public void postFlattenedSimpleProduct() {
        SimpleProduct simpleProduct = new SimpleProduct();
        simpleProduct.setDescription("product description");
        simpleProduct.setProductId("123");
        simpleProduct.setMaxProductDisplayName("max name");
        simpleProduct.setCapacity(SimpleProductPropertiesMaxProductCapacity.LARGE);
        simpleProduct.setOdataValue("http://foo");
        client.postFlattenedSimpleProduct("123", "product description", "max name",
            SimpleProductPropertiesMaxProductCapacity.LARGE, null, "http://foo");
    }

    @Test
    public void putSimpleProductWithGrouping() {
        SimpleProduct simpleProduct = new SimpleProduct();
        simpleProduct.setDescription("product description");
        simpleProduct.setProductId("123");
        simpleProduct.setMaxProductDisplayName("max name");
        simpleProduct.setCapacity(SimpleProductPropertiesMaxProductCapacity.LARGE);
        simpleProduct.setOdataValue("http://foo");

        FlattenParameterGroup flattenParameterGroup = new FlattenParameterGroup();
        flattenParameterGroup.setDescription("product description");
        flattenParameterGroup.setProductId("123");
        flattenParameterGroup.setMaxProductDisplayName("max name");
        flattenParameterGroup.setOdataValue("http://foo");
        flattenParameterGroup.setName("groupproduct");
        flattenParameterGroup.setCapacity(SimpleProductPropertiesMaxProductCapacity.LARGE);

        SimpleProduct product = client.putSimpleProductWithGrouping(flattenParameterGroup);
        assertSimpleProductEquals(simpleProduct, product);
    }

    private void assertSimpleProductEquals(SimpleProduct expected, SimpleProduct actual) {
        assertEquals(expected.getProductId(), actual.getProductId());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getCapacity(), actual.getCapacity());
        assertEquals(expected.getMaxProductDisplayName(), actual.getMaxProductDisplayName());
        assertEquals(expected.getOdataValue(), actual.getOdataValue());
    }
}
