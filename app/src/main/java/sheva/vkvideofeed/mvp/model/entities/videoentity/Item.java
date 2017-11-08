
package sheva.vkvideofeed.mvp.model.entities.videoentity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("owner_id")
    @Expose
    private int ownerId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("duration")
    @Expose
    private int duration;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("date")
    @Expose
    private int date;
    @SerializedName("comments")
    @Expose
    private int comments;
    @SerializedName("views")
    @Expose
    private int views;
    @SerializedName("width")
    @Expose
    private int width;
    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("photo_130")
    @Expose
    private String photo130;
    @SerializedName("photo_320")
    @Expose
    private String photo320;
    @SerializedName("photo_800")
    @Expose
    private String photo800;
    @SerializedName("live_status")
    @Expose
    private String liveStatus;
    @SerializedName("live")
    @Expose
    private int live;
    @SerializedName("spectators")
    @Expose
    private int spectators;
    @SerializedName("first_frame_800")
    @Expose
    private String firstFrame800;
    @SerializedName("first_frame_320")
    @Expose
    private String firstFrame320;
    @SerializedName("first_frame_160")
    @Expose
    private String firstFrame160;
    @SerializedName("first_frame_130")
    @Expose
    private String firstFrame130;
    @SerializedName("files")
    @Expose
    private Files files;
    @SerializedName("player")
    @Expose
    private String player;
    @SerializedName("can_add")
    @Expose
    private int canAdd;
    @SerializedName("can_comment")
    @Expose
    private int canComment;
    @SerializedName("can_repost")
    @Expose
    private int canRepost;
    @SerializedName("likes")
    @Expose
    private Likes likes;
    @SerializedName("reposts")
    @Expose
    private Reposts reposts;
    @SerializedName("repeat")
    @Expose
    private int repeat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPhoto130() {
        return photo130;
    }

    public void setPhoto130(String photo130) {
        this.photo130 = photo130;
    }

    public String getPhoto320() {
        return photo320;
    }

    public void setPhoto320(String photo320) {
        this.photo320 = photo320;
    }

    public String getPhoto800() {
        return photo800;
    }

    public void setPhoto800(String photo800) {
        this.photo800 = photo800;
    }

    public String getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(String liveStatus) {
        this.liveStatus = liveStatus;
    }

    public int getLive() {
        return live;
    }

    public void setLive(int live) {
        this.live = live;
    }

    public int getSpectators() {
        return spectators;
    }

    public void setSpectators(int spectators) {
        this.spectators = spectators;
    }

    public String getFirstFrame800() {
        return firstFrame800;
    }

    public void setFirstFrame800(String firstFrame800) {
        this.firstFrame800 = firstFrame800;
    }

    public String getFirstFrame320() {
        return firstFrame320;
    }

    public void setFirstFrame320(String firstFrame320) {
        this.firstFrame320 = firstFrame320;
    }

    public String getFirstFrame160() {
        return firstFrame160;
    }

    public void setFirstFrame160(String firstFrame160) {
        this.firstFrame160 = firstFrame160;
    }

    public String getFirstFrame130() {
        return firstFrame130;
    }

    public void setFirstFrame130(String firstFrame130) {
        this.firstFrame130 = firstFrame130;
    }

    public Files getFiles() {
        return files;
    }

    public void setFiles(Files files) {
        this.files = files;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getCanAdd() {
        return canAdd;
    }

    public void setCanAdd(int canAdd) {
        this.canAdd = canAdd;
    }

    public int getCanComment() {
        return canComment;
    }

    public void setCanComment(int canComment) {
        this.canComment = canComment;
    }

    public int getCanRepost() {
        return canRepost;
    }

    public void setCanRepost(int canRepost) {
        this.canRepost = canRepost;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public Reposts getReposts() {
        return reposts;
    }

    public void setReposts(Reposts reposts) {
        this.reposts = reposts;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

}
