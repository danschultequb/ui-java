package qub;

/**
 * A {@link DynamicSize2Integer} that calculates its width and height by scaling another
 * {@link DynamicSize2Integer}'s width and height.
 */
public class ScaledDynamicSize2Integer extends DynamicSize2IntegerDecorator
{
    private double widthScale;
    private double heightScale;
    private Disposable innerChangedSubscription;
    private RunnableEvent1<SizeChange> changed;

    private ScaledDynamicSize2Integer(DynamicSize2Integer innerSize)
    {
        super(innerSize);

        this.widthScale = 1.0;
        this.heightScale = 1.0;
    }

    /**
     * Create a new {@link ScaledDynamicSize2Integer} that scales the provided
     * {@link DynamicSize2Integer}.
     * @param innerSize The inner {@link DynamicSize2Integer} that the new
     * {@link ScaledDynamicSize2Integer} will scale.
     */
    public static ScaledDynamicSize2Integer create(DynamicSize2Integer innerSize)
    {
        return new ScaledDynamicSize2Integer(innerSize);
    }

    /**
     * Get the width scale factor.
     */
    public double getWidthScale()
    {
        return this.widthScale;
    }

    /**
     * Set the width scale factor.
     * @param widthScale The width scale factor.
     * @return This object for method chaining.
     */
    public ScaledDynamicSize2Integer setWidthScale(double widthScale)
    {
        return this.setScale(widthScale, this.getHeightScale());
    }

    public double getHeightScale()
    {
        return this.heightScale;
    }

    public ScaledDynamicSize2Integer setHeightScale(double heightScale)
    {
        return this.setScale(this.getWidthScale(), heightScale);
    }

    /**
     * Set the scale factors that this {@link ScaledDynamicSize2Integer} uses.
     * @param scale The width and height scale factor.
     * @return This object for method chaining.
     */
    public ScaledDynamicSize2Integer setScale(double scale)
    {
        PreCondition.assertGreaterThanOrEqualTo(scale, 0, "scale");

        return this.setScale(scale, scale);
    }

    /**
     * Set the scale factors that this {@link ScaledDynamicSize2Integer} uses.
     * @param widthScale The width scale factor.
     * @param heightScale The height scale factor.
     * @return This object for method chaining.
     */
    public ScaledDynamicSize2Integer setScale(double widthScale, double heightScale)
    {
        PreCondition.assertGreaterThanOrEqualTo(widthScale, 0, "widthScale");
        PreCondition.assertGreaterThanOrEqualTo(heightScale, 0, "heightScale");

        final int previousScaledWidth = this.getWidthAsInt();
        final int previousScaledHeight = this.getHeightAsInt();

        this.widthScale = widthScale;
        this.heightScale = heightScale;

        if (this.changed != null)
        {
            final int newScaledWidth = this.getWidthAsInt();
            final int newScaledHeight = this.getHeightAsInt();
            if (previousScaledWidth != newScaledWidth ||
                previousScaledHeight != newScaledHeight)
            {
                final SizeChange scaledSizeChange = SizeChange.create()
                    .setPreviousSize(previousScaledWidth, previousScaledHeight)
                    .setNewSize(newScaledWidth, newScaledHeight);
                this.changed.run(scaledSizeChange);
            }
        }
        return this;
    }

    private int getScaledWidth(int width)
    {
        return (int)(this.widthScale * width);
    }

    private int getScaledHeight(int height)
    {
        return (int)(this.heightScale * height);
    }

    @Override
    public int getWidthAsInt()
    {
        return this.getScaledWidth(super.getWidthAsInt());
    }

    @Override
    public int getHeightAsInt()
    {
        return this.getScaledHeight(super.getHeightAsInt());
    }

    @Override
    public Disposable onChanged(Action1<SizeChange> action)
    {
        PreCondition.assertNotNull(action, "action");

        if (this.innerChangedSubscription == null)
        {
            this.changed = Event1.create();

            this.innerChangedSubscription = super.onChanged(this.changed);
        }

        return this.changed.subscribe((SizeChange sizeChange) ->
        {
            final int previousScaledWidth = this.getScaledWidth(sizeChange.getPreviousWidth());
            final int previousScaledHeight = this.getScaledHeight(sizeChange.getPreviousHeight());
            final int newScaledWidth = this.getScaledWidth(sizeChange.getNewWidth());
            final int newScaledHeight = this.getScaledHeight(sizeChange.getNewHeight());
            if (previousScaledWidth != newScaledWidth ||
                previousScaledHeight != newScaledHeight)
            {
                final SizeChange scaledSizeChange = SizeChange.create()
                    .setPreviousSize(previousScaledWidth, previousScaledHeight)
                    .setNewSize(newScaledWidth, newScaledHeight);
                action.run(scaledSizeChange);
            }
        });
    }
}
