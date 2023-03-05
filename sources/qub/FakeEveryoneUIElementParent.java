package qub;

/**
 * A fake {@link EveryoneUIElementParent} implementation.
 */
public class FakeEveryoneUIElementParent implements EveryoneUIElementParent
{
    private Action0 repaintAction;

    private FakeEveryoneUIElementParent()
    {
    }

    public static FakeEveryoneUIElementParent create()
    {
        return new FakeEveryoneUIElementParent();
    }

    /**
     * Set the {@link Action0} that should be invoked when this
     * {@link FakeEveryoneUIElementParent}'s repaint method is invoked.
     * @param repaintAction The {@link Action0} that should be invoked when this
     * {@link FakeEveryoneUIElementParent}'s repaint method is invoked.
     * @return This object for method chaining.
     */
    public FakeEveryoneUIElementParent setRepaintAction(Action0 repaintAction)
    {
        this.repaintAction = repaintAction;

        return this;
    }

    @Override
    public void repaint()
    {
        if (this.repaintAction != null)
        {
            this.repaintAction.run();
        }
    }
}
