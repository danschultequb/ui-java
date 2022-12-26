package qub;

/**
 * A {@link UI} implementation that uses Swing UI {@link javax.swing.JComponent}s.
 */
public class SwingUI extends UIBase<SwingUI>
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
        return this.createSwingUIWindow();
    }

    public Result<SwingUIWindow> createSwingUIWindow()
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

    /**
     * Convert the provided {@link Color} to {@link java.awt.Color}.
     * @param color The {@link Color} to convert.
     */
    public java.awt.Color convertColorToJavaAwtColor(Color color)
    {
        PreCondition.assertNotNull(color, "javaAwtColor");

        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        final int alpha = color.getAlpha();
        final java.awt.Color result = new java.awt.Color(red, green, blue, alpha);

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    /**
     * Convert the provided {@link java.awt.Color} to {@link Color}.
     * @param javaAwtColor The {@link java.awt.Color} to convert.
     */
    public Color convertJavaAwtColorToColor(java.awt.Color javaAwtColor)
    {
        PreCondition.assertNotNull(javaAwtColor, "javaAwtColor");

        final int red = javaAwtColor.getRed();
        final int green = javaAwtColor.getGreen();
        final int blue = javaAwtColor.getBlue();
        final int alpha = javaAwtColor.getAlpha();
        final Color result = MutableColor.create(red, green, blue, alpha);

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    /**
     * Set the background color on the provided {@link T} to be the provided {@link Color}.
     * @param uiElement The {@link T} to set the background color on.
     * @param backgroundColor The {@link Color} to set as the background color.
     */
    public <T extends JComponentUIElement> T setBackgroundColor(T uiElement, Color backgroundColor)
    {
        PreCondition.assertNotNull(uiElement, "uiElement");
        PreCondition.assertNotNull(backgroundColor, "backgroundColor");

        final javax.swing.JComponent jComponent = uiElement.getJComponent();
        final java.awt.Color javaAwtColor = this.convertColorToJavaAwtColor(backgroundColor);
        jComponent.setBackground(javaAwtColor);

        final boolean isOpaque = (backgroundColor.getAlpha() != 0);
        jComponent.setOpaque(isOpaque);

        return uiElement;
    }

    /**
     * Get the background color on the provided {@link JComponentUIElement}.
     * @param uiElement The {@link JComponentUIElement} to get the background color of.
     */
    public Color getBackgroundColor(JComponentUIElement uiElement)
    {
        PreCondition.assertNotNull(uiElement, "uiElement");

        final javax.swing.JComponent jComponent = uiElement.getJComponent();
        final java.awt.Color javaAwtColor = jComponent.getBackground();
        final Color result = this.convertJavaAwtColorToColor(javaAwtColor);

        PostCondition.assertNotNull(result, "result");

        return result;
    }
}
