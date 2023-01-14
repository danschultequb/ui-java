package qub;

/**
 * A change in an object's size.
 */
public interface SizeChange
{
    public static MutableSizeChange create()
    {
        return MutableSizeChange.create();
    }

    /**
     * Get whether the object's width changed.
     */
    public default boolean getWidthChanged()
    {
        return this.getPreviousWidth() != this.getNewWidth();
    }

    /**
     * Get whether the object's height changed.
     */
    public default boolean getHeightChanged()
    {
        return this.getPreviousHeight() != this.getNewHeight();
    }

    /**
     * Get the pixel width of the object before the change.
     */
    public int getPreviousWidth();

    /**
     * Get the pixel height of the object before the change.
     */
    public int getPreviousHeight();

    /**
     * Get the pixel size of the object before the change.
     */
    public Size2Integer getPreviousSize();

    /**
     * Get the pixel width of the object after the change.
     */
    public int getNewWidth();

    /**
     * Get the pixel height of the object after the change.
     */
    public int getNewHeight();

    /**
     * Get the pixel size of the object after the change.
     */
    public Size2Integer getNewSize();
}
