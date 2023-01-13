package qub;

public class SwingUITextBox implements UITextBox.Typed<SwingUITextBox>, JComponentUIElement.Typed<SwingUITextBox>
{
    private final SwingUI ui;
    private final javax.swing.JTextArea javaTextBox;

    private SwingUITextBox(SwingUI ui)
    {
        PreCondition.assertNotNull(ui, "ui");

        this.ui = ui;
        this.javaTextBox = new javax.swing.JTextArea();
    }

    public static SwingUITextBox create(SwingUI ui)
    {
        return new SwingUITextBox(ui);
    }

    private javax.swing.text.Document getDocument()
    {
        return this.javaTextBox.getDocument();
    }

    @Override
    public javax.swing.JComponent getJComponent()
    {
        return this.javaTextBox;
    }

    @Override
    public SwingUITextBox setBackgroundColor(Color backgroundColor)
    {
        return JComponentUIElement.Typed.super.setBackgroundColor(backgroundColor);
    }

    @Override
    public int getTextLength()
    {
        return this.getDocument().getLength();
    }

    @Override
    public String getText()
    {
        return this.javaTextBox.getText();
    }

    @Override
    public SwingUITextBox setText(String text)
    {
        PreCondition.assertNotNull(text, "text");

        this.javaTextBox.setText(text);

        return this;
    }

    @Override
    public SwingUITextBox insertText(int startIndex, String text)
    {
        PreCondition.assertBetween(0, startIndex, this.getTextLength(), "startIndex");

        try
        {
            this.getDocument().insertString(startIndex, text, null);
        }
        catch (javax.swing.text.BadLocationException e)
        {
            throw Exceptions.asRuntime(e);
        }

        return this;
    }

    @Override
    public SwingUITextBox removeText(int startIndex, int length)
    {
        PreCondition.assertStartIndex(startIndex, this.getTextLength(), "startIndex");
        PreCondition.assertLength(length, startIndex, this.getTextLength(), "length");

        try
        {
            final javax.swing.text.Document document = this.getDocument();
            document.remove(startIndex, length);
        }
        catch (javax.swing.text.BadLocationException e)
        {
            throw Exceptions.asRuntime(e);
        }

        return this;
    }

    @Override
    public Disposable onTextChanged(Action1<TextChange> callback)
    {
        PreCondition.assertNotNull(callback, "callback");

        final javax.swing.text.Document document = this.getDocument();
        final SwingUIDocumentListener listener = SwingUIDocumentListener.create(callback);

        document.addDocumentListener(listener);
        final Disposable result = Disposable.create(() ->
        {
            document.removeDocumentListener(listener);
        });

        PostCondition.assertNotNull(result, "result");

        return result;
    }
}
