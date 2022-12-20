package qub;

public class SwingUIButton implements UIButton, JComponentUIElement
{
    private final SwingUI ui;
    private final javax.swing.JButton button;

    private SwingUIButton(SwingUI ui)
    {
        PreCondition.assertNotNull(ui, "ui");

        this.ui = ui;
        this.button = new javax.swing.JButton();
    }

    public static SwingUIButton create(SwingUI ui)
    {
        return new SwingUIButton(ui);
    }

    @Override
    public javax.swing.JComponent getJComponent()
    {
        return this.button;
    }

    @Override
    public String getText()
    {
        return this.button.getText();
    }

    @Override
    public SwingUIButton setText(String text)
    {
        PreCondition.assertNotNull(text, "text");

        this.button.setText(text);

        return this;
    }

    /**
     * Initiate a click on this {@link SwingUIButton}.
     */
    public void click()
    {
        this.button.doClick();
    }

    @Override
    public Disposable onClick(Action0 callback)
    {
        PreCondition.assertNotNull(callback, "callback");

        final java.awt.event.ActionListener actionListener = (java.awt.event.ActionEvent e) -> callback.run();
        this.button.addActionListener(actionListener);
        return Disposable.create(() ->
        {
            this.button.removeActionListener(actionListener);
        });
    }

    @Override
    public SwingUIButton setBackgroundColor(Color backgroundColor)
    {
        PreCondition.assertNotNull(backgroundColor, "backgroundColor");

        return this.ui.setBackgroundColor(this, backgroundColor);
    }

    @Override
    public Color getBackgroundColor()
    {
        return this.ui.getBackgroundColor(this);
    }
}
