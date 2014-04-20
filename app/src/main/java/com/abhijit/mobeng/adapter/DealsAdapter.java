package com.abhijit.mobeng.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhijit.mobeng.R;
import com.abhijit.mobeng.model.Deal;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by AbhijitNukalapati on 4/20/14.
 */
public class DealsAdapter extends ArrayAdapter<Deal>{

    private List<Deal> mDeals;

    public DealsAdapter(Context context, int resource, List<Deal> deals) {
        super(context, resource, deals);
        mDeals = deals;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView != null) {
            holder =  (ViewHolder) convertView.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_row, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        Deal deal = mDeals.get(position);

        holder.dealTitle.setText(deal.getAttrib());
        Picasso.with(getContext())
                .load(deal.getSrc())
                .into(holder.dealImage);

        return convertView;
    }


    static class ViewHolder {
        @InjectView(R.id.deal_title) TextView dealTitle;
        @InjectView(R.id.deal_image) ImageView dealImage;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}


