package qub;

public class EveryoneUICanvas extends EveryoneUIElement.Base<EveryoneUICanvas> implements UICanvas.Typed<EveryoneUICanvas>
{
    private Action1<UIPainter> paintAction;

    private EveryoneUICanvas(EveryoneSwingUI ui)
    {
        super(ui);

        this.setPaintAction((UIPainter painter) -> {});
    }

    public static EveryoneUICanvas create(EveryoneSwingUI ui)
    {
        return new EveryoneUICanvas(ui);
    }

    @Override
    public EveryoneUICanvas setPaintAction(Action1<UIPainter> paintAction)
    {
        PreCondition.assertNotNull(paintAction, "paintAction");

        if (this.paintAction != paintAction)
        {
            this.paintAction = paintAction;
            this.repaint();
        }

        return this;
    }

    @Override
    public void paint(UIPainter painter)
    {
        super.paint(painter);

        this.paintAction.run(painter);
    }
}
