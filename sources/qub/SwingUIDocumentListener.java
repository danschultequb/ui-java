package qub;

public class SwingUIDocumentListener implements javax.swing.event.DocumentListener
{
    private final Action1<TextChange> callback;

    private SwingUIDocumentListener(Action1<TextChange> callback)
    {
        PreCondition.assertNotNull(callback, "callback");

        this.callback = callback;
    }

    public static SwingUIDocumentListener create(Action1<TextChange> callback)
    {
        return new SwingUIDocumentListener(callback);
    }

    @Override
    public void insertUpdate(javax.swing.event.DocumentEvent e)
    {
        this.callback.run(SwingUITextChange.create(e));
    }

    @Override
    public void removeUpdate(javax.swing.event.DocumentEvent e)
    {
        this.callback.run(SwingUITextChange.create(e));
    }

    @Override
    public void changedUpdate(javax.swing.event.DocumentEvent e)
    {
        this.callback.run(SwingUITextChange.create(e));
    }
}
