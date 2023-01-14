package qub;

public class UIDynamicScaledSize extends UIDynamicSizeDecorator
{
    private double widthScale;
    private double heightScale;

    private UIDynamicScaledSize(UIDynamicSize innerSize)
    {
        super(innerSize);

        this.widthScale = 1.0;
        this.heightScale = 1.0;
    }

    public static UIDynamicScaledSize create(UIDynamicSize innerSize)
    {
        return new UIDynamicScaledSize(innerSize);
    }

    public UIDynamicScaledSize setWidthScale(double widthScale)
    {
        PreCondition.assertGreaterThanOrEqualTo(widthScale, 0, "widthScale");

        this.widthScale = widthScale;

        return this;
    }

    public UIDynamicScaledSize setHeightScale(double heightScale)
    {
        PreCondition.assertGreaterThanOrEqualTo(heightScale, 0, "heightScale");

        this.heightScale = heightScale;

        return this;
    }

    public UIDynamicScaledSize setScale(double scale)
    {
        this.setWidthScale(scale);
        this.setHeightScale(scale);

        return this;
    }

    @Override
    public Size2Integer getSize()
    {
        final Size2Integer innerSize = this.innerSize.getSize();
        return Size2Integer.create()
            .setWidth((int)(innerSize.getWidthAsInt() * this.widthScale))
            .setHeight((int)(innerSize.getHeightAsInt() * this.heightScale));
    }

    @Override
    public Disposable onSizeChanged(Action1<SizeChange> action)
    {
        PreCondition.assertNotNull(action, "action");

        return this.innerSize.onSizeChanged((SizeChange sizeChange) ->
        {
            final SizeChange scaledSizeChange = SizeChange.create()
                .setPreviousWidth((int)(sizeChange.getPreviousWidth() * this.widthScale))
                .setPreviousHeight((int)(sizeChange.getPreviousHeight() * this.heightScale))
                .setNewWidth((int)(sizeChange.getNewWidth() * this.widthScale))
                .setNewHeight((int)(sizeChange.getNewHeight() * this.heightScale));
            action.run(scaledSizeChange);
        });
    }
}
