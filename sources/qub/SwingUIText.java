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
    public javax.swing.JComponent getJComponent()
    {
        return this.label;
    }
}
