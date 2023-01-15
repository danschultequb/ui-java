package qub;

public class BasicDynamicSize2Integer extends DynamicSize2Integer.Base
{
    private Function0<Integer> getWidthFunction;
    private Function0<Integer> getHeightFunction;
    private Function1<Action1<SizeChange>,Disposable> onChangedFunction;

    private BasicDynamicSize2Integer()
    {
    }

    public static BasicDynamicSize2Integer create()
    {
        return new BasicDynamicSize2Integer();
    }

    public BasicDynamicSize2Integer setGetWidthFunction(Function0<Integer> getWidthFunction)
    {
        PreCondition.assertNotNull(getWidthFunction, "getWidthFunction");

        this.getWidthFunction = getWidthFunction;

        return this;
    }

    public BasicDynamicSize2Integer setGetHeightFunction(Function0<Integer> getHeightFunction)
    {
        PreCondition.assertNotNull(getHeightFunction, "getHeightFunction");

        this.getHeightFunction = getHeightFunction;

        return this;
    }

    public BasicDynamicSize2Integer setOnChangedFunction(Function1<Action1<SizeChange>,Disposable> onChangedFunction)
    {
        PreCondition.assertNotNull(onChangedFunction, "onChangedFunction");

        this.onChangedFunction = onChangedFunction;

        return this;
    }

    @Override
    public Disposable onChanged(Action1<SizeChange> action)
    {
        PreCondition.assertNotNull(action, "action");
        PreCondition.assertNotNull(this.onChangedFunction, "this.onChangedFunction");

        return this.onChangedFunction.run(action);
    }

    @Override
    public int getWidthAsInt()
    {
        PreCondition.assertNotNull(this.getWidthFunction, "this.getWidthFunction");

        return this.getWidthFunction.run();
    }

    @Override
    public int getHeightAsInt()
    {
        PreCondition.assertNotNull(this.getHeightFunction, "this.getHeightFunction");

        return this.getHeightFunction.run();
    }
}
