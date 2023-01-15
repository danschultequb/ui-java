package qub;

public class UIWindowContentAreaDynamicSize2Integer implements DynamicSize2Integer
{
    private final UIWindow uiWindow;

    private UIWindowContentAreaDynamicSize2Integer(UIWindow uiWindow)
    {
        PreCondition.assertNotNull(uiWindow, "uiWindow");

        this.uiWindow = uiWindow;
    }

    public static UIWindowContentAreaDynamicSize2Integer create(UIWindow uiWindow)
    {
        return new UIWindowContentAreaDynamicSize2Integer(uiWindow);
    }

    @Override
    public Disposable onChanged(Action1<SizeChange> action)
    {
        return this.uiWindow.onContentAreaSizeChanged(action);
    }

    @Override
    public int getWidthAsInt()
    {
        return this.uiWindow.getContentAreaWidth();
    }

    @Override
    public int getHeightAsInt()
    {
        return this.uiWindow.getContentAreaHeight();
    }
}
