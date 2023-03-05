package qub;

/**
 * Measurements for a piece of text.
 */
public interface TextMeasurements
{
    /**
     * Get the ascent of the text.
     */
    public int getAscent();

    /**
     * Get the descent of the text.
     */
    public int getDescent();

    /**
     * Get the height of the text.
     */
    public int getHeight();

    /**
     * Get the width of the text.
     */
    public int getWidth();
}
