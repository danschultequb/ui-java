package qub;

public class SwingUIVerticalLayout extends JComponentUIElement.Base<SwingUIVerticalLayout> implements UIVerticalLayout.Typed<SwingUIVerticalLayout>
{
    private final javax.swing.JPanel jPanel;

    private SwingUIVerticalLayout(SwingUI ui)
    {
        super(ui);

        this.jPanel = new javax.swing.JPanel();
        this.jPanel.setLayout(new javax.swing.BoxLayout(this.jPanel, javax.swing.BoxLayout.Y_AXIS));
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

        jComponent.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        return this;
    }

    @Override
    public javax.swing.JComponent getJComponent()
    {
        return this.jPanel;
    }
}
