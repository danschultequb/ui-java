package qub;

public abstract class UIBase<UIType extends UI> implements UI
{
    private final MutableMap<Class<? extends UIElement>,Function0<? extends UIElement>> uiCreators;

    private int horizontalPixelsPerInch;
    private int verticalPixelsPerInch;

    protected UIBase()
    {
        this.uiCreators = Map.create();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <U extends UIElement, T extends U> UIType setCreator(Iterable<Class<? extends U>> uiElementTypes, Function0<T> uiElementCreator)
    {
        return (UIType)UI.super.setCreator(uiElementTypes, uiElementCreator);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <U extends UIElement, T extends U> UIType setCreator(Class<? extends U> uiElementType, Function0<T> uiElementCreator)
    {
        PreCondition.assertNotNull(uiElementType, "uiElementType");
        PreCondition.assertNotNull(uiElementCreator, "uiElementCreator");

        this.uiCreators.set(uiElementType, uiElementCreator);

        return (UIType)this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends UIElement> Result<T> create(Class<T> uiElementType)
    {
        PreCondition.assertNotNull(uiElementType, "uiElementType");

        return Result.create(() ->
        {
            final Function0<? extends UIElement> creatorFunction = this.uiCreators.get(uiElementType)
                .convertError(NotFoundException.class, () -> new NotFoundException("No " + Types.getTypeName(UIElement.class) + " creator function registered for " + Types.getTypeName(UIElement.class) + " type " + Types.getFullTypeName(uiElementType) + "."))
                .await();
            final UIElement result = creatorFunction.run();

            PostCondition.assertInstanceOf(result, uiElementType, "result");

            return (T)result;
        });
    }

    @SuppressWarnings("unchecked")
    public UIType setPixelsPerInch(int pixelsPerInch)
    {
        PreCondition.assertGreaterThan(pixelsPerInch, 0, "pixelsPerInch");

        this.setHorizontalPixelsPerInch(pixelsPerInch);
        this.setVerticalPixelsPerInch(pixelsPerInch);

        return (UIType)this;
    }

    @SuppressWarnings("unchecked")
    public UIType setHorizontalPixelsPerInch(int horizontalPixelsPerInch)
    {
        PreCondition.assertGreaterThan(horizontalPixelsPerInch, 0, "horizontalPixelsPerInch");

        this.horizontalPixelsPerInch = horizontalPixelsPerInch;

        return (UIType)this;
    }

    @SuppressWarnings("unchecked")
    public UIType setVerticalPixelsPerInch(int verticalPixelsPerInch)
    {
        PreCondition.assertGreaterThan(verticalPixelsPerInch, 0, "verticalPixelsPerInch");

        this.verticalPixelsPerInch = verticalPixelsPerInch;

        return (UIType)this;
    }

    @Override
    public int convertHorizontalDistanceToPixels(Distance horizontalDistance)
    {
        PreCondition.assertNotNull(horizontalDistance, "width");

        final double horizontalDistanceInInches = horizontalDistance.toInches().getValue();
        final int result = (int)(horizontalDistanceInInches * this.horizontalPixelsPerInch);

        return result;
    }

    @Override
    public int convertVerticalDistanceToPixels(Distance verticalDistance)
    {
        PreCondition.assertNotNull(verticalDistance, "height");

        final double verticalDistanceInInches = verticalDistance.toInches().getValue();
        final int result = (int)(verticalDistanceInInches * this.verticalPixelsPerInch);

        return result;
    }

    @Override
    public Distance convertHorizontalPixelsToDistance(int horizontalPixels)
    {
        final double horizontalDistanceInInches = horizontalPixels / (double)this.horizontalPixelsPerInch;
        final Distance result = Distance.inches(horizontalDistanceInInches);

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    @Override
    public Distance convertVerticalPixelsToDistance(int verticalPixels)
    {
        final double verticalDistanceInInches = verticalPixels / (double)this.verticalPixelsPerInch;
        final Distance result = Distance.inches(verticalDistanceInInches);

        PostCondition.assertNotNull(result, "result");

        return result;
    }
}
