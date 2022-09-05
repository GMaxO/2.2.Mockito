import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;
import static ru.netology.geo.GeoServiceImpl.*;

public class TestMock {

    @Test
    public void LocationByIpTest() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location expected = new Location(MOSCOW_IP, RUSSIA, "Lenina", 15);
        Location actual = geoService.byIp(MOSCOW_IP);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void LocationByIpTest2() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location expected = new Location(NEW_YORK_IP, USA, " 10th Avenue", 32);
        Location actual = geoService.byIp(NEW_YORK_IP);
        Assert.assertEquals(expected, actual);

    }
    @Test
    public void LocationByIpTest3() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location expected = new Location(MOSCOW_IP, RUSSIA, "Lenina", 15);
        Location actual = geoService.byIp("176.");
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void LocationByIpTest4() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location expected = new Location(NEW_YORK_IP, USA, " 10th Avenue", 32);
        Location actual = geoService.byIp("96.");
        Assert.assertEquals(expected, actual);
    }
}

