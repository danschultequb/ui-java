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
}
