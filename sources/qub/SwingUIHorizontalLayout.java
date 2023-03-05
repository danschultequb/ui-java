package qub;

public class SwingUIHorizontalLayout extends JComponentUIElement.Base<SwingUIHorizontalLayout> implements UIHorizontalLayout.Typed<SwingUIHorizontalLayout>
{
    private final javax.swing.JPanel jPanel;
    private float awtVerticalAlignment;

    private SwingUIHorizontalLayout(SwingUI ui)
    {
        super(ui);

        this.jPanel = new javax.swing.JPanel();
        this.jPanel.setLayout(new javax.swing.BoxLayout(this.jPanel, javax.swing.BoxLayout.X_AXIS));
        this.awtVerticalAlignment = java.awt.Component.TOP_ALIGNMENT;
    }

    public static SwingUIHorizontalLayout create(SwingUI ui)
    {
        return new SwingUIHorizontalLayout(ui);
    }

    @Override
    public SwingUIHorizontalLayout add(UIElement uiElement)
    {
        PreCondition.assertNotNull(uiElement, "uiElement");
        PreCondition.assertInstanceOf(uiElement, JComponentUIElement.class, "uiElement");

        final JComponentUIElement jComponentUIElement = (JComponentUIElement)uiElement;
        final javax.swing.JComponent jComponent = jComponentUIElement.getJComponent();
        this.jPanel.add(jComponent);

        jComponent.setAlignmentY(this.awtVerticalAlignment);

        return this;
    }

    @Override
    public VerticalAlignment getVerticalAlignment()
    {
        VerticalAlignment result;
        if (this.awtVerticalAlignment == java.awt.Component.BOTTOM_ALIGNMENT)
        {
            result = VerticalAlignment.Bottom;
        }
        else if (this.awtVerticalAlignment == java.awt.Component.CENTER_ALIGNMENT)
        {
            result = VerticalAlignment.Center;
        }
        else
        {
            result = VerticalAlignment.Top;
        }

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    @Override
    public UIHorizontalLayout setVerticalAlignment(VerticalAlignment verticalAlignment)
    {
        PreCondition.assertNotNull(verticalAlignment, "verticalAlignment");

        float awtVerticalAlignment;
        switch (verticalAlignment)
        {
            case Bottom -> awtVerticalAlignment = java.awt.Component.BOTTOM_ALIGNMENT;
            case Center -> awtVerticalAlignment = java.awt.Component.CENTER_ALIGNMENT;
            default -> awtVerticalAlignment = java.awt.Component.TOP_ALIGNMENT;
        }

        if (this.awtVerticalAlignment != awtVerticalAlignment)
        {
            this.awtVerticalAlignment = awtVerticalAlignment;

            for (final java.awt.Component component : this.jPanel.getComponents())
            {
                if (component instanceof javax.swing.JComponent jComponent)
                {
                    jComponent.setAlignmentY(this.awtVerticalAlignment);
                }
            }
        }

        return this;
    }

    @Override
    public javax.swing.JComponent getJComponent()
    {
        return this.jPanel;
    }
}
