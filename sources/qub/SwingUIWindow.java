package qub;

public class SwingUIWindow extends UIWindowBase<SwingUIWindow>
{
    private final SwingUI ui;

    private boolean disposed;
    private final RunnableEvent0 disposeEvent;
    private final AsyncTask<Void> disposeTask;

    private final javax.swing.JFrame jFrame;
    private UIElement content;

    private SwingUIWindow(SwingUI ui)
    {
        PreCondition.assertNotNull(ui, "ui");

        this.ui = ui;

        this.disposeEvent = Event0.create();
        this.disposeTask = this.ui.createPausedAsyncTask(Action0.empty);

        this.jFrame = new javax.swing.JFrame();
        this.jFrame.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        this.jFrame.addWindowListener(new java.awt.event.WindowAdapter()
        {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e)
            {
                SwingUIWindow.this.ui.setCurrentThreadAsyncRunner();
            }

            @Override
            public void windowClosed(java.awt.event.WindowEvent e)
            {
                SwingUIWindow.this.close();
            }
        });
    }

    public static SwingUIWindow create(SwingUI ui)
    {
        return new SwingUIWindow(ui);
    }

    @Override
    public SwingUIWindow setBackgroundColor(Color backgroundColor)
    {
        PreCondition.assertNotNull(backgroundColor, "backgroundColor");

        final java.awt.Color javaAwtColor = this.ui.convertColorToJavaAwtColor(backgroundColor);
        this.jFrame.setBackground(javaAwtColor);

        return this;
    }

    @Override
    public Color getBackgroundColor()
    {
        final java.awt.Color javaAwtColor = this.jFrame.getBackground();
        final Color result = this.ui.convertJavaAwtColorToColor(javaAwtColor);

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    @Override
    public SwingUIWindow setTitle(String title)
    {
        PreCondition.assertNotNull(title, "title");

        this.jFrame.setTitle(title);

        return this;
    }

    @Override
    public String getTitle()
    {
        return this.jFrame.getTitle();
    }

    /**
     * Get whether this {@link SwingUIWindow} is visible.
     */
    public boolean getVisible()
    {
        return this.jFrame.isVisible();
    }

    /**
     * Set the visibility of this {@link SwingUIWindow}.
     * @param visible Whether this {@link SwingUIWindow} is visible.
     * @return This object for method chaining.
     */
    public SwingUIWindow setVisible(boolean visible)
    {
        this.jFrame.setVisible(visible);

        return this;
    }

    /**
     * Run the provided {@link Action0} when this {@link SwingUIWindow} is disposed.
     * @param action The {@link Action0} to run when this {@link SwingUIWindow} is disposed.
     * @return A {@link Disposable} that can unregister the provided {@link Action0}.
     */
    public Disposable onDisposed(Action0 action)
    {
        return this.disposeEvent.subscribe(action);
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

                this.jFrame.dispose();
                this.disposeEvent.run();

                this.disposeTask.schedule();
                this.disposeTask.await();
            }
            return result;
        });
    }

    /**
     * Wait for this {@link SwingUIWindow} to be closed/disposed.
     */
    public void await()
    {
        this.disposeTask.await();
    }

    /**
     * Set the width and height pixel counts of this {@link SwingUIWindow}.
     * @param pixelWidth The new width pixel count of this {@link SwingUIWindow}.
     * @param pixelHeight The new height pixel count of this {@link SwingUIWindow}.
     * @return This object for method chaining.
     */
    public SwingUIWindow setSize(int pixelWidth, int pixelHeight)
    {
        PreCondition.assertGreaterThanOrEqualTo(pixelWidth, 0, "pixelWidth");
        PreCondition.assertGreaterThanOrEqualTo(pixelHeight, 0, "pixelHeight");
        PreCondition.assertNotDisposed(this, "this");

        this.jFrame.setSize(pixelWidth, pixelHeight);

        return this;
    }

    /**
     * Set the width and height {@link Distance}s of this {@link SwingUIWindow}.
     * @param width The new width {@link Distance} of this {@link SwingUIWindow}.
     * @param height The new height {@link Distance} of this {@link SwingUIWindow}.
     * @return This object for method chaining.
     */
    public SwingUIWindow setSize(Distance width, Distance height)
    {
        PreCondition.assertGreaterThanOrEqualTo(width, Distance.zero, "width");
        PreCondition.assertGreaterThanOrEqualTo(height, Distance.zero, "height");
        PreCondition.assertNotDisposed(this, "this");

        final int pixelWidth = this.ui.convertHorizontalDistanceToPixels(width);
        final int pixelHeight = this.ui.convertVerticalDistanceToPixels(height);
        return this.setSize(pixelWidth, pixelHeight);
    }

    /**
     * Set the width pixel count of this {@link SwingUIWindow}.
     * @param pixelWidth The new width pixel count of this {@link SwingUIWindow}.
     * @return This object for method chaining.
     */
    public SwingUIWindow setWidth(int pixelWidth)
    {
        return this.setSize(pixelWidth, this.getHeight());
    }

    /**
     * Set the width {@link Distance} of this {@link SwingUIWindow}.
     * @param width The new width {@link Distance} of this {@link SwingUIWindow}.
     * @return This object for method chaining.
     */
    public SwingUIWindow setWidth(Distance width)
    {
        PreCondition.assertNotNull(width, "width");
        PreCondition.assertGreaterThanOrEqualTo(width, Distance.zero, "width");

        final int pixelWidth = this.ui.convertHorizontalDistanceToPixels(width);
        return this.setWidth(pixelWidth);
    }

    /**
     * Set the height pixel count of this {@link SwingUIWindow}.
     * @param pixelHeight The new height pixel count of this {@link SwingUIWindow}.
     * @return This object for method chaining.
     */
    public SwingUIWindow setHeight(int pixelHeight)
    {
        return this.setSize(this.getWidth(), pixelHeight);
    }

    /**
     * Set the height {@link Distance} of this {@link SwingUIWindow}.
     * @param height The new height of this {@link SwingUIWindow}.
     * @return This object for method chaining.
     */
    public SwingUIWindow setHeight(Distance height)
    {
        PreCondition.assertNotNull(height, "height");
        PreCondition.assertGreaterThanOrEqualTo(height, Distance.zero, "height");

        final int pixelHeight = this.ui.convertVerticalDistanceToPixels(height);
        return this.setHeight(pixelHeight);
    }

    @Override
    public int getWidth()
    {
        return this.jFrame.getWidth();
    }

    @Override
    public Distance getWidthDistance()
    {
        final int pixelWidth = this.getWidth();
        final Distance result = this.ui.convertHorizontalPixelsToDistance(pixelWidth);

        PostCondition.assertNotNull(result, "result");
        PostCondition.assertGreaterThanOrEqualTo(result, Distance.zero, "result");

        return result;
    }

    @Override
    public int getHeight()
    {
        return this.jFrame.getHeight();
    }

    @Override
    public Distance getHeightDistance()
    {
        final int pixelHeight = this.getHeight();
        final Distance result = this.ui.convertVerticalPixelsToDistance(pixelHeight);

        PostCondition.assertNotNull(result, "result");
        PostCondition.assertGreaterThanOrEqualTo(result, Distance.zero, "result");

        return result;
    }

    @Override
    public Result<? extends UIElement> getContent()
    {
        return Result.create(() ->
        {
            if (this.content == null)
            {
                throw new NotFoundException("No " + Types.getTypeName(UIElement.class) + " content has been set in this " + Types.getTypeName(UIWindow.class) + ".");
            }
            final UIElement result = this.content;

            PostCondition.assertNotNull(result, "result");

            return result;
        });
    }

    @Override
    public SwingUIWindow setContent(UIElement uiElement)
    {
        PreCondition.assertNotNull(uiElement, "uiElement");
        PreCondition.assertInstanceOf(uiElement, JComponentUIElement.class, "uiElement");

        final JComponentUIElement jComponentUIElement = (JComponentUIElement)uiElement;
        final javax.swing.JComponent jComponent = jComponentUIElement.getJComponent();
        this.jFrame.setContentPane(jComponent);
        this.content = uiElement;

        return this;
    }
}
