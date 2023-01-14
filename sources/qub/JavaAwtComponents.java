package qub;

public interface JavaAwtComponents
{
    public static void setBackgroundColor(java.awt.Component component, Color backgroundColor)
    {
        PreCondition.assertNotNull(component, "component");
        PreCondition.assertNotNull(backgroundColor, "backgroundColor");

        component.setBackground(JavaAwtColors.convertColorToJavaAwtColor(backgroundColor));
    }

    public static Color getBackgroundColor(java.awt.Component component)
    {
        PreCondition.assertNotNull(component, "component");

        final java.awt.Color javaAwtColor = component.getBackground();
        final Color result = (javaAwtColor == null ? Color.transparent : JavaAwtColors.convertJavaAwtColorToColor(javaAwtColor));

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    public static Size2Integer getSize(java.awt.Component component)
    {
        PreCondition.assertNotNull(component, "component");

        return Size2.create(component.getWidth(), component.getHeight());
    }

    public static Disposable addComponentListener(java.awt.Component component, java.awt.event.ComponentListener componentListener)
    {
        PreCondition.assertNotNull(component, "component");
        PreCondition.assertNotNull(componentListener, "componentListener");

        component.addComponentListener(componentListener);
        return Disposable.create(() ->
        {
            component.removeComponentListener(componentListener);
        });
    }

    public static Disposable onSizeChanged(java.awt.Component component, Action1<SizeChange> action)
    {
        PreCondition.assertNotNull(component, "component");
        PreCondition.assertNotNull(action, "action");

        final JavaComponentListener componentListener = JavaComponentListener.create()
            .setGetCurrentSizeFunction(() -> JavaAwtComponents.getSize(component))
            .setSizeChangedAction(action);

        return JavaAwtComponents.addComponentListener(component, componentListener);
    }
}
