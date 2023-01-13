package qub;

public class SwingUIVerticalLayout implements UIVerticalLayout.Typed<SwingUIVerticalLayout>, JComponentUIElement.Typed<SwingUIVerticalLayout>
{
    private final SwingUI ui;
    private final javax.swing.JPanel jPanel;
    private final List<UIElement> childUIElements;

    private SwingUIVerticalLayout(SwingUI ui)
    {
        PreCondition.assertNotNull(ui, "ui");

        this.ui = ui;
        this.jPanel = new javax.swing.JPanel();
        this.jPanel.setLayout(new javax.swing.BoxLayout(this.jPanel, javax.swing.BoxLayout.Y_AXIS));
        this.childUIElements = List.create();
    }

    public static SwingUIVerticalLayout create(SwingUI ui)
    {
        return new SwingUIVerticalLayout(ui);
    }

    @Override
    public SwingUIVerticalLayout setBackgroundColor(Color backgroundColor)
    {
        return JComponentUIElement.Typed.super.setBackgroundColor(backgroundColor);
    }

    @Override
    public SwingUIVerticalLayout add(UIElement uiElement)
    {
        PreCondition.assertNotNull(uiElement, "uiElement");
        PreCondition.assertInstanceOf(uiElement, JComponentUIElement.class, "uiElement");

        final JComponentUIElement jComponentUIElement = (JComponentUIElement)uiElement;
        final javax.swing.JComponent jComponent = jComponentUIElement.getJComponent();
        this.jPanel.add(jComponent);
        this.childUIElements.add(uiElement);

        jComponent.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        return this;
    }

    @Override
    public javax.swing.JComponent getJComponent()
    {
        return this.jPanel;
    }
}
