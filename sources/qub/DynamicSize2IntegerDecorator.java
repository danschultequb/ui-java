package qub;

public abstract class DynamicSize2IntegerDecorator extends DynamicSize2Integer.Base
{
    private final DynamicSize2Integer innerSize;

    protected DynamicSize2IntegerDecorator(DynamicSize2Integer innerSize)
    {
        PreCondition.assertNotNull(innerSize, "innerSize");

        this.innerSize = innerSize;
    }

    @Override
    public Disposable onChanged(Action1<SizeChange> action)
    {
        return this.innerSize.onChanged(action);
    }

    @Override
    public int getWidthAsInt()
    {
        return this.innerSize.getWidthAsInt();
    }

    @Override
    public int getHeightAsInt()
    {
        return this.innerSize.getHeightAsInt();
    }
}
