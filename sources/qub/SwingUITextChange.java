package qub;

public class SwingUITextChange implements TextChange
{
    private final javax.swing.event.DocumentEvent documentEvent;

    private SwingUITextChange(javax.swing.event.DocumentEvent documentEvent)
    {
        PreCondition.assertNotNull(documentEvent, "documentEvent");

        this.documentEvent = documentEvent;
    }

    public static SwingUITextChange create(javax.swing.event.DocumentEvent documentEvent)
    {
        return new SwingUITextChange(documentEvent);
    }

    @Override
    public TextChangeType getChangeType()
    {
        final TextChangeType result;

        final javax.swing.event.DocumentEvent.EventType eventType = this.documentEvent.getType();
        if (eventType == javax.swing.event.DocumentEvent.EventType.INSERT)
        {
            result = TextChangeType.Insertion;
        }
        else if (eventType == javax.swing.event.DocumentEvent.EventType.REMOVE)
        {
            result = TextChangeType.Removal;
        }
        else
        {
            throw new NotFoundException("Unrecognized " + Types.getFullTypeName(javax.swing.event.DocumentEvent.EventType.class) + ": " + eventType.toString());
        }

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    @Override
    public int getStartIndex()
    {
        return this.documentEvent.getOffset();
    }

    @Override
    public int getLength()
    {
        return this.documentEvent.getLength();
    }

    @Override
    public String getPreviousText()
    {
        PreCondition.assertTrue(this.isInsertion(), "this.isInsertion()");

        final javax.swing.text.Document document = this.documentEvent.getDocument();

        final CharacterList list = CharacterList.create();
        final int startIndex = this.getStartIndex();
        final int afterEndIndex = this.getAfterEndIndex();
        final int documentLength = document.getLength();

        if (startIndex > 0)
        {
            try
            {
                list.addAll(document.getText(0, startIndex));
            }
            catch (javax.swing.text.BadLocationException e)
            {
                throw Exceptions.asRuntime(e);
            }
        }
        if (afterEndIndex < documentLength)
        {
            try
            {
                list.addAll(document.getText(afterEndIndex, documentLength - afterEndIndex));
            }
            catch (javax.swing.text.BadLocationException e)
            {
                throw Exceptions.asRuntime(e);
            }
        }

        final String result = list.toString(true);

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    @Override
    public String getNewText()
    {
        final javax.swing.text.Document document = this.documentEvent.getDocument();
        final int documentLength = document.getLength();
        final String result;
        try
        {
            result = document.getText(0, documentLength);
        }
        catch (javax.swing.text.BadLocationException e)
        {
            throw Exceptions.asRuntime(e);
        }

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    @Override
    public String getInsertedText()
    {
        PreCondition.assertTrue(this.isInsertion(), "this.isInsertion()");

        final int startIndex = this.getStartIndex();
        final int length = this.getLength();
        final String result;
        try
        {
            result = this.documentEvent.getDocument().getText(startIndex, length);
        }
        catch (javax.swing.text.BadLocationException e)
        {
            throw Exceptions.asRuntime(e);
        }

        PostCondition.assertNotNullAndNotEmpty(result, "result");

        return result;
    }
}
