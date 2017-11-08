
package sheva.vkvideofeed.mvp.model.entities.videoentity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Files {

    @SerializedName("external")
    @Expose
    private String external;
    @SerializedName("live")
    @Expose
    private String live;
    @SerializedName("hls")
    @Expose
    private String hls;

    public String getExternal() {
        return external;
    }

    public void setExternal(String external) {
        this.external = external;
    }

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

    public String getHls() {
        return hls;
    }

    public void setHls(String hls) {
        this.hls = hls;
    }

}
