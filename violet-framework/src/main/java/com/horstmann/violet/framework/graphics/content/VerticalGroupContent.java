package com.horstmann.violet.framework.graphics.content;

import com.horstmann.violet.framework.graphics.content.Content;
import com.horstmann.violet.framework.graphics.content.GroupContent;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

/**
 * Created by Adrian Bobrowski on 21.12.2015.
 */
public class VerticalGroupContent extends GroupContent
{
    @Override
    protected Point2D getNextOffset(Point2D beforeOffset, Content content) {
        return new Point2D.Double(beforeOffset.getX(),beforeOffset.getY() + content.getHeight());
    }

    @Override
    protected Point2D getStartPointSeparator(Point2D offset) {
        return new Point2D.Double(0, offset.getY());
    }

    @Override
    protected Point2D getEndPointSeparator(Point2D offset) {
        return new Point2D.Double(getWidth(), offset.getY());
    }

    @Override
    public final void refresh() {
        int height = 0;
        int width = 0;

        for (Content content: getContents()) {
            height += content.getHeight();
            if(width < content.getWidth())
            {
                width = content.getWidth();
            }
        }

        setHeight(height);
        setWidth(width);
        setContentsWidth(width);

        super.refresh();
    }
}