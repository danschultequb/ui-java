package qub;

public interface JavaAwtFrames
{
    public static Color getBackgroundColor(java.awt.Frame frame)
    {
        PreCondition.assertNotNull(frame, "frame");

        return JavaAwtComponents.getBackgroundColor(frame);
    }

    public static void setBackgroundColor(java.awt.Frame frame, Color backgroundColor)
    {
        PreCondition.assertNotNull(frame, "frame");
        PreCondition.assertNotNull(backgroundColor, "backgroundColor");

        JavaAwtComponents.setBackgroundColor(frame, backgroundColor);
    }

    public static String getTitle(java.awt.Frame frame)
    {
        PreCondition.assertNotNull(frame, "frame");

        return frame.getTitle();
    }

    public static void setTitle(java.awt.Frame frame, String title)
    {
        PreCondition.assertNotNull(frame, "frame");
        PreCondition.assertNotNull(title, "title");

        frame.setTitle(title);
    }

    public static boolean getVisible(java.awt.Frame frame)
    {
        PreCondition.assertNotNull(frame, "frame");

        return frame.isVisible();
    }

    public static void setVisible(java.awt.Frame frame, boolean visible)
    {
        PreCondition.assertNotNull(frame, "frame");

        frame.setVisible(visible);
    }

    public static Size2Integer getSize(java.awt.Frame frame)
    {
        PreCondition.assertNotNull(frame, "frame");

        final int width = frame.getWidth();
        final int height = frame.getHeight();
        return Size2.create(width, height);
    }

    public static Size2Distance getDistanceSize(java.awt.Frame frame, UI ui)
    {
        PreCondition.assertNotNull(frame, "frame");
        PreCondition.assertNotNull(ui, "ui");

        final int width = frame.getWidth();
        final int height = frame.getHeight();
        return ui.convertSizePixelsToDistance(width, height);
    }

    public static void setSize(java.awt.Frame frame, int width, int height)
    {
        PreCondition.assertNotNull(frame, "frame");
        PreCondition.assertGreaterThanOrEqualTo(width, 0, "width");
        PreCondition.assertGreaterThanOrEqualTo(height, 0, "height");

        frame.setSize(width, height);
    }

    public static void setSize(java.awt.Frame frame, UI ui, Distance width, Distance height)
    {
        PreCondition.assertNotNull(frame, "frame");
        PreCondition.assertGreaterThanOrEqualTo(width, Distance.zero, "width");
        PreCondition.assertGreaterThanOrEqualTo(height, Distance.zero, "height");

        final int pixelWidth = ui.convertHorizontalDistanceToPixels(width);
        final int pixelHeight = ui.convertVerticalDistanceToPixels(height);
        JavaAwtFrames.setSize(frame, pixelWidth, pixelHeight);
    }

    public static Disposable onSizeChanged(java.awt.Frame frame, Action1<SizeChange> action)
    {
        PreCondition.assertNotNull(frame, "frame");
        PreCondition.assertNotNull(action, "action");

        final JavaComponentListener componentListener = JavaComponentListener.create()
            .setGetCurrentSizeFunction(() -> JavaAwtFrames.getSize(frame))
            .setSizeChangedAction(action);

        final java.awt.Component component = frame.getComponent(0);
        return JavaAwtComponents.addComponentListener(component, componentListener);
    }

    public static int getWidth(java.awt.Frame frame)
    {
        PreCondition.assertNotNull(frame, "frame");

        return frame.getWidth();
    }

    public static Distance getWidthDistance(java.awt.Frame frame, UI ui)
    {
        PreCondition.assertNotNull(frame, "frame");
        PreCondition.assertNotNull(ui, "ui");

        final int width = JavaAwtFrames.getWidth(frame);
        final Distance result = ui.convertHorizontalPixelsToDistance(width);

        PostCondition.assertNotNull(result, "result");
        PostCondition.assertGreaterThanOrEqualTo(result, Distance.zero, "result");

        return result;
    }

    public static void setWidth(java.awt.Frame frame, int width)
    {
        PreCondition.assertNotNull(frame, "frame");
        PreCondition.assertGreaterThanOrEqualTo(width, 0, "width");

        final int height = JavaAwtFrames.getHeight(frame);
        JavaAwtFrames.setSize(frame, width, height);
    }

