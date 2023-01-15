package qub;

public class ScaledDynamicSize2Integer extends DynamicSize2IntegerDecorator
{
    private double widthScale;
    private double heightScale;

    private ScaledDynamicSize2Integer(DynamicSize2Integer innerSize)
    {
        super(innerSize);

        this.widthScale = 1.0;
        this.heightScale = 1.0;
    }

    public static ScaledDynamicSize2Integer create(DynamicSize2Integer innerSize)
    {
        return new ScaledDynamicSize2Integer(innerSize);
    }

    public ScaledDynamicSize2Integer setWidthScale(double widthScale)
    {
        PreCondition.assertGreaterThanOrEqualTo(widthScale, 0, "widthScale");

        this.widthScale = widthScale;

        return this;
    }

    public ScaledDynamicSize2Integer setHeightScale(double heightScale)
    {
        PreCondition.assertGreaterThanOrEqualTo(heightScale, 0, "heightScale");

        this.heightScale = heightScale;

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

        return super.onChanged((SizeChange sizeChange) ->
        {
            action.run(SizeChange.create()
                .setPreviousWidth(this.getScaledWidth(sizeChange.getPreviousWidth()))
                .setPreviousHeight(this.getScaledHeight(sizeChange.getPreviousHeight()))
                .setNewWidth(this.getScaledWidth(sizeChange.getNewWidth()))
                .setNewHeight(this.getScaledHeight(sizeChange.getNewHeight())));
        });
    }
}
