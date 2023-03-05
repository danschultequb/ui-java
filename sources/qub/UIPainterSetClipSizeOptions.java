package qub;

/**
 * Options that can be passed to {@link UIPainter}.setClipSize().
 */
public class UIPainterSetClipSizeOptions
{
    private static final String widthPropertyName = "width";
    private static final String heightPropertyName = "height";
    private static final String resetClipPropertyName = "resetClip";

    private int width;
    private int height;
    private boolean resetClip;

    private UIPainterSetClipSizeOptions()
    {
    }

    public static UIPainterSetClipSizeOptions create()
    {
        return new UIPainterSetClipSizeOptions();
    }

    public int getWidth()
    {
        return this.width;
    }

    public UIPainterSetClipSizeOptions setWidth(int width)
    {
        PreCondition.assertGreaterThanOrEqualTo(width, 0, "width");

        this.width = width;

        return this;
    }

    public int getHeight()
    {
        return this.height;
    }

    public UIPainterSetClipSizeOptions setHeight(int height)
    {
        PreCondition.assertGreaterThanOrEqualTo(height, 0, "height");

        this.height = height;

        return this;
    }

    public Size2Integer getSize()
    {
        return Size2.create(this.width, this.height);
    }

    public UIPainterSetClipSizeOptions setSize(int width, int height)
    {
        PreCondition.assertGreaterThanOrEqualTo(width, 0, "width");
        PreCondition.assertGreaterThanOrEqualTo(height, 0, "height");

        this.width = width;
        this.height = height;

        return this;
    }

    public UIPainterSetClipSizeOptions setSize(Size2<Integer> size)
    {
        PreCondition.assertNotNull(size, "size");

        return this.setSize(size.getWidth(), size.getHeight());
    }

    public boolean getResetClip()
    {
        return this.resetClip;
    }

    public UIPainterSetClipSizeOptions setResetClip(boolean resetClip)
    {
        this.resetClip = resetClip;

        return this;
    }

    @Override
    public boolean equals(Object rhs)
    {
        return rhs instanceof UIPainterSetClipSizeOptions options &&
            this.width == options.width &&
            this.height == options.height &&
            this.resetClip == options.resetClip;
    }

    @Override
    public String toString()
    {
        return this.toJson().toString();
    }

    /**
     * Get the JSON representation of this {@link UIPainterSetClipSizeOptions}.
     */
    public JSONObject toJson()
    {
        return JSONObject.create()
            .setNumber(UIPainterSetClipSizeOptions.widthPropertyName, this.width)
            .setNumber(UIPainterSetClipSizeOptions.heightPropertyName, this.height)
            .setBoolean(UIPainterSetClipSizeOptions.resetClipPropertyName, this.resetClip);
    }
}
