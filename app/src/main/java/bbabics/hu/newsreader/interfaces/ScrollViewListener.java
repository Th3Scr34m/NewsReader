package bbabics.hu.newsreader.interfaces;

import bbabics.hu.newsreader.models.ScrollViewExt;

public interface ScrollViewListener {
    void onScrollChanged(ScrollViewExt scrollView, int x, int y, int oldx, int oldy);
}
