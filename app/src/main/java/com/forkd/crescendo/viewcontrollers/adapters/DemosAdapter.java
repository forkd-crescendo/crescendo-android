package com.forkd.crescendo.viewcontrollers.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.forkd.crescendo.R;
import com.forkd.crescendo.models.Artwork;
import com.forkd.crescendo.viewcontrollers.activities.ArtworkDetailActivity;

import java.util.List;

public class DemosAdapter extends RecyclerView.Adapter<DemosAdapter.ViewHolder> {

    private List<Artwork> demos;

    @Override
    public DemosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_demo, parent, false));
    }

    @Override
    public void onBindViewHolder(DemosAdapter.ViewHolder holder, int position) {
        holder.updatesViews(demos.get(position));
    }

    @Override
    public int getItemCount() {
        return demos.size();
    }

    public List<Artwork> getDemos() {
        return demos;
    }

    public DemosAdapter setDemos(List<Artwork> demos) {
        this.demos = demos;
        return this;
    }

    public DemosAdapter() {

    }

    public DemosAdapter(List<Artwork> demos) {
        this.demos = demos;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ANImageView demoThumbnail;
        private TextView demoTitle;
        private ConstraintLayout demoLayout;
        public ViewHolder(View view) {
            super(view);
            demoThumbnail = (ANImageView) view.findViewById(R.id.item_demo_thumbnail);
            demoTitle = (TextView) view.findViewById(R.id.item_demo_title);
            demoLayout = (ConstraintLayout) view.findViewById(R.id.layout_demo_item);
        }

        public void updatesViews(final Artwork demo) {
            demoThumbnail.setDefaultImageResId(R.mipmap.ic_launcher);
            demoThumbnail.setErrorImageResId(R.mipmap.ic_launcher);
            demoThumbnail.setImageUrl(demo.getThumbnail());
            demoTitle.setText(demo.getTitle());
            demoLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    context.startActivity(new Intent(
                            context, ArtworkDetailActivity.class
                    ).putExtras(demo.toBundle()));
                }
            });
        }
    }
}
