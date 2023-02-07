package qub;

public class EveryoneSwingUI extends UI.Base<EveryoneSwingUI>
{
    private final AsyncScheduler asyncScheduler;
    private final List<EveryoneSwingUIWindow> windows;
    private boolean disposed;

    private EveryoneSwingUI(AsyncScheduler asyncScheduler)
    {
        PreCondition.assertNotNull(asyncScheduler, "asyncScheduler");

        this.asyncScheduler = asyncScheduler;
        this.windows = List.create();

        this.setCreator(Iterable.create(UIButton.class, EveryoneUIButton.class), () -> EveryoneUIButton.create(this));
        this.setCreator(Iterable.create(UICanvas.class, EveryoneUICanvas.class), () -> EveryoneUICanvas.create(this));
        this.setCreator(Iterable.create(UIHorizontalLayout.class, EveryoneUIHorizontalLayout.class), () -> EveryoneUIHorizontalLayout.create(this));
        this.setCreator(Iterable.create(UIText.class, EveryoneUIText.class), () -> EveryoneUIText.create(this));
        this.setCreator(Iterable.create(UITextBox.class, EveryoneUITextBox.class), () -> EveryoneUITextBox.create(this));
        this.setCreator(Iterable.create(UIVerticalLayout.class, EveryoneUIVerticalLayout.class), () -> EveryoneUIVerticalLayout.create(this));

        final java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
        final int screenResolution = toolkit.getScreenResolution();
        this.setPixelsPerInch(screenResolution);
    }

    public static EveryoneSwingUI create(AsyncScheduler asyncScheduler)
    {
        return new EveryoneSwingUI(asyncScheduler);
    }

    public Result<? extends UIWindow> createUIWindow()
    {
        return this.createEveryoneUIWindow();
    }

    public Result<? extends EveryoneSwingUIWindow> createEveryoneUIWindow()
    {
        return Result.create(() ->
        {
            final EveryoneSwingUIWindow window = EveryoneSwingUIWindow.create(this);
            window.onDisposed(() ->
            {
                this.windows.remove(window);
            });
            this.windows.add(window);
            return window;
        });
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

                for (final EveryoneSwingUIWindow window : List.create(this.windows))
                {
                    window.dispose().await();
                }
            }
            return result;
        });
    }

    @Override
    public TextMeasurements getTextMeasurements(String text)
    {
        PreCondition.assertNotNull(text, "text");

        final java.awt.FontMetrics fontMetrics = JavaAwtFontMetrics.getDefault();
        return JavaAwtFontMetricsTextMeasurements.create(text, fontMetrics);
    }
}
