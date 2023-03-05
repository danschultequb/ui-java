package qub;

public class EveryoneUIButton extends EveryoneUIElement.Base<EveryoneUIButton> implements UIButton.Typed<EveryoneUIButton>
{
    private static final String textPropertyName = "text";

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

        if (!Comparer.equal(this.text, text))
        {
            this.text = text;

            final TextMeasurements measurements = this.ui.getTextMeasurements(text);
            this.setContentSize(measurements.getWidth(), measurements.getHeight());

            this.repaint();
        }

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

        final int width = this.getWidth();
        final int height = this.getHeight();
        painter.drawRectangle(0, 0, width, height);

        final String text = this.getText();
        if (!Strings.isNullOrEmpty(text))
        {
            painter.drawText(DrawTextOptions.create()
                .setCenterX(width / 2)
                .setCenterY(height / 2)
                .setText(text));
        }
    }

    @Override
    public JSONObject toJson()
    {
        return super.toJson()
            .setString(EveryoneUIButton.textPropertyName, this.getText());
    }
}
