package com.lottodroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.widget.SimpleExpandableListAdapter;

/**
 * Activity for the details view.
 * 
 * It is currently constructed on top of a {@link ExpandableListActivity}, and populated with toy
 * data that is displayed as a simple {@code TextView} inside a list.
 */
public class DetailsActivity extends ExpandableListActivity {

  private final static String GROUP_KEY = "datesKey";
  private final static String CHILD_KEY = "colorName";

  private final static String GROUP_CELLS[] = {
    "05/03/2009",
    "06/03/2009",
    "07/03/2009",
    "08/03/2009" };
  private final static String CHILD_CELLS[] = {
    "Nº 1 2 3 4 5 6, C: 2, R: 3",
    "Nº 5 7 8 9 8 4, C: 4, R: 8",
    "Nº 6 7 3 0 1 0, C: 3, R: 1",
    "Nº 8 9 0 5 2 6, C: 8, R: 0" };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.details);

    SimpleExpandableListAdapter listAdapter = new SimpleExpandableListAdapter(this,
        createGroupList(), R.layout.group_row, new String[] { GROUP_KEY },
        new int[] { R.id.groupname }, createChildList(), R.layout.child_row,
        new String[] { CHILD_KEY }, new int[] { R.id.childname });
    setListAdapter(listAdapter);
  }

  /**
   * Construct the list of groups. They are represented as a map, where all the keys are
   * {@link #GROUP_KEY}. I don't really understand why it has to be like that.
   * 
   * @return the list of groups
   */
  private static List<Map<String, String>> createGroupList() {
    ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();

    for (String groupCell : GROUP_CELLS) {
      HashMap<String, String> group = new HashMap<String, String>();
      group.put(GROUP_KEY, groupCell);
      result.add(group);
    }
    return result;
  }

  /**
   * Constructs the list of rows every group.
   * 
   * The return type is pretty complex, but it is what {@link SimpleExpandableListAdapter} expects:
   * every group contains a list of maps. Every of those maps, which are {@code <String, List>}
   * correspond to a visible row once the group is expanded: every element in that list corresponds
   * to a row in the expanded view of the group cell. Yes, it is a mess.
   * 
   * @return the child list
   */
  private static List<List<Map<String, String>>> createChildList() {
    ArrayList<List<Map<String, String>>> result = new ArrayList<List<Map<String, String>>>();

    for (String childCell : CHILD_CELLS) {
      ArrayList<Map<String, String>> secList = new ArrayList<Map<String, String>>();
      HashMap<String, String> child = new HashMap<String, String>();
      child.put(CHILD_KEY, childCell);
      secList.add(child);
      result.add(secList);
    }

    return result;
  }
}
