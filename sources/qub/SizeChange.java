package qub;

/**
 * A change in an object's size.
 */
public interface SizeChange
{
    public static final String previousWidthPropertyName = "previousWidth";
    public static final String previousHeightPropertyName = "previousHeight";
    public static final String newWidthPropertyName = "newWidth";
    public static final String newHeightPropertyName = "newHeight";

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

    public default JSONObject toJson()
    {
        return JSONObject.create()
            .setNumber(SizeChange.previousWidthPropertyName, this.getPreviousWidth())
            .setNumber(SizeChange.previousHeightPropertyName, this.getPreviousHeight())
            .setNumber(SizeChange.newWidthPropertyName, this.getNewWidth())
            .setNumber(SizeChange.newHeightPropertyName, this.getNewHeight());
    }

    /**
     * Get the {@link String} representation of the provided {@link SizeChange}.
     */
    public static String toString(SizeChange sizeChange)
    {
        PreCondition.assertNotNull(sizeChange, "sizeChange");

        return sizeChange.toJson().toString();
    }

    /**
     * Get whether the provided {@link SizeChange} is equal to the provided {@link Object}.
     * @param rhs The {@link Object} to compare against the provided {@link SizeChange}.
     */
    public static boolean equals(SizeChange lhs, Object rhs)
    {
        PreCondition.assertNotNull(lhs, "lhs");

        return rhs instanceof SizeChange rhsChange && lhs.equals(rhsChange);
    }

    /**
     * Get whether the provided {@link SizeChange} is equal to this {@link SizeChange}.
     * @param rhs The {@link SizeChange} to compare against this {@link SizeChange}.
     */
    public default boolean equals(SizeChange rhs)
    {
        return rhs != null &&
            this.getPreviousWidth() == rhs.getPreviousWidth() &&
            this.getPreviousHeight() == rhs.getPreviousHeight() &&
            this.getNewWidth() == rhs.getNewWidth() &&
            this.getNewHeight() == rhs.getNewHeight();
    }
}
