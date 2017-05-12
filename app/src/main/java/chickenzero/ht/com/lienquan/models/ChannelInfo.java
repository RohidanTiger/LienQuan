package chickenzero.ht.com.lienquan.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by QuyDV on 5/10/17.
 */

public class ChannelInfo {

    @SerializedName("items")
    private List<Item> items = null;


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public class Item {

        @SerializedName("kind")
        private String kind;

        @SerializedName("etag")
        private String etag;

        @SerializedName("id")
        private String id;

        @SerializedName("snippet")
        private Snippet snippet;

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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Snippet getSnippet() {
            return snippet;
        }

        public void setSnippet(Snippet snippet) {
            this.snippet = snippet;
        }

    }

    public class Snippet {

        @SerializedName("title")
        private String title;

        @SerializedName("description")
        private String description;

        @SerializedName("customUrl")
        private String customUrl;

        @SerializedName("publishedAt")
        private String publishedAt;

        @SerializedName("thumbnails")
        private Thumbnails thumbnails;

        @SerializedName("localized")
        private Localized localized;

        @SerializedName("country")
        private String country;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCustomUrl() {
            return customUrl;
        }

        public void setCustomUrl(String customUrl) {
            this.customUrl = customUrl;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public Thumbnails getThumbnails() {
            return thumbnails;
        }

        public void setThumbnails(Thumbnails thumbnails) {
            this.thumbnails = thumbnails;
        }

        public Localized getLocalized() {
            return localized;
        }

        public void setLocalized(Localized localized) {
            this.localized = localized;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

    }

    public class Localized {

        @SerializedName("title")
        private String title;

        @SerializedName("description")
        private String description;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }



    public class Thumbnails {
        @SerializedName("default")
        private Default _default;

        @SerializedName("medium")
        private Medium medium;

        @SerializedName("high")
        private High high;

        public Default getDefault() {
            return _default;
        }

        public void setDefault(Default _default) {
            this._default = _default;
        }

        public Medium getMedium() {
            return medium;
        }

        public void setMedium(Medium medium) {
            this.medium = medium;
        }

        public High getHigh() {
            return high;
        }

        public void setHigh(High high) {
            this.high = high;
        }

    }

    public class Default {

        @SerializedName("url")
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public class Medium {

        @SerializedName("url")
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public class High {

        @SerializedName("url")
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
