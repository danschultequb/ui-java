package qub;

public class UIWindowDynamicContentAreaSize implements UIDynamicSize
{
    private final UIWindow uiWindow;

    private UIWindowDynamicContentAreaSize(UIWindow uiWindow)
    {
        PreCondition.assertNotNull(uiWindow, "uiWindow");

        this.uiWindow = uiWindow;
    }

    public static UIWindowDynamicContentAreaSize create(UIWindow uiWindow)
    {
        return new UIWindowDynamicContentAreaSize(uiWindow);
    }

    @Override
    public Size2Integer getSize()
    {
        return this.uiWindow.getContentAreaSize();
    }

    @Override
    public Disposable onSizeChanged(Action1<SizeChange> action)
    {
        return this.uiWindow.onContentAreaSizeChanged(action);
    }
}
