package qub;

/**
 * A {@link Transform2} that can change its values.
 */
public interface MutableTransform2 extends Transform2
{
    /**
     * Create a new {@link MutableTransform2}.
     */
    public static MutableTransform2 create()
    {
        return AffineTransform2.create();
    }

    /**
     * Create a new {@link MutableTransform2} from the provided {@link Transform2}.
     * @param toCopy The {@link Transform2} to copy.
     */
    public static MutableTransform2 create(Transform2 toCopy)
    {
        return AffineTransform2.create(toCopy);
    }

    /**
     * Set this {@link MutableTransform2} to copy the provided {@link Transform2}.
     * @param toCopy The {@link Transform2} to copy.
     * @return This object for method chaining.
     */
    public MutableTransform2 set(Transform2 toCopy);

    /**
     * Translate this {@link MutableTransform2} by the provided x-value.
     * @param x The x-value to translate this {@link MutableTransform2} by.
     * @return This object for method chaining.
     */
    public default MutableTransform2 translateX(int x)
    {
        return this.translate(x, 0);
    }

    /**
     * Translate this {@link MutableTransform2} by the provided y-value.
     * @param y The y-value to translate this {@link MutableTransform2} by.
     * @return This object for method chaining.
     */
    public default MutableTransform2 translateY(int y)
    {
        return this.translate(0, y);
    }

    /**
     * Translate this {@link MutableTransform2} by the provided x- and y-values.
     * @param x The x-value to translate this {@link MutableTransform2} by.
     * @param y The y-value to translate this {@link MutableTransform2} by.
     * @return This object for method chaining.
     */
    public MutableTransform2 translate(int x, int y);

    /**
     * A version of a {@link MutableTransform2} that returns its own types from chainable methods.
     * @param <T> The actual type of the {@link MutableTransform2}.
     */
    public static interface Typed<T extends MutableTransform2> extends MutableTransform2
    {
        @Override
        public T set(Transform2 toCopy);

        @Override
        @SuppressWarnings("unchecked")
        public default T translateX(int x)
        {
            return (T)MutableTransform2.super.translateX(x);
        }

        @Override
        @SuppressWarnings("unchecked")
        public default T translateY(int y)
        {
            return (T)MutableTransform2.super.translateY(y);
        }

        @Override
        public T translate(int x, int y);
    }
}
