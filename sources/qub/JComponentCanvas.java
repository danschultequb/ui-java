package qub;

public class JComponentCanvas extends javax.swing.JComponent
{
    private Action1<UIPainter> paintAction;

    private JComponentCanvas()
    {
        this.setPaintAction((UIPainter painter) -> {});
    }

    public static JComponentCanvas create()
    {
        return new JComponentCanvas();
    }

    public JComponentCanvas setPaintAction(Action1<UIPainter> paintAction)
    {
        PreCondition.assertNotNull(paintAction, "paintAction");

        this.paintAction = paintAction;

        return this;
    }

    @Override
    protected void paintComponent(java.awt.Graphics graphics)
    {
        PreCondition.assertNotNull(graphics, "graphics");
        PreCondition.assertInstanceOf(graphics, java.awt.Graphics2D.class, "graphics");

        super.paintComponent(graphics);

        this.paint(SwingUIPainter.create((java.awt.Graphics2D)graphics));
    }

    public void paint(UIPainter painter)
    {
        PreCondition.assertNotNull(painter, "painter");
        PreCondition.assertNotNull(this.paintAction, "this.paintAction");

        this.paintAction.run(painter);
    }
}