    public static void setWidth(java.awt.Frame frame, UI ui, Distance width)
    {
        PreCondition.assertNotNull(frame, "frame");
        PreCondition.assertNotNull(ui, "ui");
        PreCondition.assertNotNull(width, "width");
        PreCondition.assertGreaterThanOrEqualTo(width, Distance.zero, "width");

        final int pixelWidth = ui.convertHorizontalDistanceToPixels(width);
        JavaAwtFrames.setWidth(frame, pixelWidth);
    }

    public static int getHeight(java.awt.Frame frame)
    {
        PreCondition.assertNotNull(frame, "frame");

        return frame.getHeight();
    }

    public static Distance getHeightDistance(java.awt.Frame frame, UI ui)
    {
        PreCondition.assertNotNull(frame, "frame");
        PreCondition.assertNotNull(ui, "ui");

        final int pixelHeight = JavaAwtFrames.getHeight(frame);
        final Distance result = ui.convertVerticalPixelsToDistance(pixelHeight);

        PostCondition.assertNotNull(result, "result");
        PostCondition.assertGreaterThanOrEqualTo(result, Distance.zero, "result");

        return result;
    }

    public static void setHeight(java.awt.Frame frame, int height)
    {
        PreCondition.assertNotNull(frame, "frame");
        PreCondition.assertGreaterThanOrEqualTo(height, 0, "height");

        final int width = JavaAwtFrames.getWidth(frame);
        JavaAwtFrames.setSize(frame, width, height);
    }

    public static void setHeight(java.awt.Frame frame, UI ui, Distance height)
    {
        PreCondition.assertNotNull(frame, "frame");
        PreCondition.assertNotNull(ui, "ui");
        PreCondition.assertNotNull(height, "height");
        PreCondition.assertGreaterThanOrEqualTo(height, Distance.zero, "height");

        final int pixelHeight = ui.convertVerticalDistanceToPixels(height);
        JavaAwtFrames.setHeight(frame, pixelHeight);
    }

    public static Point2Integer getContentAreaTopLeftCorner(java.awt.Frame frame)
    {
        PreCondition.assertNotNull(frame, "frame");

        final java.awt.Insets insets = frame.getInsets();
        final Point2Integer result = Point2.create(insets.left, insets.top);

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    public static int getContentAreaWidth(java.awt.Frame frame)
    {
        PreCondition.assertNotNull(frame, "frame");

        final java.awt.Insets insets = frame.getInsets();
        final int width = JavaAwtFrames.getWidth(frame);
        final int result = width - insets.left - insets.right;

        PostCondition.assertGreaterThanOrEqualTo(result, 0, "result");

        return result;
    }

    public static int getContentAreaHeight(java.awt.Frame frame)
    {
        PreCondition.assertNotNull(frame, "frame");

        final java.awt.Insets insets = frame.getInsets();
        final int height = JavaAwtFrames.getHeight(frame);
        final int result = height - insets.top - insets.bottom;

        PostCondition.assertGreaterThanOrEqualTo(result, 0, "result");

        return result;
    }

    public static Size2Integer getContentAreaSize(java.awt.Frame frame)
    {
        PreCondition.assertNotNull(frame, "frame");

        final java.awt.Insets insets = frame.getInsets();
        final int width = JavaAwtFrames.getWidth(frame);
        final int contentAreaWidth = width - (insets.left + insets.right);

        final int height = JavaAwtFrames.getHeight(frame);
        final int contentAreaHeight = height - (insets.top + insets.bottom);

        final Size2Integer result = Size2.create(contentAreaWidth, contentAreaHeight);

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    public static Disposable onContentAreaSizeChanged(java.awt.Frame frame, Action1<SizeChange> action)
    {
        PreCondition.assertNotNull(frame, "frame");
        PreCondition.assertNotNull(action, "action");

        final JavaComponentListener componentListener = JavaComponentListener.create()
            .setGetCurrentSizeFunction(() -> JavaAwtFrames.getContentAreaSize(frame))
            .setSizeChangedAction(action);

        final java.awt.Component component = frame.getComponent(0);
        return JavaAwtComponents.addComponentListener(component, componentListener);
    }
}
