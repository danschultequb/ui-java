package qub;

public class BasicUIDynamicSize implements UIDynamicSize
{
    private Function0<Size2Integer> getSizeFunction;
    private Function1<Action1<SizeChange>,Disposable> onSizeChangedFunction;

    private BasicUIDynamicSize()
    {
    }

    public static BasicUIDynamicSize create()
    {
        return new BasicUIDynamicSize();
    }

    public BasicUIDynamicSize setGetSizeFunction(Function0<Size2Integer> getSizeFunction)
    {
        PreCondition.assertNotNull(getSizeFunction, "getSizeFunction");

        this.getSizeFunction = getSizeFunction;

        return this;
    }

    public BasicUIDynamicSize setOnSizeChangedFunction(Function1<Action1<SizeChange>,Disposable> onSizeChangedFunction)
    {
        PreCondition.assertNotNull(onSizeChangedFunction, "onSizeChangedFunction");

        this.onSizeChangedFunction = onSizeChangedFunction;

        return this;
    }

    @Override
    public Size2Integer getSize()
    {
        PreCondition.assertNotNull(this.getSizeFunction, "this.getSizeFunction");

        return this.getSizeFunction.run();
    }

    @Override
    public Disposable onSizeChanged(Action1<SizeChange> action)
    {
        PreCondition.assertNotNull(action, "action");
        PreCondition.assertNotNull(this.onSizeChangedFunction, "this.onSizeChangedFunction");

        return this.onSizeChangedFunction.run(action);
    }
}
