package qub;

public interface JavaAwtFontMetrics
{
    public static java.awt.FontMetrics getDefault()
    {
        final java.awt.Font font = java.awt.Font.decode(null);
        final java.awt.Canvas canvas = new java.awt.Canvas();
        final java.awt.FontMetrics result = canvas.getFontMetrics(font);

        PostCondition.assertNotNull(result, "result");

        return result;
    }
}
