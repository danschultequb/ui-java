package qub;

/**
 * A change to a piece of text.
 */
public interface TextChange
{
    /**
     * Get the type of text change that occurred.
     */
    public TextChangeType getChangeType();

    /**
     * Get whether this {@link TextChange} is an insertion.
     */
    public default boolean isInsertion()
    {
        return this.getChangeType() == TextChangeType.Insertion;
    }

    /**
     * Get whether this {@link TextChange} is a removal.
     */
    public default boolean isRemoval()
    {
        return this.getChangeType() == TextChangeType.Removal;
    }

    /**
     * Get the index in the text at which the change starts.
     */
    public int getStartIndex();

    /**
     * Get the number of characters that were affected by the change.
     */
    public int getLength();

    /**
     * Get the index of the first character that was not affected by this change.
     */
    public default int getAfterEndIndex()
    {
        return this.getStartIndex() + this.getLength();
    }

    /**
     * Get the text before the change.
     */
    public String getPreviousText();

    /**
     * Get the text after the change.
     */
    public String getNewText();

    /**
     * If this was an insertion text change, get the text that was inserted.
     */
    public String getInsertedText();
}
