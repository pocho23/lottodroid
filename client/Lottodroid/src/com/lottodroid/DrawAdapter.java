package com.lottodroid;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class DrawAdapter extends BaseAdapter {

    private Context context;
    private List<IDraw> drawList;

    public DrawAdapter(Context context, List<IDraw> drawList ) { 
        this.context = context;
        this.drawList = drawList;
    }

    public int getCount() {                        
        return drawList.size();
    }

    public Object getItem(int position) {     
        return drawList.get(position);
    }

    public long getItemId(int position) {  
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) 
    {
    	IDraw draw = drawList.get(position);
    	
    	/* Get the specific draw view for this object */
    	View v = View.inflate(context, draw.getLayoutResource(), null);
    	
    	draw.bindData(v);
    	
        
        return v;
    }

}