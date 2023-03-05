package qub;

public class SwingUIVerticalLayout extends JComponentUIElement.Base<SwingUIVerticalLayout> implements UIVerticalLayout.Typed<SwingUIVerticalLayout>
{
    private final javax.swing.JPanel jPanel;
    private float awtHorizontalAlignment;

    private SwingUIVerticalLayout(SwingUI ui)
    {
        super(ui);

        this.jPanel = new javax.swing.JPanel();
        this.jPanel.setLayout(new javax.swing.BoxLayout(this.jPanel, javax.swing.BoxLayout.Y_AXIS));

        this.awtHorizontalAlignment = java.awt.Component.LEFT_ALIGNMENT;
    }

    public static SwingUIVerticalLayout create(SwingUI ui)
    {
        return new SwingUIVerticalLayout(ui);
    }

    @Override
    public SwingUIVerticalLayout add(UIElement uiElement)
    {
        PreCondition.assertNotNull(uiElement, "uiElement");
        PreCondition.assertInstanceOf(uiElement, JComponentUIElement.class, "uiElement");

        final JComponentUIElement jComponentUIElement = (JComponentUIElement)uiElement;
        final javax.swing.JComponent jComponent = jComponentUIElement.getJComponent();
        this.jPanel.add(jComponent);

        jComponent.setAlignmentX(this.awtHorizontalAlignment);

        return this;
    }

    @Override
    public SwingUIVerticalLayout setHorizontalAlignment(HorizontalAlignment horizontalAlignment)
    {
        PreCondition.assertNotNull(horizontalAlignment, "horizontalAlignment");

        float awtHorizontalAlignment;
        switch (horizontalAlignment)
        {
            case Right -> awtHorizontalAlignment = java.awt.Component.RIGHT_ALIGNMENT;
            case Center -> awtHorizontalAlignment = java.awt.Component.CENTER_ALIGNMENT;
            default -> awtHorizontalAlignment = java.awt.Component.LEFT_ALIGNMENT;
        }

        if (this.awtHorizontalAlignment != awtHorizontalAlignment)
        {
            this.awtHorizontalAlignment = awtHorizontalAlignment;

            for (final java.awt.Component component : this.jPanel.getComponents())
            {
                if (component instanceof javax.swing.JComponent jComponent)
                {
                    jComponent.setAlignmentX(this.awtHorizontalAlignment);
                }
            }
        }

        return this;
    }

    @Override
    public HorizontalAlignment getHorizontalAlignment()
    {
        HorizontalAlignment result;
        if (this.awtHorizontalAlignment == java.awt.Component.RIGHT_ALIGNMENT)
        {
            result = HorizontalAlignment.Right;
        }
        else if (this.awtHorizontalAlignment == java.awt.Component.CENTER_ALIGNMENT)
        {
            result = HorizontalAlignment.Center;
        }
        else
        {
            result = HorizontalAlignment.Left;
        }

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    @Override
    public javax.swing.JComponent getJComponent()
    {
        return this.jPanel;
    }
}
