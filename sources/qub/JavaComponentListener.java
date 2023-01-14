package qub;

public class JavaComponentListener implements java.awt.event.ComponentListener
{
    private Size2Integer currentSize;
    private Function0<Size2Integer> getCurrentSizeFunction;
    private Action1<SizeChange> sizeChangedAction;

    private JavaComponentListener()
    {
    }

    public static JavaComponentListener create()
    {
        return new JavaComponentListener();
    }

    public JavaComponentListener setGetCurrentSizeFunction(Function0<Size2Integer> getCurrentSizeFunction)
    {
        PreCondition.assertNotNull(getCurrentSizeFunction, "getCurrentSizeFunction");

        this.getCurrentSizeFunction = getCurrentSizeFunction;
        this.currentSize = this.getCurrentSizeFunction.run();

        return this;
    }

    public JavaComponentListener setSizeChangedAction(Action1<SizeChange> action)
    {
        PreCondition.assertNotNull(action, "action");

        this.sizeChangedAction = action;

        return this;
    }

    @Override
    public void componentResized(java.awt.event.ComponentEvent e)
    {
        PreCondition.assertNotNull(e, "e");

        if (this.sizeChangedAction != null)
        {
            final Size2Integer previousSize = this.currentSize;
            this.currentSize = this.getCurrentSizeFunction.run();

            if (!Comparer.equal(previousSize, this.currentSize))
            {
                this.sizeChangedAction.run(SizeChange.create()
                    .setPreviousSize(previousSize)
                    .setNewSize(this.currentSize));
            }
        }
    }

    @Override
    public void componentMoved(java.awt.event.ComponentEvent e)
    {
    }

    @Override
    public void componentShown(java.awt.event.ComponentEvent e)
    {
    }

    @Override
    public void componentHidden(java.awt.event.ComponentEvent e)
    {
    }
}
