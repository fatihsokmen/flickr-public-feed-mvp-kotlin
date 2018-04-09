package com.github.flickr.home.data;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "feed", strict = false)
public class PhotoFeedDTO {

    @ElementList(inline = true)
    public List<EntryDTO> entries;

    @Root(name = "entry", strict = false)
    public static class EntryDTO {
        @Element
        public String title;
        @Element(required = false)
        AuthorDTO author;
        @ElementList(inline=true)
        List<LinkDTO> links;
    }

    @Root(name = "author", strict = false)
    public static class AuthorDTO {
        @Element
        public String name;
        @Element
        String buddyicon;
    }

    @Root(name="link")
    public static class LinkDTO {
        @Attribute
        public String rel;
        @Attribute
        String type;
        @Attribute
        String href;
    }
}


