package sheva.vkvideofeed.mvp.model.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import sheva.vkvideofeed.mvp.model.entities.NewsFeedEntity;
import sheva.vkvideofeed.mvp.model.entities.videoentity.VideoEntity;

/**
 * Created by Никита on 08.11.2017.
 */

public interface ServerAPI {
    @GET("/method/newsfeed.get")
    Observable<NewsFeedEntity> getVideosFromFeed(@Query("filters") String filters, @Query("count") int count, @Query("start_from") String startFrom, @Query("access_token") String token, @Query("v") String version);

    @GET("/method/video.get")
    Observable<VideoEntity> getVideoById(@Query("videos") String videos, @Query("count") int count, @Query("access_token") String token, @Query("v") String version);
}
