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
    public void localeTest() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String expected1 = "Добро пожаловать";
        String expected2 = "Welcome";
        String actual1 = localizationService.locale(RUSSIA);
        String actual2 = localizationService.locale(USA);
        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
    }

    @Test
    public void LocationByIpTest() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location expected1 = new Location(MOSCOW_IP, RUSSIA, "Lenina", 15);
        Location expected2 = new Location(NEW_YORK_IP, USA, " 10th Avenue", 32);
        Location actual1 = geoService.byIp(MOSCOW_IP);
        Location actual2 = geoService.byIp(NEW_YORK_IP);
        Location actual3 = geoService.byIp("176.");
        Location actual4 = geoService.byIp("96.");
        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
        Assert.assertEquals(expected1, actual3);
        Assert.assertEquals(expected2, actual4);
    }

    @Test
    public void sendRusTest() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class); // заглушка на объект
        Mockito.when(geoService.byIp(MOSCOW_IP)) // установка условия
                .thenReturn(new Location("Moscow", RUSSIA, "Lenina", 15)); // установка значения

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class); // заглушка на объект
        Mockito.when(localizationService.locale(RUSSIA)) // установка условия
                .thenReturn("Добро пожаловать"); // установка значения

        Map<String, String> list = new HashMap<>(); // создание мапы
        list.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11"); //

        MessageSenderImpl sendTest = new MessageSenderImpl(geoService, localizationService); // создание объета
        String expected = "Добро пожаловать"; // ожидаемое значение
        String actual = sendTest.send(list); // фактическое значение
        Assert.assertEquals(expected, actual); // сравнение
    }

    @Test
    public void sendUsaTest(){
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(NEW_YORK_IP))
                .thenReturn(new Location("New York",USA," 10th Avenue",32));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(USA))
                .thenReturn("Welcome");

        Map<String, String> list1 = new HashMap<>();
        list1.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");

        MessageSenderImpl sendTest = new MessageSenderImpl(geoService, localizationService);
        String expected = "Welcome";
        String actual = sendTest.send(list1);
        Assert.assertEquals(expected, actual);
    }
}

