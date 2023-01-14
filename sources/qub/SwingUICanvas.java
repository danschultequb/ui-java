package qub;

public class SwingUICanvas extends JComponentUIElement.Base<SwingUICanvas> implements UICanvas.Typed<SwingUICanvas>
{
    private final JComponentCanvas jComponentCanvas;

    protected SwingUICanvas(SwingUI ui)
    {
        super(ui);

        this.jComponentCanvas = JComponentCanvas.create();
    }

    public static SwingUICanvas create(SwingUI ui)
    {
        return new SwingUICanvas(ui);
    }

    @Override
    public SwingUICanvas setPaintAction(Action1<UIPainter> paintAction)
    {
        PreCondition.assertNotNull(paintAction, "paintAction");

        this.jComponentCanvas.setPaintAction(paintAction);

        return this;
    }

    @Override
    public javax.swing.JComponent getJComponent()
    {
        return this.jComponentCanvas;
    }

    @Override
    public void paint(UIPainter painter)
    {
        PreCondition.assertNotNull(painter, "painter");

        this.jComponentCanvas.paint(painter);
    }
}
