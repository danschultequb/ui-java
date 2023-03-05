package qub;

public class SwingUIText extends JComponentUIElement.Base<SwingUIText> implements UIText.Typed<SwingUIText>
{
    private final javax.swing.JLabel label;

    private SwingUIText(SwingUI ui)
    {
        super(ui);

        this.label = new javax.swing.JLabel();
    }

    public static SwingUIText create(SwingUI ui)
    {
        return new SwingUIText(ui);
    }

    @Override
    public String getText()
    {
        return this.label.getText();
    }

    @Override
    public SwingUIText setText(String text)
    {
        PreCondition.assertNotNull(text, "text");

        this.label.setText(text);

        return this;
    }

    @Override
    public SwingUIText setAlignment(HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment)
    {
        PreCondition.assertNotNull(horizontalAlignment, "horizontalAlignment");
        PreCondition.assertNotNull(verticalAlignment, "verticalAlignment");

        int swingHorizontalAlignment = javax.swing.SwingConstants.LEFT;
        switch (horizontalAlignment)
        {
            case Center -> swingHorizontalAlignment = javax.swing.SwingConstants.CENTER;
            case Right -> swingHorizontalAlignment = javax.swing.SwingConstants.RIGHT;
        }
        this.label.setHorizontalAlignment(swingHorizontalAlignment);

        int swingVerticalAlignment = javax.swing.SwingConstants.TOP;
        switch (verticalAlignment)
        {
            case Center -> swingVerticalAlignment = javax.swing.SwingConstants.CENTER;
            case Bottom -> swingVerticalAlignment = javax.swing.SwingConstants.BOTTOM;
        }
        this.label.setVerticalAlignment(swingVerticalAlignment);

        return this;
    }

    @Override
    public HorizontalAlignment getHorizontalAlignment()
    {
        HorizontalAlignment result = HorizontalAlignment.Left;
        switch (this.label.getHorizontalAlignment())
        {
            case javax.swing.SwingConstants.CENTER -> result = HorizontalAlignment.Center;
            case javax.swing.SwingConstants.RIGHT -> result = HorizontalAlignment.Right;
        }

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    @Override
    public VerticalAlignment getVerticalAlignment()
    {
        VerticalAlignment result = VerticalAlignment.Top;
        switch (this.label.getVerticalAlignment())
        {
            case javax.swing.SwingConstants.CENTER -> result = VerticalAlignment.Center;
            case javax.swing.SwingConstants.BOTTOM -> result = VerticalAlignment.Bottom;
        }

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    @Override
    public javax.swing.JComponent getJComponent()
    {
        return this.label;
    }
}
