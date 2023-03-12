package qub;

/**
 * A type that listeners for Java AWT mouse events.
 */
public class JavaMouseListener extends java.awt.event.MouseAdapter
{
    private Point2Integer previousLocation;
    private Action1<MouseEvent> mousePressedAction;
    private Action1<MouseEvent> mouseReleasedAction;
    private Action1<MouseEvent> mouseMovedAction;

    private JavaMouseListener()
    {
    }

    /**
     * Create a new {@link JavaMouseListener}.
     */
    public static JavaMouseListener create()
    {
        return new JavaMouseListener();
    }

    private MouseEvent convertToMouseEvent(java.awt.event.MouseEvent e)
    {
        PreCondition.assertNotNull(e, "e");

        final MouseEvent result = MouseEvent.create()
            .setNewLocation(e.getX(), e.getY());

        if (e.getID() != java.awt.event.MouseEvent.MOUSE_ENTERED)
        {
            result.setPreviousLocation(this.previousLocation);
        }

        if (e.getID() != java.awt.event.MouseEvent.MOUSE_EXITED)
        {
            this.previousLocation = result.getNewLocation();
        }

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    /**
     * Set the {@link Action1} that will be invoked when a mouse button is pressed.
     * @param mousePressedAction The {@link Action1} that will be invoked when a mouse button is
     *                           pressed.
     * @return This object for method chaining.
     */
    public JavaMouseListener setMousePressedAction(Action1<MouseEvent> mousePressedAction)
    {
        PreCondition.assertNotNull(mousePressedAction, "mousePressedAction");

        this.mousePressedAction = mousePressedAction;

        return this;
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e)
    {
        Actions.run(this.mousePressedAction, this.convertToMouseEvent(e));
    }

    /**
     * Set the {@link Action1} that will be invoked when a mouse button is released.
     * @param mouseReleasedAction The {@link Action1} that will be invoked when a mouse button is
     *                            released.
     * @return This object for method chaining.
     */
    public JavaMouseListener setMouseReleasedAction(Action1<MouseEvent> mouseReleasedAction)
    {
        PreCondition.assertNotNull(mouseReleasedAction, "mouseReleasedAction");

        this.mouseReleasedAction = mouseReleasedAction;

        return this;
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e)
    {
        Actions.run(this.mouseReleasedAction, this.convertToMouseEvent(e));
    }

    /**
     * Set the {@link Action1} that will be invoked when the mouse is moved.
     * @param mouseMovedAction The {@link Action1} that will be invoked when the mouse is moved.
     * @return This object for method chaining.
     */
    public JavaMouseListener setMouseMovedAction(Action1<MouseEvent> mouseMovedAction)
    {
        PreCondition.assertNotNull(mouseMovedAction, "mouseMovedAction");

        this.mouseMovedAction = mouseMovedAction;

        return this;
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e)
    {
        Actions.run(this.mouseMovedAction, this.convertToMouseEvent(e));
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e)
    {
        Actions.run(this.mouseMovedAction, this.convertToMouseEvent(e));
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e)
    {
        Actions.run(this.mouseMovedAction, this.convertToMouseEvent(e));
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e)
    {
        Actions.run(this.mouseMovedAction, this.convertToMouseEvent(e));
    }
}
