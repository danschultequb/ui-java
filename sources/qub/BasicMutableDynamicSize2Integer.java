package qub;

/**
 * A basic {@link MutableDynamicSize2Integer} implementation.
 */
public class BasicMutableDynamicSize2Integer extends MutableDynamicSize2Integer.Base<BasicMutableDynamicSize2Integer>
{
    private int width;
    private int height;
    private RunnableEvent1<SizeChange> changed;

    private BasicMutableDynamicSize2Integer(int width, int height)
    {
        this.set(width, height);
    }

    public static BasicMutableDynamicSize2Integer create()
    {
        return BasicMutableDynamicSize2Integer.create(0, 0);
    }

    public static BasicMutableDynamicSize2Integer create(int width, int height)
    {
        return new BasicMutableDynamicSize2Integer(width, height);
    }

    public static BasicMutableDynamicSize2Integer create(Size2<Integer> size)
    {
        PreCondition.assertNotNull(size, "size");

        return BasicMutableDynamicSize2Integer.create(size.getWidth(), size.getHeight());
    }

    @Override
    public Disposable onChanged(Action1<SizeChange> action)
    {
        PreCondition.assertNotNull(action, "action");

        if (this.changed == null)
        {
            this.changed = RunnableEvent1.create();
        }
        return this.changed.subscribe(action);
    }

    @Override
    public int getWidthAsInt()
    {
        return this.width;
    }

    @Override
    public int getHeightAsInt()
    {
        return this.height;
    }

    @Override
    public BasicMutableDynamicSize2Integer set(int width, int height)
    {
        PreCondition.assertGreaterThanOrEqualTo(width, 0, "width");
        PreCondition.assertGreaterThanOrEqualTo(height, 0, "height");

        if (this.width != width || this.height != height)
        {
            final int previousWidth = this.width;
            final int previousHeight = this.height;

            this.width = width;
            this.height = height;

            if (this.changed != null)
            {
                this.changed.run(SizeChange.create()
                    .setPreviousSize(previousWidth, previousHeight)
                    .setNewSize(width, height));
            }
        }
        return this;
    }
}
