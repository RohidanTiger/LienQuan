package chickenzero.ht.com.lienquan.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by QuyDV on 4/19/17.
 */

public class PlayListYoutube{
    @SerializedName("kind")
    private String kind;

    @SerializedName("etag")
    private String etag;

    @SerializedName("nextPageToken")
    private String nextPageToken;

    @SerializedName("items")
    private List<Item> items;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public class Item {
        @SerializedName("snippet")
        private Snippet snippet;

        public Snippet getSnippet() {
            return snippet;
        }

        public void setSnippet(Snippet snippet) {
            this.snippet = snippet;
        }

    }

    public class Snippet {
        @SerializedName("publishedAt")
        private String publishedAt;

        @SerializedName("channelId")
        private String channelId;

        @SerializedName("title")
        private String title;

        @SerializedName("thumbnails")
        private Thumbnails thumbnails;

        @SerializedName("channelTitle")
        private String channelTitle;

        @SerializedName("position")
        private Integer position;

        @SerializedName("resourceId")
        private ResourceId resourceId;

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Thumbnails getThumbnails() {
            return thumbnails;
        }

        public void setThumbnails(Thumbnails thumbnails) {
            this.thumbnails = thumbnails;
        }

        public String getChannelTitle() {
            return channelTitle;
        }

        public void setChannelTitle(String channelTitle) {
            this.channelTitle = channelTitle;
        }

        public Integer getPosition() {
            return position;
        }

        public void setPosition(Integer position) {
            this.position = position;
        }

        public ResourceId getResourceId() {
            return resourceId;
        }

        public void setResourceId(ResourceId resourceId) {
            this.resourceId = resourceId;
        }

    }

    public class Thumbnails {

        @SerializedName("high")
        private High high;


        public High getHigh() {
            return high;
        }

        public void setHigh(High high) {
            this.high = high;
        }
    }

    public class High {

        @SerializedName("url")
        private String url;

        @SerializedName("width")
        private Integer width;

        @SerializedName("height")
        private Integer height;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

    }

    public class ResourceId {

        @SerializedName("kind")
        private String kind;

        @SerializedName("videoId")
        private String videoId;

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

    }

}
