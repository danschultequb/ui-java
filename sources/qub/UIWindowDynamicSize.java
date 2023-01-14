package qub;

public class UIWindowDynamicSize implements UIDynamicSize
{
    private final UIWindow uiWindow;

    private UIWindowDynamicSize(UIWindow uiWindow)
    {
        PreCondition.assertNotNull(uiWindow, "uiWindow");

        this.uiWindow = uiWindow;
    }

    public static UIWindowDynamicSize create(UIWindow uiWindow)
    {
        return new UIWindowDynamicSize(uiWindow);
    }

    @Override
    public Size2Integer getSize()
    {
        return this.uiWindow.getSize();
    }

    @Override
    public Disposable onSizeChanged(Action1<SizeChange> action)
    {
        return this.uiWindow.onSizeChanged(action);
    }
}
