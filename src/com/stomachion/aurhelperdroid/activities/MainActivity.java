package com.stomachion.aurhelperdroid.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.stomachion.aurhelperdroid.R;
import com.stomachion.aurhelperdroid.fragments.AboutFrag;
import com.stomachion.aurhelperdroid.fragments.PreferencesFrag;
import com.stomachion.aurhelperdroid.fragments.SearchFrag;
import shared.ui.actionscontentview.ActionsContentView;

public class MainActivity extends FragmentActivity implements AdapterView.OnItemClickListener {

    private ActionsContentView mActionsContentView;

    private final int MENU_NEW = 0;
    private final int MENU_SEARCH = 1;
    private final int MENU_ABOUT = 2;
    private final int MENU_PREFERENCES = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mActionsContentView = (ActionsContentView) findViewById(R.id.actionsContentView);

        final ListView viewActionsList = (ListView) findViewById(R.id.actions);

        final String[] values = new String[]{getString(R.string.menu_new), getString(R.string.menu_search), getString(R.string.menu_about), getString(R.string.menu_prefs)};

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        viewActionsList.setAdapter(adapter);
        viewActionsList.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showFragment(position);
    }

    /**
     * Show a given fragment after selecting an item from the content menu
     *
     * @param position position of the item on the content menu
     */
    private void showFragment(int position) {
        final Fragment f;
        switch (position) {
            case MENU_NEW:
                f = new AboutFrag();
                break;

            case MENU_SEARCH:
                f = new SearchFrag();
                break;

            case MENU_ABOUT:
                f = new AboutFrag();
                break;

            case MENU_PREFERENCES:
                f = new PreferencesFrag();
                break;

            default:
                return;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content, f).commit();

        mActionsContentView.showContent();
    }
}
