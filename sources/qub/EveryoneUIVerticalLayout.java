package qub;

public class EveryoneUIVerticalLayout extends EveryoneUIElement.Base<EveryoneUIVerticalLayout> implements UIVerticalLayout.Typed<EveryoneUIVerticalLayout>
{
    private EveryoneUIVerticalLayout(EveryoneSwingUI ui)
    {
        super(ui);
    }

    public static EveryoneUIVerticalLayout create(EveryoneSwingUI ui)
    {
        return new EveryoneUIVerticalLayout(ui);
    }

    @Override
    public EveryoneUIVerticalLayout add(UIElement uiElement)
    {
        PreCondition.assertNotNull(uiElement, "uiElement");

        return this;
    }
}
