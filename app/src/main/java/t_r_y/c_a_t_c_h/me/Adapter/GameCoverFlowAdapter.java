package t_r_y.c_a_t_c_h.me.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import t_r_y.c_a_t_c_h.me.R;
import t_r_y.c_a_t_c_h.me.Xbox.Game;

/**
 * Created by Admin on 10.12.2016.
 */

public class GameCoverFlowAdapter extends BaseAdapter {

    private ArrayList<Game> mData = new ArrayList<>(0);
    private Context mContext;

    public GameCoverFlowAdapter(Context context, ArrayList<Game> gameList) {
        mContext = context;
        this.mData = gameList;
    }

    public void setData(ArrayList<Game> data) {
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Game getItem(int pos) {
        return mData.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_coverflow_game, null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) rowView.findViewById(R.id.label);
            viewHolder.image = (ImageView) rowView.findViewById(R.id.image);
            rowView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder) rowView.getTag();

        Picasso.with(mContext).load(mData.get(position).getCoverUrl()).placeholder(R.drawable.xbox_cover).fit().into(holder.image);

        holder.text.setText(mData.get(position).getName());

        return rowView;
    }


    static class ViewHolder {
        public TextView text;
        public ImageView image;
    }
}
