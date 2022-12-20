package qub;

public abstract class UIWindowBase<T extends UIWindow> implements UIWindow
{
    @Override
    public abstract T setBackgroundColor(Color backgroundColor);

    @Override
    public abstract T setTitle(String title);
}
