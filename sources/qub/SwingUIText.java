package qub;

public class SwingUIText implements UIText.Typed<SwingUIText>, JComponentUIElement.Typed<SwingUIText>
{
    private final SwingUI ui;
    private final javax.swing.JLabel label;

    private SwingUIText(SwingUI ui)
    {
        PreCondition.assertNotNull(ui, "ui");

        this.ui = ui;
        this.label = new javax.swing.JLabel();
    }

    public static SwingUIText create(SwingUI ui)
    {
        return new SwingUIText(ui);
    }

    @Override
    public SwingUIText setBackgroundColor(Color backgroundColor)
    {
        return JComponentUIElement.Typed.super.setBackgroundColor(backgroundColor);
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
    public javax.swing.JComponent getJComponent()
    {
        return this.label;
    }
}
