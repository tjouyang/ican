package cn.hnust.icanjiuye.entities;

/**
 * Created by tjouyang on 2017/6/2.
 *
 */

public class Community {
    private String title;
    private String content;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "title " + title +
                "content " + content;
    }
}
