package sheva.vkvideofeed.mvp.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.vkvideofeed.R;
import sheva.vkvideofeed.customviews.WrapperLinearLayoutManager;
import sheva.vkvideofeed.mvp.model.entities.Item_;
import sheva.vkvideofeed.mvp.presenter.activities.FeedActivityPresenter;
import sheva.vkvideofeed.mvp.ui.adapters.VideoFeedAdapter;
import sheva.vkvideofeed.mvp.ui.interfaces.IFeedActivityView;
import sheva.vkvideofeed.utils.IConstants;

public class FeedActivity extends AppCompatActivity implements VideoFeedAdapter.IFeedInteractionListener, IFeedActivityView{
    @BindView(R.id.rvFeedList)
    RecyclerView rvList;
    private VideoFeedAdapter adapter;
    private final FeedActivityPresenter presenter = new FeedActivityPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        ButterKnife.bind(this);
        presenter.bind(this);
        adapter = new VideoFeedAdapter(this, this);
        rvList.setLayoutManager(new WrapperLinearLayoutManager(this));
        rvList.setAdapter(adapter);
        presenter.loadBase();
    }

    @Override
    public void reloadFeedPartial(String nextPageCode) {
        presenter.loadPartial(nextPageCode);
    }

    @Override
    public void reloadFeedBase() {
        presenter.loadBase();
    }

    @Override
    public void onVideoSelected(String videoId, String ownerId) {
        String fullVideoId = ownerId + "_" + videoId;
        Intent intent = new Intent(this, VideoActivity.class);
        intent.putExtra(IConstants.VIDEO_ID_KEY, fullVideoId);
        startActivity(intent);
    }

    @Override
    public void onBaseUpdated(List<Item_> items, String nextPageCode) {
        adapter.updateListWhole(items);
        adapter.setNextPageCode(nextPageCode);
    }

    @Override
    public void onPartialUpdate(List<Item_> items, String nextPageCode) {
        adapter.updateListAtEnding(items);
        adapter.setNextPageCode(nextPageCode);
    }

    @Override
    public void onStartLoading() {
        adapter.onStartLoading();
    }

    @Override
    public void onFailedToLoad() {
        adapter.onFailedToLoad();
    }

    @Override
    public void onLogout() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        presenter.unbind();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,0,"Exit");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 0) {
            presenter.logout();
        }
        return super.onOptionsItemSelected(item);
    }
}
