package qub;

public class SwingUIHorizontalLayout extends JComponentUIElement.Base<SwingUIHorizontalLayout> implements UIHorizontalLayout.Typed<SwingUIHorizontalLayout>
{
    private final javax.swing.JPanel jPanel;

    private SwingUIHorizontalLayout(SwingUI ui)
    {
        super(ui);

        this.jPanel = new javax.swing.JPanel();
        this.jPanel.setLayout(new javax.swing.BoxLayout(this.jPanel, javax.swing.BoxLayout.X_AXIS));
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

        jComponent.setAlignmentY(java.awt.Component.TOP_ALIGNMENT);

        return this;
    }

    @Override
    public javax.swing.JComponent getJComponent()
    {
        return this.jPanel;
    }
}
