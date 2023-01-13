package qub;

public class JavaWindowListenter extends java.awt.event.WindowAdapter
{
    private Action1<java.awt.event.WindowEvent> windowOpenedAction;
    private Action1<java.awt.event.WindowEvent> windowClosedAction;

    private JavaWindowListenter()
    {
    }

    public static JavaWindowListenter create()
    {
        return new JavaWindowListenter();
    }

    /**
     * Set the {@link Action0} that will be invoked when a window is opened.
     * @param windowOpenedAction The {@link Action0} to invoke.
     * @return This object for method chaining.
     */
    public JavaWindowListenter setWindowOpenedAction(Action0 windowOpenedAction)
    {
        PreCondition.assertNotNull(windowOpenedAction, "windowOpenedAction");

        return this.setWindowOpenedAction((java.awt.event.WindowEvent e) ->
        {
            windowOpenedAction.run();
        });
    }

    /**
     * Set the {@link Action1} that will be invoked when a window is opened.
     * @param windowOpenedAction The {@link Action1} to invoke.
     * @return This object for method chaining.
     */
    public JavaWindowListenter setWindowOpenedAction(Action1<java.awt.event.WindowEvent> windowOpenedAction)
    {
        PreCondition.assertNotNull(windowOpenedAction, "windowOpenedAction");

        this.windowOpenedAction = windowOpenedAction;

        return this;
    }

    @Override
    public void windowOpened(java.awt.event.WindowEvent e)
    {
        if (this.windowOpenedAction != null)
        {
            this.windowOpenedAction.run(e);
        }
    }

    /**
     * Set the {@link Action0} that will be invoked when a window is closed.
     * @param windowClosedAction The {@link Action0} to invoke.
     * @return This object for method chaining.
     */
    public JavaWindowListenter setWindowClosedAction(Action0 windowClosedAction)
    {
        PreCondition.assertNotNull(windowClosedAction, "windowClosedAction");

        return this.setWindowClosedAction((java.awt.event.WindowEvent e) ->
        {
            windowClosedAction.run();
        });
    }

    /**
     * Set the {@link Action1} that will be invoked when a window is closed.
     * @param windowClosedAction The {@link Action1} to invoke.
     * @return This object for method chaining.
     */
    public JavaWindowListenter setWindowClosedAction(Action1<java.awt.event.WindowEvent> windowClosedAction)
    {
        PreCondition.assertNotNull(windowClosedAction, "windowClosedAction");

        this.windowClosedAction = windowClosedAction;

        return this;
    }

    @Override
    public void windowClosed(java.awt.event.WindowEvent e)
    {
        if (this.windowClosedAction != null)
        {
            this.windowClosedAction.run(e);
        }
    }
}
