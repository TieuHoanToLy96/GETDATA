package model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by TieuHoan on 22/05/2017.
 */

public class Genre extends ExpandableGroup {


    public Genre(String title, List items) {
        super(title, items);
    }
}
