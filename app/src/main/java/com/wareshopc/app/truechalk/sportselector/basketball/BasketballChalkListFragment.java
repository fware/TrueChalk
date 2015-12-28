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
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.wareshopc.app.truechalk.R;
import com.wareshopc.app.truechalk.TrueChalkLab;
import com.wareshopc.app.truechalk.sportselector.EventChalk;
import com.wareshopc.app.truechalk.sportselector.EventChalkFragment;

import java.util.ArrayList;

public class BasketballChalkListFragment extends ListFragment {
    private ArrayList<EventChalk> mEventChalks;
    private boolean mSubtitleVisible;
    private Callbacks mCallbacks;

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
        mEventChalks = TrueChalkLab.get(getActivity()).getTrueChalks();

        ChalkAdapter adapter = new ChalkAdapter(mEventChalks);
        setListAdapter(adapter);

        setRetainInstance(true);
        mSubtitleVisible = false;
    }

    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.basketballchalk_list_fragment, parent, false);
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        //    if (mSubtitleVisible) {
        //        ((ActionBarActivity) getActivity()).getSupportActionBar().setSubtitle(R.string.subtitle);
        //    }
        //}

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
                            TrueChalkLab trueChalkLab = TrueChalkLab.get(getActivity());
                            for (int i = adapter.getCount() - 1; i >= 0; i--) {
                                if (getListView().isItemChecked(i)) {
                                    trueChalkLab.deleteChalk(adapter.getItem(i));
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
        return v;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        EventChalk c = ((ChalkAdapter) getListAdapter()).getItem(position);
        mCallbacks.onChalkSelected(c);
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

        //MenuItem showSubtitle = menu.findItem(R.id.menu_item_show_subtitle);
        //if (mSubtitleVisible && showSubtitle != null) {
        //    showSubtitle.setTitle(R.string.hide_subtitle);
        //}

        Button addAChalkButton = (Button) getActivity().findViewById(android.R.id.empty);
        addAChalkButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EventChalk eventChalk = new EventChalk();
                TrueChalkLab.get(getActivity()).addChalk(eventChalk);
                Intent i = new Intent(getActivity(), BasketballChalkPagerActivity.class);
                i.putExtra(EventChalkFragment.EXTRA_TRUECHALK_ID, eventChalk.getId());
                startActivityForResult(i, 0);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_chalk:
                EventChalk eventChalk = new EventChalk();
                TrueChalkLab.get(getActivity()).addChalk(eventChalk);
                /*Intent i = new Intent(getActivity(), BasketballChalkPagerActivity.class);
                i.putExtra(EventChalkFragment.EXTRA_TRUECHALK_ID, eventChalk.getId());
                startActivityForResult(i, 0);*/
                ((ChalkAdapter) getListAdapter()).notifyDataSetChanged();
                mCallbacks.onChalkSelected(eventChalk);
                return true;
            /*case R.id.menu_item_show_subtitle:
                if (((ActionBarActivity) getActivity()).getSupportActionBar().getSubtitle() == null) {
                    ((ActionBarActivity) getActivity()).getSupportActionBar().setSubtitle(R.string.subtitle);
                    mSubtitleVisible = true;
                    item.setTitle(R.string.hide_subtitle);
                } else {
                    ((ActionBarActivity) getActivity()).getSupportActionBar().setSubtitle(null);
                    mSubtitleVisible = false;
                    item.setTitle(R.string.show_subtitle);
                }
                return true;*/
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
        EventChalk eventChalk = adapter.getItem(position);

        switch (item.getItemId()) {
            case R.id.menu_item_delete_chalk:
                TrueChalkLab.get(getActivity()).deleteChalk(eventChalk);
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    /**
     * Required interface for hosting activities.
     */
    public interface Callbacks {
        void onChalkSelected(EventChalk eventChalk);
    }

    private class ChalkAdapter extends ArrayAdapter<EventChalk> {
        public ChalkAdapter(ArrayList<EventChalk> eventChalks) {
            super(getActivity(), 0, eventChalks);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // If we weren't given a view, inflate one
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(
                        R.layout.list_item_chalk, null);
            }
            // Configure the view for this EventChalk
            EventChalk c = getItem(position);
            TextView titleTextView = (TextView) convertView
                    .findViewById(R.id.truechalk_list_item_titleTextView);
            titleTextView.setText(c.getTitle());
            TextView dateTextView = (TextView) convertView
                    .findViewById(R.id.truechalk_list_item_dateTextView);
            dateTextView.setText(c.getDate().toString());
            CheckBox solvedCheckBox = (CheckBox) convertView
                    .findViewById(R.id.truechalk_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(c.isSolved());
            return convertView;
        }
    }

    public void updateUI() {
        ((ChalkAdapter)getListAdapter()).notifyDataSetChanged();
    }

}
