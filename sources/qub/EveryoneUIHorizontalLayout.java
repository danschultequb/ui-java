package qub;

public class EveryoneUIHorizontalLayout extends EveryoneUIElement.Base<EveryoneUIHorizontalLayout> implements UIHorizontalLayout.Typed<EveryoneUIHorizontalLayout>, EveryoneUIElementParent
{
    private static final String verticalAlignmentPropertyName = "verticalLayout";
    private static final String elementsPropertyName = "elements";

    private final List<EveryoneUIElement> elements;
    private VerticalAlignment verticalAlignment;

    private EveryoneUIHorizontalLayout(EveryoneSwingUI ui)
    {
        super(ui);

        this.elements = List.create();
        this.verticalAlignment = VerticalAlignment.Top;
    }

    public static EveryoneUIHorizontalLayout create(EveryoneSwingUI ui)
    {
        return new EveryoneUIHorizontalLayout(ui);
    }

    @Override
    public EveryoneUIHorizontalLayout add(UIElement uiElement)
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
            contentWidth += element.getWidth();
            contentHeight = Math.maximum(contentHeight, element.getHeight());
        }
        this.setContentSize(contentWidth, contentHeight);

        this.repaint();

        return this;
    }

    @Override
    public EveryoneUIHorizontalLayout setVerticalAlignment(VerticalAlignment verticalAlignment)
    {
        PreCondition.assertNotNull(verticalAlignment, "verticalAlignment");

        if (this.verticalAlignment != verticalAlignment)
        {
            this.verticalAlignment = verticalAlignment;
            this.repaint();
        }

        return this;
    }

    @Override
    public VerticalAlignment getVerticalAlignment()
    {
        return this.verticalAlignment;
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

        final int layoutHeight = this.getHeight();
        final int halfLayoutHeight = layoutHeight / 2;
        try (final Disposable layoutTransform = painter.saveTransform())
        {
            for (final EveryoneUIElement uiElement : this.elements)
            {
                try (final Disposable savedState = painter.saveState())
                {
                    if (this.verticalAlignment != VerticalAlignment.Top)
                    {
                        final int uiElementHeight = uiElement.getHeight();
                        switch (this.verticalAlignment)
                        {
                            case Center -> painter.translateY(halfLayoutHeight - (uiElementHeight / 2));
                            case Bottom -> painter.translateY(layoutHeight - uiElementHeight);
                        }
                    }

                    painter.setClip(uiElement.getWidth(), uiElement.getHeight());
                    uiElement.paint(painter);
                }

                painter.translateX(uiElement.getWidth());
            }
        }
    }

    @Override
    public void sendPointerEvent(PointerEvent event)
    {
        PreCondition.assertNotNull(event, "event");

        this.handlePointerEnterEvent(event);

        for (final EveryoneUIElement element : this.elements)
        {
            if (element.containsEvent(event))
            {
                element.sendPointerEvent(event);
            }
        }

        this.handlePointerExitEvent(event);
    }

    @Override
    public JSONObject toJson()
    {
        return super.toJson()
            .setString(EveryoneUIHorizontalLayout.verticalAlignmentPropertyName, this.verticalAlignment.toString())
            .setArray(EveryoneUIHorizontalLayout.elementsPropertyName, this.elements.map(EveryoneUIElement::toJson));
    }
}
