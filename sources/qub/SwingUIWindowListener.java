package qub;

public class SwingUIWindowListener extends java.awt.event.WindowAdapter
{
    private final SwingUI ui;
    private final SwingUIWindow window;

    private SwingUIWindowListener(SwingUI ui, SwingUIWindow window)
    {
        PreCondition.assertNotNull(ui, "ui");
        PreCondition.assertNotNull(window, "window");

        this.ui = ui;
        this.window = window;
    }

    public static SwingUIWindowListener create(SwingUI ui, SwingUIWindow window)
    {
        return new SwingUIWindowListener(ui, window);
    }

    @Override
    public void windowOpened(java.awt.event.WindowEvent e)
    {
        this.ui.setCurrentThreadAsyncRunner();
    }

    @Override
    public void windowClosed(java.awt.event.WindowEvent e)
    {
        this.window.close();
    }
}
