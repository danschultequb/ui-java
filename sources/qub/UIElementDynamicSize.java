package qub;

public class UIElementDynamicSize implements UIDynamicSize
{
    private final UIElement uiElement;

    private UIElementDynamicSize(UIElement uiElement)
    {
        PreCondition.assertNotNull(uiElement, "uiElement");

        this.uiElement = uiElement;
    }

    public static UIElementDynamicSize create(UIElement uiElement)
    {
        return new UIElementDynamicSize(uiElement);
    }

    @Override
    public Size2Integer getSize()
    {
        return this.uiElement.getSize();
    }

    @Override
    public Disposable onSizeChanged(Action1<SizeChange> action)
    {
        return this.uiElement.onSizeChanged(action);
    }
}
