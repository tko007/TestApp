package at.tko.collector.page;

import at.tko.collector.BaseClass;
import at.tko.collector.classes.Bet;
import at.tko.collector.util.ConfigReader;
import org.openqa.selenium.*;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Predicate;

public class HandiCap extends BaseClass{

    private static final String HANDICAP = "Handicap";
    private static final String ROWS_CLASS = ".numberOfSubItems";
    private static final String TABLE_ROWS_SELECTOR = "table.ods tbody tr";
    private static final float LIMIT_BET =
            ConfigReader.getInstance().getConfigFile().getLimit();

    private int handiCapElementNum = 0;

    public HandiCap(WebDriver driver){
        super(driver);
    }

    public void openHandiCapTable() throws InterruptedException {
        WebElement el =
                exactTestMatching(HANDICAP, "l-grid-mainItem-content");

        if(el == null){ return; }

        hoverOverAnElement(el);

        el = executeJs(".l-grid-subItem-content[title=\"Handicap\"]");
        String numberOfListItem =
                el.findElement(By.cssSelector(ROWS_CLASS))
                        .getText()
                        .replaceAll("\\(", "")
                        .replaceAll("\\)", "");

        handiCapElementNum =
                Integer.parseInt(numberOfListItem);
    }

    public List<Bet> collectBets(){
        Predicate<WebDriver> waitTableRowsLoaded =
                (el) -> executeJsForList(TABLE_ROWS_SELECTOR).size() == handiCapElementNum;

        wait.until(waitTableRowsLoaded);
        return executeJsForList(TABLE_ROWS_SELECTOR).stream()
                .map(element -> new Bet(element.getText()))
                .filter(bet -> LIMIT_BET > bet.getTeam1Win() ||
                        LIMIT_BET > bet.getX() ||
                        LIMIT_BET > bet.getTeam2Win())
                .collect(Collectors.toList());
    }
}
