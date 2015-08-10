package hu.bba.myfirstapp.interfaces;

import hu.bba.myfirstapp.models.ScrollViewExt;

public interface ScrollViewListener {
    void onScrollChanged(ScrollViewExt scrollView,
                         int x, int y, int oldx, int oldy);
}
