package qub;

public class EveryoneUIText extends EveryoneUIElement.Base<EveryoneUIText> implements UIText.Typed<EveryoneUIText>
{
    private String text;

    private EveryoneUIText(EveryoneSwingUI ui)
    {
        super(ui);

        this.setText("");
    }

    public static EveryoneUIText create(EveryoneSwingUI ui)
    {
        return new EveryoneUIText(ui);
    }

    @Override
    public String getText()
    {
        return this.text;
    }

    @Override
    public EveryoneUIText setText(String text)
    {
        PreCondition.assertNotNull(text, "text");

        this.text = text;

        return this;
    }

    @Override
    public void paint(UIPainter painter)
    {
        super.paint(painter);
    }
}
