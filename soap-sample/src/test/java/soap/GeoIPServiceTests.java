package soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created on 22/11/2017.
 * по видео 9.2 курса java для тестировщиков
 */
public class GeoIPServiceTests {

    @Test
    public void testMyIP() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("154.54.221.193");
        Assert.assertEquals(geoIP.getCountryCode(), "USA");

    }
}
