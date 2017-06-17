package at.tko.collector.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tomci on 2017.01.13..
 */
public enum Category {
    CHAMPION_LEAGUE(219, Arrays.asList(0)),
    EUROPA_LEAGUE(220, Arrays.asList(0)),
    DEUTSCHLAND(243, Arrays.asList(9086)),
    ENGLAND(247, Arrays.asList(0)),
    ITALY(264, Arrays.asList(0)),
    SPAIN(304, Arrays.asList(0)),
    FRANCE(251, Arrays.asList(0));

    private int dataId;
    private List<Integer> subMenus;

    Category(int dataId, List<Integer> subMenus){
        this.dataId = dataId;
        this.subMenus = subMenus;
    }

    public int getDataId(){
        return dataId;
    }

    public List<Integer> getSubMenus(){
        return subMenus;
    }
}
