package sheva.vkvideofeed.mvp.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.vkvideofeed.R;
import sheva.vkvideofeed.mvp.model.entities.Item_;

/**
 * Created by shevc on 05.10.2017.
 * Let's GO!
 */

public class VideoFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Item_> videos;
    private LayoutInflater inflater;
    private Context context;
    private boolean isLoading = false;
    private String nextPageCode;
    private boolean isSent = false;

    public VideoFeedAdapter(Context context, IFeedInteractionListener listener) {
        videos = new ArrayList<>();
        inflater = LayoutInflater.from(context);
        this.context = context;
        interactionListener = listener;
    }

    public interface IFeedInteractionListener {
        void reloadFeedPartial(String nextPageCode);

        void reloadFeedBase();

        void onVideoSelected(String videoId, String ownerId);
    }

    private IFeedInteractionListener interactionListener;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View v = inflater.inflate(R.layout.item_feed, parent, false);
                return new VideoFeedItem(v);
            case 1:
                if (isLoading) {
                    View v1 = inflater.inflate(R.layout.item_feed_loading, parent, false);
                    return new FeedLoadingViewHolder(v1);
                } else {
                    View v2 = inflater.inflate(R.layout.item_feed_failed_to_load, parent, false);
                    return new FeedFailedToLoadViewHolder(v2);
                }
            default:
                return null;
        }
    }

    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (position == videos.size() - 1 && !isSent && !isLoading) {
            interactionListener.reloadFeedPartial(nextPageCode);
            isSent = true;
        }
        if (holder instanceof VideoFeedItem) {
            final VideoFeedItem videoFeedItem = (VideoFeedItem) holder;
            final Item_ video = videos.get(position);
            videoFeedItem.sdvFilmCover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    interactionListener.onVideoSelected(String.valueOf(video.getId()), String.valueOf(video.getOwnerId()));
                }
            });
            String uri;
            videoFeedItem.tvFilmLength.setText("");
            videoFeedItem.tvTitle.setText("");
            if (video.getPhoto800() != null){
                uri = video.getPhoto800();
            } else if (video.getPhoto320() != null) {
                uri = video.getPhoto320();
            } else {
                uri = video.getPhoto130();
            }
            videoFeedItem.sdvFilmCover.getHierarchy().setProgressBarImage(new ProgressBarDrawable());
            videoFeedItem.sdvFilmCover.setImageURI(uri);

            if (video.getTitle()!= null) {
                videoFeedItem.tvTitle.setText(video.getTitle());
            } else {
                videoFeedItem.tvTitle.setText("Title is unavailable for this video");
            }
            if (video.getDuration() > 0) {
                int mins = video.getDuration() / 60;
                double seconds = ((video.getDuration() /60d) - video.getDuration()/60) * 30;
                videoFeedItem.tvFilmLength.setText(mins + ":" + ((int)seconds));
            }
        } else if (holder instanceof FeedFailedToLoadViewHolder) {
            final FeedFailedToLoadViewHolder failedToLoadViewHolder = (FeedFailedToLoadViewHolder) holder;
            failedToLoadViewHolder.btnRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (videos.size() > 1) {
                        interactionListener.reloadFeedPartial(nextPageCode);
                    } else {
                        interactionListener.reloadFeedBase();
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (videos.get(position) == null) {
            return 1;
        } else {
            return 0;
        }
    }

    public void onStartLoading() {
        Handler handler = new Handler();
        isLoading = true;
        if (!videos.contains(null)) {
            videos.add(null);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    notifyItemInserted(videos.size() - 1);
                }
            }, 100);
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    notifyItemChanged(videos.size() - 1);
                }
            }, 100);
        }
    }

    public void onFailedToLoad() {
        isLoading = false;
        notifyItemChanged(videos.size() - 1);
    }

    public void updateListWhole(List<Item_> list) {
        isSent = false;
        if (isLoading) {
            isLoading = false;
        }
        videos.clear();
        videos.addAll(list);
        notifyDataSetChanged();
    }

    public void setNextPageCode(String code) {
        nextPageCode = code;
    }

    public void updateListAtEnding(List<Item_> list) {
        isSent = false;
        if (isLoading) {
            isLoading = false;
            int lastPos = videos.size() - 1;
            videos.remove(null);
            notifyItemChanged(lastPos);
        }
        int currPos = videos.size() - 1;
        videos.addAll(list);
        notifyItemRangeInserted(currPos, list.size());
    }

    public List<Item_> getList() {
        return videos;
    }

    public Item_ getItem(int position) {
        return videos.get(position);
    }

    static class VideoFeedItem extends RecyclerView.ViewHolder {
        @BindView(R.id.sdvFilmCover)
        SimpleDraweeView sdvFilmCover;
        @BindView(R.id.tvFilmTitle)
        TextView tvTitle;
        @BindView(R.id.tvFilmLength)
        TextView tvFilmLength;

        VideoFeedItem(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class FeedLoadingViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pbItemFeedLoading)
        ProgressBar pbLoading;

        FeedLoadingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            pbLoading.getIndeterminateDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
        }
    }

    static class FeedFailedToLoadViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.btnItemFeedRetry)
        Button btnRetry;

        FeedFailedToLoadViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
