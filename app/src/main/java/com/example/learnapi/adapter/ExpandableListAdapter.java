package com.example.learnapi.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.learnapi.R;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader;
    private HashMap<String, List<String>> _listDataChild;
    private TextView selectedTextView = null;
    private Button button;
    public static String selectedHeader;
    public static String selectedHour;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData,Button button) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.button = button;
    }


    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item_date, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);


        txtListChild.setText(childText);
        txtListChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTextView != null) {
                    selectedTextView.setTextColor(_context.getResources().getColor(R.color.white));
                    button.setEnabled(false);
                    button.setBackgroundTintList(ContextCompat.getColorStateList(_context, R.color.gray));
                }
                if (selectedTextView == txtListChild) {
                    button.setEnabled(false);
                    selectedTextView = null;
                    txtListChild.setTextColor(_context.getResources().getColor(R.color.white));
                    button.setBackgroundTintList(ContextCompat.getColorStateList(_context, R.color.gray));
                }
                else {
                    button.setEnabled(true);
                    selectedTextView = txtListChild;
                    txtListChild.setTextColor(_context.getResources().getColor(R.color.green));
                    selectedHeader = _listDataHeader.get(groupPosition);
                    selectedHour = txtListChild.getText().toString();
                    button.setBackgroundTintList(ContextCompat.getColorStateList(_context, R.color.green));


                }



            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }



    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_date, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

