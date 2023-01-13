package qub;

public class SwingUICanvas implements JComponentUIElement.Typed<SwingUICanvas>, UICanvas.Typed<SwingUICanvas>
{
    private final SwingUI ui;
    private final JComponentCanvas jComponentCanvas;

    protected SwingUICanvas(SwingUI ui)
    {
        PreCondition.assertNotNull(ui, "ui");

        this.ui = ui;
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
    public SwingUICanvas setBackgroundColor(Color backgroundColor)
    {
        return JComponentUIElement.Typed.super.setBackgroundColor(backgroundColor);
    }

    @Override
    public Color getBackgroundColor()
    {
        return JComponentUIElement.Typed.super.getBackgroundColor();
    }

    @Override
    public int getWidth()
    {
        return this.jComponentCanvas.getWidth();
    }

    @Override
    public int getHeight()
    {
        return this.jComponentCanvas.getHeight();
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
