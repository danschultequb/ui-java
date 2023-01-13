package qub;

public class EveryoneUIButton extends EveryoneUIElement.Base<EveryoneUIButton> implements UIButton.Typed<EveryoneUIButton>
{
    private String text;

    private EveryoneUIButton(EveryoneSwingUI ui)
    {
        super(ui);

        this.setText("");
    }

    public static EveryoneUIButton create(EveryoneSwingUI ui)
    {
        return new EveryoneUIButton(ui);
    }

    @Override
    public String getText()
    {
        return this.text;
    }

    @Override
    public EveryoneUIButton setText(String text)
    {
        PreCondition.assertNotNull(text, "text");

        this.text = text;

        return this;
    }

    @Override
    public Disposable onClick(Action0 callback)
    {
        PreCondition.assertNotNull(callback, "callback");

        return Disposable.create();
    }

    @Override
    public void paint(UIPainter painter)
    {
        super.paint(painter);

        final int pixelWidth = this.getWidth();
        final int pixelHeight = this.getHeight();

        painter.drawRectangle(0, 0, pixelWidth, pixelHeight);
    }
}
