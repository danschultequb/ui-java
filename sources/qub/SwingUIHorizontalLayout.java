package qub;

public class SwingUIHorizontalLayout implements UIHorizontalLayout, JComponentUIElement
{
    private final SwingUI ui;
    private final javax.swing.JPanel jPanel;
    private final List<UIElement> childUIElements;

    private SwingUIHorizontalLayout(SwingUI ui)
    {
        PreCondition.assertNotNull(ui, "ui");

        this.ui = ui;
        this.jPanel = new javax.swing.JPanel();
        this.jPanel.setLayout(new javax.swing.BoxLayout(this.jPanel, javax.swing.BoxLayout.X_AXIS));
        this.childUIElements = List.create();
    }

    public static SwingUIHorizontalLayout create(SwingUI ui)
    {
        return new SwingUIHorizontalLayout(ui);
    }

    @Override
    public SwingUIHorizontalLayout setBackgroundColor(Color backgroundColor)
    {
        return this.ui.setBackgroundColor(this, backgroundColor);
    }

    @Override
    public Color getBackgroundColor()
    {
        return this.ui.getBackgroundColor(this);
    }

    @Override
    public SwingUIHorizontalLayout add(UIElement uiElement)
    {
        PreCondition.assertNotNull(uiElement, "uiElement");
        PreCondition.assertInstanceOf(uiElement, JComponentUIElement.class, "uiElement");

        final JComponentUIElement jComponentUIElement = (JComponentUIElement)uiElement;
        final javax.swing.JComponent jComponent = jComponentUIElement.getJComponent();
        this.jPanel.add(jComponent);
        this.childUIElements.add(uiElement);

        return this;
    }

    @Override
    public SwingUIHorizontalLayout addAll(UIElement... uiElements)
    {
        return (SwingUIHorizontalLayout)UIHorizontalLayout.super.addAll(uiElements);
    }

    @Override
    public SwingUIHorizontalLayout addAll(Iterable<? extends UIElement> uiElements)
    {
        return (SwingUIHorizontalLayout)UIHorizontalLayout.super.addAll(uiElements);
    }

    @Override
    public javax.swing.JComponent getJComponent()
    {
        return this.jPanel;
    }
}
