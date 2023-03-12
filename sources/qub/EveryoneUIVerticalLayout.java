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

        final EveryoneUIElement everyoneUIElement = (EveryoneUIElement)uiElement;
        this.elements.add(everyoneUIElement);
        everyoneUIElement.setParent(this);

        int contentWidth = 0;
        int contentHeight = 0;
        for (final EveryoneUIElement element : this.elements)
        {
            contentWidth = Math.maximum(contentWidth, element.getWidth());
            contentHeight += element.getHeight();
        }
        this.setContentSize(contentWidth, contentHeight);

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
                try (final Disposable savedState = painter.saveState())
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

                    painter.setClip(uiElement.getWidth(), uiElement.getHeight());
                    uiElement.paint(painter);
                }

                painter.translateY(uiElement.getHeight());
            }
        }
    }

    @Override
    public void sendPointerEvent(PointerEvent event)
    {
        PreCondition.assertNotNull(event, "event");

        this.handlePointerEnterEvent(event);

        final int layoutWidth = this.getWidth();
        final int halfLayoutWidth = layoutWidth / 2;
        try (final Disposable layoutState = event.saveState())
        {
            for (final EveryoneUIElement uiElement : this.elements)
            {
                try (final Disposable uiElementState = event.saveState())
                {
                    if (this.horizontalAlignment != HorizontalAlignment.Left)
                    {
                        final int uiElementWidth = uiElement.getWidth();
                        switch (this.horizontalAlignment)
                        {
                            case Center -> event.inverseTranslateX(halfLayoutWidth - (uiElementWidth / 2));
                            case Right -> event.inverseTranslateX(layoutWidth - uiElementWidth);
                        }
                    }

                    if (uiElement.containsEvent(event))
                    {
                        uiElement.sendPointerEvent(event);
                    }
                }

                event.inverseTranslateY(uiElement.getHeight());
            }
        }

        this.handlePointerExitEvent(event);
    }

    @Override
    public JSONObject toJson()
    {
        return super.toJson()
            .setString(EveryoneUIVerticalLayout.horizontalAlignmentPropertyName, this.horizontalAlignment.toString())
            .setArray(EveryoneUIVerticalLayout.elementsPropertyName, this.elements.map(EveryoneUIElement::toJson));
    }
}
