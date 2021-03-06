package dong.lan.tuyi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import dong.lan.tuyi.R;
import dong.lan.tuyi.bean.TUser;
import dong.lan.tuyi.utils.MyImageAsyn;

/**
 * Created by 梁桂栋 on 2015/11/7.
 */
public class RadarUserAdapter extends RecyclerView.Adapter<RadarUserHolder> {


    private Context context;
    private List<TUser> users;
    private LayoutInflater inflater;
    public RadarUserAdapter(Context context,List<TUser> users)
    {
        this.context =context;
        this.users = users;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public RadarUserHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_radar_user,null);
        return new RadarUserHolder(view);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public void onBindViewHolder(final RadarUserHolder holder, int pos) {
        holder.name.setText(users.get(pos).getUsername());
        if(users.get(pos).getDes()==null)
        holder.des.setText("给Ta打个招呼吧");
        else
            holder.des.setText(users.get(pos).getDes());
        new MyImageAsyn(holder.head, MyImageAsyn.NORMAL).execute(users.get(pos).getHead());
            holder.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(clickListener!=null)
                    clickListener.onitemClik(users.get(holder.getPosition()));
                }
            });
    }

    public void replaceUser(List<TUser> users)
    {
        this.users =users;
    }

    public interface itemClickListener
    {
        void onitemClik(TUser user);
    }
    itemClickListener clickListener;
    public void setOnItemClickListener(itemClickListener listener)
    {
        clickListener =listener;
    }

}

class RadarUserHolder extends RecyclerView.ViewHolder
{


    public ImageView head;
    public TextView name;
    public TextView des;
    public RelativeLayout parent;
    public RadarUserHolder(View v) {
        super(v);
        parent = (RelativeLayout) v.findViewById(R.id.radar_user_parent);
        head = (ImageView) v.findViewById(R.id.radar_user_head);
        name = (TextView) v.findViewById(R.id.radar_user_name);
        des = (TextView) v.findViewById(R.id.radar_user_des);
    }
}