package qub;

public interface MutableDynamicSize2Integer extends DynamicSize2Integer, MutableSize2Integer
{
    public static MutableDynamicSize2Integer create()
    {
        return BasicMutableDynamicSize2Integer.create();
    }

    public static MutableDynamicSize2Integer create(int width, int height)
    {
        return BasicMutableDynamicSize2Integer.create(width, height);
    }

    public static MutableDynamicSize2Integer create(Size2<Integer> size)
    {
        return BasicMutableDynamicSize2Integer.create(size);
    }

    @Override
    public default MutableDynamicSize2Integer setWidth(Integer width)
    {
        return (MutableDynamicSize2Integer)MutableSize2Integer.super.setWidth(width);
    }

    @Override
    public default MutableDynamicSize2Integer setWidth(int width)
    {
        return (MutableDynamicSize2Integer)MutableSize2Integer.super.setWidth(width);
    }

    @Override
    public default MutableDynamicSize2Integer setHeight(Integer height)
    {
        return (MutableDynamicSize2Integer)MutableSize2Integer.super.setHeight(height);
    }

    @Override
    public default MutableDynamicSize2Integer setHeight(int height)
    {
        return (MutableDynamicSize2Integer)MutableSize2Integer.super.setHeight(height);
    }

    @Override
    public default MutableDynamicSize2Integer set(Integer width, Integer height)
    {
        return (MutableDynamicSize2Integer)MutableSize2Integer.super.set(width, height);
    }

    @Override
    public MutableDynamicSize2Integer set(int width, int height);

    /**
     * A version of a {@link MutableDynamicSize2Integer} that returns its own type from chainable
     * methods.
     * @param <T> The actual type of the {@link MutableDynamicSize2Integer}.
     */
    public static interface Typed<T extends MutableDynamicSize2Integer> extends MutableDynamicSize2Integer
    {
        @Override
        @SuppressWarnings("unchecked")
        public default T setWidth(Integer width)
        {
            return (T)MutableDynamicSize2Integer.super.setWidth(width);
        }

        @Override
        @SuppressWarnings("unchecked")
        public default T setWidth(int width)
        {
            return (T)MutableDynamicSize2Integer.super.setWidth(width);
        }

        @Override
        @SuppressWarnings("unchecked")
        public default T setHeight(Integer height)
        {
            return (T)MutableDynamicSize2Integer.super.setHeight(height);
        }

        @Override
        @SuppressWarnings("unchecked")
        public default T setHeight(int height)
        {
            return (T)MutableDynamicSize2Integer.super.setHeight(height);
        }

        @Override
        @SuppressWarnings("unchecked")
        public default T set(Integer width, Integer height)
        {
            return (T)MutableDynamicSize2Integer.super.set(width, height);
        }

        @Override
        public T set(int width, int height);
    }

    /**
     * An abstract base class implementation of {@link MutableDynamicSize2Integer} that contains common
     * properties and functions for all {@link MutableDynamicSize2Integer} types.
     */
    public static abstract class Base<T extends MutableDynamicSize2Integer> extends DynamicSize2Integer.Base implements MutableDynamicSize2Integer.Typed<T>
    {
    }
}
