package qub;

public class EveryoneUITextBox extends EveryoneUIElement.Base<EveryoneUITextBox> implements UITextBox.Typed<EveryoneUITextBox>
{
    private EveryoneUITextBox(EveryoneSwingUI ui)
    {
        super(ui);
    }

    public static EveryoneUITextBox create(EveryoneSwingUI ui)
    {
        return new EveryoneUITextBox(ui);
    }

    @Override
    public int getTextLength()
    {
        return 0;
    }

    @Override
    public String getText()
    {
        return null;
    }

    @Override
    public EveryoneUITextBox setText(String text)
    {
        return null;
    }

    @Override
    public EveryoneUITextBox insertText(int startIndex, String text)
    {
        return null;
    }

    @Override
    public EveryoneUITextBox removeText(int startIndex, int length)
    {
        return null;
    }

    @Override
    public Disposable onTextChanged(Action1<TextChange> callback)
    {
        return null;
    }
}
