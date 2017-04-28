package chickenzero.ht.com.lienquan.models;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by QuyDV on 4/20/17.
 */

@Root(name="rss", strict=false)
public class NewsList {

    @Element
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Root(name = "channel")
    public static class Channel {
        @ElementList(inline = true, required = false)
        private List<Item> item;

        public List<Item> getItem() {
            return item;
        }

        public void setItem(List<Item> item) {
            this.item = item;
        }
    }

    @Root(name = "item", strict = false)
    public static class Item {
        @Element
        private String pubDate;

        @Element
        private String title;

        @Element
        private String link;

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}
