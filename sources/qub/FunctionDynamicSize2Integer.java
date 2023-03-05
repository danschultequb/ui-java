package qub;

/**
 * A {@link DynamicSize2Integer} implementation that can have its functions configured at runtime.
 */
public class FunctionDynamicSize2Integer extends DynamicSize2Integer.Base
{
    private Function0<Integer> getWidthFunction;
    private Function0<Integer> getHeightFunction;
    private Function1<Action1<SizeChange>,Disposable> onChangedFunction;

    private FunctionDynamicSize2Integer()
    {
    }

    /**
     * Create a new {@link FunctionDynamicSize2Integer}.
     */
    public static FunctionDynamicSize2Integer create()
    {
        return new FunctionDynamicSize2Integer();
    }

    /**
     * Set the {@link Function0} that will be invoked when this {@link DynamicSize2Integer}'s
     * getWidth() function is invoked.
     * @param getWidthFunction The {@link Function0} that will be invoked when this
     * {@link DynamicSize2Integer}'s getWidth() function is invoked.
     * @return This object for method chaining.
     */
    public FunctionDynamicSize2Integer setGetWidthFunction(Function0<Integer> getWidthFunction)
    {
        PreCondition.assertNotNull(getWidthFunction, "getWidthFunction");

        this.getWidthFunction = getWidthFunction;

        return this;
    }

    /**
     * Set the {@link Function0} that will be invoked when this {@link DynamicSize2Integer}'s
     * getHeight() function is invoked.
     * @param getHeightFunction The {@link Function0} that will be invoked when this
     * {@link DynamicSize2Integer}'s getHeight() function is invoked.
     * @return This object for method chaining.
     */
    public FunctionDynamicSize2Integer setGetHeightFunction(Function0<Integer> getHeightFunction)
    {
        PreCondition.assertNotNull(getHeightFunction, "getHeightFunction");

        this.getHeightFunction = getHeightFunction;

        return this;
    }

    /**
     * Set the {@link Function0} that will be invoked when this {@link DynamicSize2Integer}'s
     * onChanged() function is invoked.
     * @param onChangedFunction The {@link Function0} that will be invoked when this
     * {@link DynamicSize2Integer}'s onChanged() function is invoked.
     * @return This object for method chaining.
     */
    public FunctionDynamicSize2Integer setOnChangedFunction(Function1<Action1<SizeChange>,Disposable> onChangedFunction)
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
    public Integer getWidth()
    {
        PreCondition.assertNotNull(this.getWidthFunction, "this.getWidthFunction");

        return this.getWidthFunction.run();
    }

    @Override
    public int getWidthAsInt()
    {
        return this.getWidth();
    }

    @Override
    public Integer getHeight()
    {
        PreCondition.assertNotNull(this.getHeightFunction, "this.getHeightFunction");

        return this.getHeightFunction.run();
    }

    @Override
    public int getHeightAsInt()
    {
        return this.getHeight();
    }
}
