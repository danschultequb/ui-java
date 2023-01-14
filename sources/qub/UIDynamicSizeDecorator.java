package qub;

public abstract class UIDynamicSizeDecorator implements UIDynamicSize
{
    protected final UIDynamicSize innerSize;

    protected UIDynamicSizeDecorator(UIDynamicSize innerSize)
    {
        PreCondition.assertNotNull(innerSize, "innerSize");

        this.innerSize = innerSize;
    }
}
