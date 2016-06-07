package com.wareshopc.app.truechalk.sportselector.basketball;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.wareshopc.app.truechalk.R;
import com.wareshopc.app.truechalk.sportselector.basketball.db.BasketballDatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class BasketballChalkListFragment extends ListFragment {
    private List<BasketballChalk> mBasketballChalks;
    private Callbacks mCallbacks;
    private BasketballDatabaseHandler mDb;

    /**
     * Required interface for hosting activities.
     */
    public interface Callbacks {
        void onBasketballChalkSelected(BasketballChalk basketballChalk);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.basketball_chalk_collection_title);

        //mBasketballChalks = BasketballChalkLab.get(getActivity()).getBasketballChalks();  NOTE:  Removing File saving
        mDb = new BasketballDatabaseHandler(getActivity());
        mBasketballChalks = mDb.getAllBasketballChalks();
        ChalkAdapter adapter = new ChalkAdapter(mBasketballChalks);
        setListAdapter(adapter);

        setRetainInstance(true);
    }

    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.basketballchalk_list_fragment, parent, false);

        ListView listView = (ListView) v.findViewById(android.R.id.list);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            // Use floating context menus on Froyo and Gingerbread
            registerForContextMenu(listView);
        } else {
            // Use contextual action bar on Honeycomb and higher
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
            listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

                public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                      long id, boolean checked) {
                    // Required, but not used in this implementation
                }

                // ActionMode.Callback methods
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    MenuInflater inflater = mode.getMenuInflater();
                    inflater.inflate(R.menu.truechalk_list_item_context, menu);
                    return true;
                }

                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                    // Required, but not used in this implementation
                }

                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_item_delete_chalk:
                            ChalkAdapter adapter = (ChalkAdapter) getListAdapter();

                            //BasketballChalkLab trueChalkLab = BasketballChalkLab.get(getActivity());   NOTE: Removing file saving.
                            for (int i = adapter.getCount() - 1; i >= 0; i--) {
                                if (getListView().isItemChecked(i)) {
                                    //trueChalkLab.deleteBasketballChalk(adapter.getItem(i));    NOTE: Removing file saving.
                                    mDb.deleteBasketballChalk(adapter.getItem(i));
                                }
                            }

                            mode.finish();
                            adapter.notifyDataSetChanged();
                            return true;
                        default:
                            return false;
                    }
                }

                public void onDestroyActionMode(ActionMode mode) {
                    // Required, but not used in this implementation
                }
            });
        }

        Button addAChalkButton = (Button) v.findViewById(android.R.id.empty);
        addAChalkButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BasketballChalk basketballChalk = new BasketballChalk();
                //BasketballChalkLab.get(getActivity()).addBasketballChalk(basketballChalk);    //NOTE: Removing file saving.
                mDb.insertBasketballChalk(basketballChalk);

                Intent i = new Intent(getActivity(), BasketballChalkPagerActivity.class);
                i.putExtra(BasketballChalkFragment.EXTRA_TRUECHALK_ID, basketballChalk.getId());
                startActivityForResult(i, 0);
            }
        });

        return v;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        BasketballChalk chalk = ((ChalkAdapter)getListAdapter()).getItem(position);
        Intent i = new Intent(getActivity(), BasketballReportActivity.class);
        i.putExtra(BasketballChalkFragment.EXTRA_TRUECHALK_ID, chalk.getId());
        startActivity(i);

        //BasketballChalk c = ((ChalkAdapter) getListAdapter()).getItem(position);
        //mCallbacks.onBasketballChalkSelected(c);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((ChalkAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ChalkAdapter) getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_basketballchalk_list_menu, menu);
    }

    @TargetApi(11)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_chalk:
                BasketballChalk basketballChalk = new BasketballChalk();
                //BasketballChalkLab.get(getActivity()).addBasketballChalk(basketballChalk);
                mDb.insertBasketballChalk(basketballChalk);


                ((ChalkAdapter) getListAdapter()).notifyDataSetChanged();
                mCallbacks.onBasketballChalkSelected(basketballChalk);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.truechalk_list_item_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        ChalkAdapter adapter = (ChalkAdapter) getListAdapter();
        BasketballChalk basketballChalk = adapter.getItem(position);

        switch (item.getItemId()) {
            case R.id.menu_item_delete_chalk:
                //BasketballChalkLab.get(getActivity()).deleteBasketballChalk(basketballChalk);
                mDb.deleteBasketballChalk(basketballChalk);
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private class ChalkAdapter extends ArrayAdapter<BasketballChalk> {
        public ChalkAdapter(List<BasketballChalk> basketballChalks) {
            super(getActivity(), android.R.layout.simple_list_item_1, basketballChalks);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // If we weren't given a view, inflate one
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_chalk, null);
            }
            // Configure the view for this BasketballChalk
            BasketballChalk c = getItem(position);
            TextView titleTextView = (TextView) convertView
                    .findViewById(R.id.truechalk_list_item_titleTextView);
            titleTextView.setText(c.getEventName());
            TextView dateTextView = (TextView) convertView
                    .findViewById(R.id.truechalk_list_item_dateTextView);
            dateTextView.setText(c.getDate().toString());

            return convertView;
        }
    }

    public void updateUI() {
        ((ChalkAdapter)getListAdapter()).notifyDataSetChanged();
    }

}
