package qub;

public class EveryoneUIText extends EveryoneUIElement.Base<EveryoneUIText> implements UIText.Typed<EveryoneUIText>
{
    private static final String textPropertyName = "text";
    private static final String horizontalAlignmentPropertyName = "horizontalAlignment";
    private static final String verticalAlignmentPropertyName = "verticalAlignment";

    private String text;
    private HorizontalAlignment horizontalAlignment;
    private VerticalAlignment verticalAlignment;

    private EveryoneUIText(EveryoneSwingUI ui)
    {
        super(ui);

        this.text = "";
        this.horizontalAlignment = HorizontalAlignment.Left;
        this.verticalAlignment = VerticalAlignment.Top;
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

        if (!Comparer.equal(this.text, text))
        {
            this.text = text;

            this.repaint();
        }

        return this;
    }

    @Override
    public HorizontalAlignment getHorizontalAlignment()
    {
        return this.horizontalAlignment;
    }

    @Override
    public EveryoneUIText setHorizontalAlignment(HorizontalAlignment horizontalAlignment)
    {
        return this.setAlignment(horizontalAlignment, this.getVerticalAlignment());
    }

    @Override
    public VerticalAlignment getVerticalAlignment()
    {
        return this.verticalAlignment;
    }

    @Override
    public EveryoneUIText setVerticalAlignment(VerticalAlignment verticalAlignment)
    {
        return this.setAlignment(this.getHorizontalAlignment(), verticalAlignment);
    }

    @Override
    public EveryoneUIText setAlignment(HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment)
    {
        PreCondition.assertNotNull(horizontalAlignment, "horizontalAlignment");
        PreCondition.assertNotNull(verticalAlignment, "verticalAlignment");

        if (this.horizontalAlignment != horizontalAlignment ||
            this.verticalAlignment != verticalAlignment)
        {
            this.horizontalAlignment = horizontalAlignment;
            this.verticalAlignment = verticalAlignment;

            this.repaint();
        }

        return this;
    }

    @Override
    public void paint(UIPainter painter)
    {
        super.paint(painter);

        if (!Strings.isNullOrEmpty(this.text))
        {
            final DrawTextOptions options = DrawTextOptions.create()
                .setText(this.getText());
            switch (this.getHorizontalAlignment())
            {
                case Left:
                    options.setLeftX(0);
                    break;

                case Center:
                    options.setCenterX(this.getWidth() / 2);
                    break;

                case Right:
                    options.setRightX(this.getWidth());
                    break;
            }
            switch (this.getVerticalAlignment())
            {
                case Top:
                    options.setTopY(0);
                    break;

                case Center:
                    options.setCenterY(this.getHeight() / 2);
                    break;

                case Bottom:
                    options.setBottomY(this.getHeight());
                    break;
            }
            painter.drawText(options);
        }
    }

    @Override
    public JSONObject toJson()
    {
        return super.toJson()
            .setString(EveryoneUIText.textPropertyName, this.getText())
            .setString(EveryoneUIText.horizontalAlignmentPropertyName, this.getHorizontalAlignment().toString())
            .setString(EveryoneUIText.verticalAlignmentPropertyName, this.getVerticalAlignment().toString());
    }
}
