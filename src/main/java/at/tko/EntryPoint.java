package at.tko;

import at.tko.collector.SeleniumStarter;
import at.tko.collector.enums.Category;
import at.tko.collector.page.BetAtHomeNaviBar;
import at.tko.collector.page.HandiCap;
import at.tko.collector.util.ExcelCreator;

import java.net.MalformedURLException;
import java.util.Locale;

public class EntryPoint {

    public static void main(String[] args) throws InterruptedException, MalformedURLException {
        SeleniumStarter starter = new SeleniumStarter(Locale.GERMANY);

        try {
            BetAtHomeNaviBar navibar =
                    new BetAtHomeNaviBar(starter.getDriver());

            navibar.openBets();
            navibar.openSubMenu(
                    Category.DEUTSCHLAND, Category.DEUTSCHLAND.getSubMenus().get(0));

            HandiCap handiCap = new HandiCap(starter.getDriver());
            handiCap.openHandiCapTable();


        } catch (Exception e){
            e.printStackTrace();

        } finally {
            starter.getDriver().close();
        }
    }
}
