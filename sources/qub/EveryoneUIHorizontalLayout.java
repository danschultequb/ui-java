package qub;

public class EveryoneUIHorizontalLayout extends EveryoneUIElement.Base<EveryoneUIHorizontalLayout> implements UIHorizontalLayout.Typed<EveryoneUIHorizontalLayout>
{
    private EveryoneUIHorizontalLayout(EveryoneSwingUI ui)
    {
        super(ui);
    }

    public static EveryoneUIHorizontalLayout create(EveryoneSwingUI ui)
    {
        return new EveryoneUIHorizontalLayout(ui);
    }

    @Override
    public EveryoneUIHorizontalLayout add(UIElement uiElement)
    {
        PreCondition.assertNotNull(uiElement, "uiElement");

        return this;
    }
}
