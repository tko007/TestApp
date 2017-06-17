package at.tko.collector.page;

import at.tko.collector.BaseClass;
import at.tko.collector.enums.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BetAtHomeNaviBar extends BaseClass{


    public BetAtHomeNaviBar(WebDriver driver){
        super(driver);
    }

    public void openBets(){
        String menuSelector = "a.ftl-item-link";
        WebElement menu =
                waitUntilElementClickable(menuSelector);

        if( menu == null ){ return; }

        menu.click();
    }

    public void openSubMenu(Category category, int selectedSubItemId){
        String subMenuSelector =
                "li[data-region='" + category.getDataId() + "']";
        String subMenuItemSelector =
                "a[data-eventgroup='" + selectedSubItemId + "']";
        WebElement subMenu =
                waitUntilElementClickable(subMenuSelector);

        if( subMenu == null ){ return; }

        subMenu.click();
        WebElement subMenuItem =
                waitUntilElementClickable(subMenuItemSelector);

        if (subMenuItem == null){ return; }

        subMenuItem.click();
    }
}
