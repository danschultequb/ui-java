package qub;

/**
 * A {@link UI} implementation that uses Swing UI {@link javax.swing.JComponent}s.
 */
public class SwingUI extends UI.Base<SwingUI>
{
    private final AsyncScheduler asyncScheduler;
    private final List<SwingUIWindow> windows;
    private boolean disposed;

    private SwingUI(AsyncScheduler asyncScheduler)
    {
        PreCondition.assertNotNull(asyncScheduler, "asyncScheduler");

        this.asyncScheduler = asyncScheduler;
        this.windows = List.create();

        this.setCreator(Iterable.create(UIText.class, SwingUIText.class), () -> SwingUIText.create(this));
        this.setCreator(Iterable.create(UITextBox.class, SwingUITextBox.class), () -> SwingUITextBox.create(this));
        this.setCreator(Iterable.create(UIButton.class, SwingUIButton.class), () -> SwingUIButton.create(this));
        this.setCreator(Iterable.create(UIHorizontalLayout.class, SwingUIHorizontalLayout.class), () -> SwingUIHorizontalLayout.create(this));
        this.setCreator(Iterable.create(UICanvas.class, SwingUICanvas.class), () -> SwingUICanvas.create(this));
        this.setCreator(Iterable.create(UIVerticalLayout.class, SwingUIVerticalLayout.class), () -> SwingUIVerticalLayout.create(this));
        this.setCreator(Iterable.create(UIWindow.class, SwingUIWindow.class), () ->
        {
            final SwingUIWindow window = SwingUIWindow.create(this);
            window.onDisposed(() ->
            {
                this.windows.remove(window);
            });
            this.windows.add(window);
            return window;
        });

        final java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
        final int screenResolution = toolkit.getScreenResolution();
        this.setPixelsPerInch(screenResolution);
    }

    public static SwingUI create(AsyncScheduler asyncScheduler)
    {
        return new SwingUI(asyncScheduler);
    }

    public Result<? extends UIWindow> createUIWindow()
    {
        return this.create(UIWindow.class);
    }

    public Result<? extends SwingUIWindow> createSwingUIWindow()
    {
        return this.create(SwingUIWindow.class);
    }

    public void setCurrentThreadAsyncRunner()
    {
        CurrentThread.setAsyncRunner(this.asyncScheduler);
    }

    /**
     * Create a new {@link PausedAsyncTask} that will run the provided action when it is scheduled.
     * @param action The {@link Action0} to run when the returned {@link PausedAsyncTask} is
     *               scheduled.
     */
    public AsyncTask<Void> createPausedAsyncTask(Action0 action)
    {
        PreCondition.assertNotNull(action, "action");

        return this.asyncScheduler.create(action);
    }

    @Override
    public boolean isDisposed()
    {
        return this.disposed;
    }

    @Override
    public Result<Boolean> dispose()
    {
        return Result.create(() ->
        {
            final boolean result = !this.disposed;
            if (result)
            {
                this.disposed = true;

                for (final SwingUIWindow window : List.create(this.windows))
                {
                    window.dispose().await();
                }
            }
            return result;
        });
    }
}
