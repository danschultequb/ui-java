package qub;

public class EveryoneUIVerticalLayout extends EveryoneUIElement.Base<EveryoneUIVerticalLayout> implements UIVerticalLayout.Typed<EveryoneUIVerticalLayout>, EveryoneUIElementParent
{
    private static final String horizontalAlignmentPropertyName = "horizontalAlignment";
    private static final String elementsPropertyName = "elements";

    private final List<EveryoneUIElement> elements;
    private HorizontalAlignment horizontalAlignment;

    private EveryoneUIVerticalLayout(EveryoneSwingUI ui)
    {
        super(ui);

        this.elements = List.create();
        this.horizontalAlignment = HorizontalAlignment.Left;
    }

    public static EveryoneUIVerticalLayout create(EveryoneSwingUI ui)
    {
        return new EveryoneUIVerticalLayout(ui);
    }

    @Override
    public EveryoneUIVerticalLayout add(UIElement uiElement)
    {
        PreCondition.assertNotNull(uiElement, "uiElement");
        PreCondition.assertInstanceOf(uiElement, EveryoneUIElement.class, "uiElement");

        this.elements.add((EveryoneUIElement)uiElement);
        this.repaint();

        return this;
    }

    @Override
    public EveryoneUIVerticalLayout setHorizontalAlignment(HorizontalAlignment horizontalAlignment)
    {
        PreCondition.assertNotNull(horizontalAlignment, "horizontalAlignment");

        if (this.horizontalAlignment != horizontalAlignment)
        {
            this.horizontalAlignment = horizontalAlignment;

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
    public void repaint()
    {
        super.repaint();
    }

    @Override
    public void paint(UIPainter painter)
    {
        PreCondition.assertNotNull(painter, "painter");

        super.paint(painter);

        final int layoutWidth = this.getWidth();
        final int halfLayoutWidth = layoutWidth / 2;
        try (final Disposable layoutTransform = painter.saveTransform())
        {
            for (final EveryoneUIElement uiElement : this.elements)
            {
                try (final Disposable childTransform = painter.saveTransform())
                {
                    if (this.horizontalAlignment != HorizontalAlignment.Left)
                    {
                        final int uiElementWidth = uiElement.getWidth();
                        switch (this.horizontalAlignment)
                        {
                            case Center -> painter.translateX(halfLayoutWidth - (uiElementWidth / 2));
                            case Right -> painter.translateX(layoutWidth - uiElementWidth);
                        }
                    }

                    uiElement.paint(painter);
                }

                painter.translateY(uiElement.getHeight());
            }
        }
    }

    @Override
    public JSONObject toJson()
    {
        return super.toJson()
            .setString(EveryoneUIVerticalLayout.horizontalAlignmentPropertyName, this.horizontalAlignment.toString())
            .setArray(EveryoneUIVerticalLayout.elementsPropertyName, this.elements.map(EveryoneUIElement::toJson));
    }
}
