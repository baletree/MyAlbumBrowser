package com.xbx.album.view;

/**
 * Created by Eric on 2016/11/15.
 */

public interface PageDecorationLastJudge {
    /**
     * Is the last row in one page
     *
     * @param position
     * @return
     */
    boolean isLastRow(int position);

    /**
     * Is the last Colum in one row;
     *
     * @param position
     * @return
     */
    boolean isLastColumn(int position);

    boolean isPageLast(int position);
}
