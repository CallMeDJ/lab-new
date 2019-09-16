package homework.seven.session5;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Seven-Steven
 * @date 2019-09-05 23:59
 */
public class BananaHashMapTest {
  @Test
  public void testAll() {
    // Test put
    BananaHashMap<String, String> map = new BananaHashMap<>(3);
    map.put("test", "test");
    map.put("tes", "test");
    map.put("test", "12");
    map.put("test", "1");
    map.put("1", "2");
    map.put("1", "3");
    map.put("test", "test");
    map.put(null, null);
    map.put("false", "false");
    // test get
    Assert.assertEquals( "test", map.get("test"));
    Assert.assertEquals( "test", map.get("tes"));
    Assert.assertEquals( "3", map.get("1"));
    Assert.assertNull(map.get(null));
    Assert.assertEquals( "false", map.get("false"));
    Assert.assertNull(map.get("notExist"));
    // test size
    Assert.assertEquals( 5, map.size());
    // test containsKey
    Assert.assertTrue(map.containsKey("false"));
    Assert.assertTrue(map.containsKey(null));
    Assert.assertTrue(map.containsKey("test"));
    Assert.assertTrue(map.containsKey("tes"));
    Assert.assertTrue(map.containsKey("1"));
    Assert.assertFalse(map.containsKey("notExist"));
    // test remove
    map.remove("test");
    Assert.assertEquals( 4, map.size());
    Assert.assertNull(map.get("test"));
    Assert.assertFalse(map.containsKey("test"));
  }
}
