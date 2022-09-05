import org.junit.Assert;
import org.junit.Test;
import ru.netology.i18n.LocalizationServiceImpl;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

public class TestMockLocalizationServiceImpl {

    @Test
    public void localeTest() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String expected = "Добро пожаловать";
        String actual = localizationService.locale(RUSSIA);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void localeTest2() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String expected = "Welcome";
        String actual = localizationService.locale(USA);
        Assert.assertEquals(expected, actual);
    }
}
