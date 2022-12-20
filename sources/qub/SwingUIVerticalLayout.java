package qub;

public class SwingUIVerticalLayout implements UIVerticalLayout, JComponentUIElement
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
        return this.ui.setBackgroundColor(this, backgroundColor);
    }

    @Override
    public Color getBackgroundColor()
    {
        return this.ui.getBackgroundColor(this);
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

        return this;
    }

    @Override
    public SwingUIVerticalLayout addAll(UIElement... uiElements)
    {
        return (SwingUIVerticalLayout)UIVerticalLayout.super.addAll(uiElements);
    }

    @Override
    public SwingUIVerticalLayout addAll(Iterable<? extends UIElement> uiElements)
    {
        return (SwingUIVerticalLayout)UIVerticalLayout.super.addAll(uiElements);
    }

    @Override
    public javax.swing.JComponent getJComponent()
    {
        return this.jPanel;
    }
}
