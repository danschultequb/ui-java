package qub;

public class UIElementDynamicSize2Integer implements DynamicSize2Integer
{
    private final UIElement uiElement;

    private UIElementDynamicSize2Integer(UIElement uiElement)
    {
        PreCondition.assertNotNull(uiElement, "uiElement");

        this.uiElement = uiElement;
    }

    public static UIElementDynamicSize2Integer create(UIElement uiElement)
    {
        return new UIElementDynamicSize2Integer(uiElement);
    }

    @Override
    public Disposable onChanged(Action1<SizeChange> action)
    {
        return this.uiElement.onSizeChanged(action);
    }

    @Override
    public int getWidthAsInt()
    {
        return this.uiElement.getWidth();
    }

    @Override
    public int getHeightAsInt()
    {
        return this.uiElement.getHeight();
    }
}
