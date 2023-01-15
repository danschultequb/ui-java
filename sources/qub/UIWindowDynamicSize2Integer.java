package qub;

public class UIWindowDynamicSize2Integer implements DynamicSize2Integer
{
    private final UIWindow uiWindow;

    private UIWindowDynamicSize2Integer(UIWindow uiWindow)
    {
        PreCondition.assertNotNull(uiWindow, "uiWindow");

        this.uiWindow = uiWindow;
    }

    public static UIWindowDynamicSize2Integer create(UIWindow uiWindow)
    {
        return new UIWindowDynamicSize2Integer(uiWindow);
    }

    @Override
    public Disposable onChanged(Action1<SizeChange> action)
    {
        return this.uiWindow.onSizeChanged(action);
    }

    @Override
    public int getWidthAsInt()
    {
        return this.uiWindow.getWidth();
    }

    @Override
    public int getHeightAsInt()
    {
        return this.uiWindow.getHeight();
    }
}
